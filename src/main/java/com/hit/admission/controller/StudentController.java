package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.dto.CommonResponse;
import com.hit.admission.dto.StudentDTO;
import com.hit.admission.mapper.StudentMapper;
import com.hit.admission.model.Student;
import com.hit.admission.utils.CurrentUserLogin;
import com.hit.admission.utils.ResourceUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mapstruct.factory.Mappers;

import java.io.File;
import java.util.List;

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

    public StudentDTO updateInfo(StudentDTO studentDTO) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            Student student = session.load(Student.class, CurrentUserLogin.user.getStudentId());
            studentMapper.updateStudentFromDTO(studentDTO, student);
            session.saveOrUpdate(student);
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

    public CommonResponse uploadAvatar(File file) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            String fileName = CurrentUserLogin.user.getStudentId() + "_" + CurrentUserLogin.user.getUsername(); 
            String pathAvatar = ResourceUtil.saveFile(fileName, "upload/avatar", file);
            Query query = session.createNativeQuery("UPDATE students SET avatar = :avatar WHERE id = :id");
            query.setParameter("avatar", pathAvatar);
            query.setParameter("id", CurrentUserLogin.user.getStudentId());
            query.executeUpdate();
            tx.commit();
            return new CommonResponse(true, pathAvatar);
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            return new CommonResponse(false, "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
        } finally {
            close(session);
        }
    }

}
