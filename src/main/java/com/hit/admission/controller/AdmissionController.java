package com.hit.admission.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hit.admission.base.BaseDAO;
import com.hit.admission.constants.AdmissionStatus;
import com.hit.admission.dto.AdmissionCreateDTO;
import com.hit.admission.dto.AdmissionResultDTO;
import com.hit.admission.dto.AdmissionResultRequest;
import com.hit.admission.dto.AdmissionUpdateDTO;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.dto.StudentDTO;
import com.hit.admission.mapper.AdmissionMapper;
import com.hit.admission.model.Admission;
import com.hit.admission.model.Block;
import com.hit.admission.model.Major;
import com.hit.admission.model.Student;
import static com.hit.admission.utils.SessionUtil.close;
import static com.hit.admission.utils.SessionUtil.getSession;
import static com.hit.admission.utils.SessionUtil.rollback;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Huy Doan
 */
public class AdmissionController extends BaseDAO {

    private final StudentController studentController;

    private final MajorController majorController;

    private final BlockController blockController;

    private final AdmissionMapper admissionMapper;

    public AdmissionController() {
        this.studentController = new StudentController();
        this.majorController = new MajorController();
        this.blockController = new BlockController();
        this.admissionMapper = Mappers.getMapper(AdmissionMapper.class);
    }

    public List<AdmissionResultDTO> getAdmissionResult(AdmissionResultRequest request) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT s.last_name, s.first_name, s.order_number, s.citizen_identity_number, ");
            sql.append("s.email, s.phone_number, s.gender, s.address, a.orders, b.code, a.total_score ");
            sql.append("FROM admissions a ");
            sql.append("INNER JOIN students s on s.id = a.student_id ");
            sql.append("INNER JOIN blocks b on b.id = a.block_id ");
            sql.append("INNER JOIN majors m on m.id = a.major_id ");
            sql.append("WHERE YEAR(a.created_date) = :year AND (:status IS NULL OR a.status = :status) AND m.code = :code ");
            sql.append("AND (COALESCE(:keyword, '') = '' OR s.last_name LIKE CONCAT('%', :keyword, '%') ");
            sql.append("OR s.last_name LIKE CONCAT('%', :keyword, '%') OR s.first_name LIKE CONCAT('%', :keyword, '%')");
            sql.append("OR s.order_number LIKE CONCAT('%', :keyword, '%') OR s.citizen_identity_number LIKE CONCAT('%', :keyword, '%')");
            sql.append("OR s.email LIKE CONCAT('%', :keyword, '%') OR s.phone_number LIKE CONCAT('%', :keyword, '%'))");
            Query query = session.createNativeQuery(sql.toString());
            query.setParameter("year", request.getYear());
            query.setParameter("keyword", request.getKeyword());
            query.setParameter("code", request.getCode());
            query.setParameter("status", request.getStatusAdmission());
            List<Object[]> queryResults = query.getResultList();
            tx.commit();
            return admissionMapper.objectsToAdmissionResultDtos(queryResults);
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }
    }

    public List<Admission> getAdmissionsByStudentId(Integer studentId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT * FROM admissions WHERE student_id = :studentId ORDER BY orders";
            Query query = session.createNativeQuery(sql, Admission.class);
            query.setParameter("studentId", studentId);
            tx.commit();
            return query.getResultList();
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }
    }

    public Admission getAdmissionsByStudentIdAndOrders(Integer studentId, Integer orders) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            String sql = "SELECT * FROM admissions WHERE student_id = :studentId AND orders = :orders";
            Query query = session.createNativeQuery(sql, Admission.class);
            query.setParameter("studentId", studentId);
            query.setParameter("orders", orders);
            List<Admission> admissions = query.getResultList();
            if (CollectionUtils.isEmpty(admissions)) {
                return null;
            }
            Admission admission = admissions.get(0);
            tx.commit();
            return admission;
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }
    }

    public CommonResponse createAdmission(AdmissionCreateDTO admissionCreateDTO) {
        try {
            Student student = studentController.getStudentById(admissionCreateDTO.getStudentId());
            Major major = majorController.getMajorByCode(admissionCreateDTO.getMajorCode());
            List<Admission> admissions = getAdmissionsByStudentId(student.getId());
            for (Admission admission : admissions) {
                if (admission.getOrders().equals(admissionCreateDTO.getOrders())) {
                    return new CommonResponse(Boolean.FALSE, "Bạn đã đăng ký nguyện vọng " + admissionCreateDTO.getOrders());
                }
                if (admission.getMajor().getId().equals(major.getId())) {
                    return new CommonResponse(Boolean.FALSE, "Bạn đã đăng ký ngành " + major.getName());
                }
            }
            Block block = blockController.getBlockByCode(admissionCreateDTO.getBlock());
            Admission admission = new Admission();
            admission.setOrders(admissionCreateDTO.getOrders());
            admission.setStudent(student);
            admission.setMajor(major);
            admission.setBlock(block);
            admission.setStatus(AdmissionStatus.PENDING.getValue());
            save(admission);
            return new CommonResponse(Boolean.TRUE, "Đăng ký nguyện vọng thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Đăng ký nguyện vọng thất bại!");
        }
    }

    public CommonResponse updateAdmission(AdmissionUpdateDTO admissionUpdateDTO) {
        try {
            List<Admission> admissions = getAdmissionsByStudentId(admissionUpdateDTO.getStudentId());
            for (Admission am : admissions) {
                if (am.getOrders().equals(admissionUpdateDTO.getOrders())
                        && !admissionUpdateDTO.getOldOrders().equals(admissionUpdateDTO.getOrders())) {
                    return new CommonResponse(Boolean.FALSE, "Bạn đã đăng ký nguyện vọng " + admissionUpdateDTO.getOrders());
                }
            }
            Admission admission = getAdmissionsByStudentIdAndOrders(
                    admissionUpdateDTO.getStudentId(), admissionUpdateDTO.getOldOrders());
            Block block = blockController.getBlockByCode(admissionUpdateDTO.getBlock());
            admission.setOrders(admissionUpdateDTO.getOrders());
            admission.setBlock(block);
            admission.setStatus(AdmissionStatus.PENDING.getValue());
            save(admission);
            return new CommonResponse(Boolean.TRUE, "Cập nhật nguyện vọng thành công");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Cập nhật nguyện vọng thất bại!");
        }
    }

    public CommonResponse deleteAdmission(Integer studentId, Integer orders) throws Exception {
        Admission admission = getAdmissionsByStudentIdAndOrders(studentId, orders);
        if (ObjectUtils.isEmpty(admission)) {
            return new CommonResponse(Boolean.FALSE, String.format("Không tìm thấy nguyện vọng %s của sinh viên %s!", orders, studentId));
        }
        delete(admission);
        return new CommonResponse(Boolean.TRUE, "Xóa nguyện vọng thành công");
    }

    public CommonResponse handleAdmissonAndSendMail(int year) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<StudentDTO> students = studentController.getStudents(year, "");
            for (StudentDTO student : students) {
                List<Admission> admissions = getAdmissionsByStudentId(student.getId());
                
            }
            tx.commit();
            return null;
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        } finally {
            close(session);
        }
    }

    
}
