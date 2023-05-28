package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.dto.StudentDTO;
import com.hit.admission.mapper.StudentMapper;
import com.hit.admission.model.Student;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.type.StringType;

/**
 *
 * @author Huy Doan
 */
public class StudentController extends BaseDAO {

    private final Logger logger = Logger.getLogger(StudentController.class);

    private final StudentMapper studentMapper;

    public StudentController() {
        this.studentMapper = Mappers.getMapper(StudentMapper.class);
    }

    public List<StudentDTO> getStudents(String keyword) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Student> queryResults = getResultStudents(session, keyword);
            tx.commit();
            return studentMapper.toStudentDTOs(queryResults);
        } catch (Exception e) {
            rollback(tx);
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            close(session);
        }
        return null;
    }

    private List<Student> getResultStudents(Session session, String keyword) {
        StringBuilder stringQuery = new StringBuilder();
        stringQuery.append("SELECT * FROM students ");
        stringQuery.append("WHERE (COALESCE(:keyword, '') = '' OR first_name LIKE CONCAT('%', :keyword, '%') ");
        stringQuery.append("OR last_name LIKE CONCAT('%', :keyword, '%') OR citizen_identity_number LIKE CONCAT('%', :keyword, '%')");
        stringQuery.append("OR email LIKE CONCAT('%', :keyword, '%') OR phone_number LIKE CONCAT('%', :keyword, '%')");
        stringQuery.append("OR order_number LIKE CONCAT('%', :keyword, '%'))");
        Query query = session.createNativeQuery(stringQuery.toString(), Student.class);
        query.setParameter("keyword", keyword, StringType.INSTANCE);
        return query.getResultList();
    }

    public StudentDTO getInfoStudent(Integer studentId) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("SELECT * FROM students WHERE id = :id", Student.class);
            query.setParameter("id", studentId);
            List<Student> students = query.getResultList();
            Student student = students.get(0);
            tx.commit();
            return studentMapper.toStudentDTO(student);
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            close(session);
        }
    }

    public CommonResponse createStudent(StudentDTO studentDTO) throws Exception {
        List<Student> students = (List<Student>) findAll(Student.class);
        for (Student s : students) {
            if (s.getEmail().equals(studentDTO.getEmail())) {
                return new CommonResponse(Boolean.FALSE, "Email này đã được đăng ký!");
            } else if (s.getCitizenIdentityNumber().equals(studentDTO.getCitizenIdentityNumber())) {
                return new CommonResponse(Boolean.FALSE, "Số CMND/CCCD này đã được đăng ký!");
            } else if (ObjectUtils.isNotEmpty(studentDTO.getOrderNumber()) && s.getOrderNumber().equals(studentDTO.getOrderNumber())) {
                return new CommonResponse(Boolean.FALSE, "SBD này đã trùng với người khác!");
            }
        }
        Student student = studentMapper.toStudent(studentDTO);
        save(student);
        return new CommonResponse(Boolean.TRUE, "Thêm sinh viên thành công");
    }

    public CommonResponse updateStudent(StudentDTO studentDTO) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            List<Student> students = (List<Student>) findAll(Student.class);
            for (Student s : students) {
                if (s.getEmail().equals(studentDTO.getEmail()) && !s.getId().equals(studentDTO.getId())) {
                    return new CommonResponse(Boolean.FALSE, "Email này đã được đăng ký!");
                } else if (s.getCitizenIdentityNumber().equals(studentDTO.getCitizenIdentityNumber())
                        && !s.getId().equals(studentDTO.getId())) {
                    return new CommonResponse(Boolean.FALSE, "Số CMND/CCCD này đã được đăng ký!");
                } else if (ObjectUtils.isNotEmpty(studentDTO.getOrderNumber())
                        && s.getOrderNumber().equals(studentDTO.getOrderNumber()) && !s.getId().equals(studentDTO.getId())) {
                    return new CommonResponse(Boolean.FALSE, "SBD này đã trùng với người khác!");
                }
            }
            Student student = session.load(Student.class, studentDTO.getId());
            studentMapper.updateStudentFromDTO(studentDTO, student);
            session.saveOrUpdate(student);
            tx.commit();
            return new CommonResponse(Boolean.TRUE, "Cập nhật thông tin cá nhân thành công");
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            return new CommonResponse(Boolean.FALSE, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        } finally {
            close(session);
        }
    }

    public CommonResponse deleteStudentById(Integer studentId) throws Exception {
        Student student = (Student) findById(Student.class, studentId);
        if (ObjectUtils.isEmpty(student)) {
            return new CommonResponse(Boolean.FALSE, "Không tìm thấy sinh viên có id " + studentId);
        }
        delete(student);
        return new CommonResponse(Boolean.TRUE, "Xóa sinh viên thành công");
    }

}
