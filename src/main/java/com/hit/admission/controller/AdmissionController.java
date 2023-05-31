package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.dto.AdmissionCreateDTO;
import com.hit.admission.dto.AdmissionUpdateDTO;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.model.Admission;
import com.hit.admission.model.Block;
import com.hit.admission.model.Major;
import com.hit.admission.model.Student;
import static com.hit.admission.utils.SessionUtil.close;
import static com.hit.admission.utils.SessionUtil.getSession;
import static com.hit.admission.utils.SessionUtil.rollback;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Huy Doan
 */
public class AdmissionController extends BaseDAO {

    private final StudentController studentController;

    private final MajorController majorController;

    private final BlockController blockController;

    public AdmissionController() {
        this.studentController = new StudentController();
        this.majorController = new MajorController();
        this.blockController = new BlockController();
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
            admission.setStatus(Boolean.FALSE);
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
            admission.setStatus(Boolean.FALSE);
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

}
