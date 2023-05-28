package com.hit.admission.controller;

import com.hit.admission.base.BaseDAO;
import com.hit.admission.constants.CommonConstant;
import com.hit.admission.constants.RoleConstant;
import com.hit.admission.dto.LoginResponse;
import com.hit.admission.dto.SignUpRequest;
import com.hit.admission.lib.security.BCryptPasswordEncoder;
import com.hit.admission.lib.security.PasswordEncoder;
import com.hit.admission.mapper.StudentMapper;
import com.hit.admission.mapper.UserMapper;
import com.hit.admission.model.Student;
import com.hit.admission.model.User;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Huy Doan
 */
public class AuthController extends BaseDAO {

    private final Logger logger = Logger.getLogger(AuthController.class);

    private final PasswordEncoder passwordEncoder;

    private final StudentMapper studentMapper;

    private final UserMapper userMapper;

    public AuthController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.studentMapper = Mappers.getMapper(StudentMapper.class);
        this.userMapper = Mappers.getMapper(UserMapper.class);
    }

    public LoginResponse login(String username, String password) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        LoginResponse loginResponse = new LoginResponse();
        try {
            NativeQuery<User> query = session.createNativeQuery("SELECT * FROM users WHERE username = :username", User.class);
            query.setParameter("username", username);
            List<User> users = query.getResultList();
            if (CollectionUtils.isEmpty(users)) {
                loginResponse.setStatus(Boolean.FALSE);
                loginResponse.setMessage("Tài khoản không tồn tại!");
                return loginResponse;
            }
            User user = users.get(0);
            tx.commit();
            if (passwordEncoder.matches(password, user.getPassword())) {
                loginResponse.setStatus(Boolean.TRUE);
                loginResponse.setMessage(CommonConstant.SUCCESS);
                loginResponse.setUser(userMapper.toUserDTO(user));
                return loginResponse;
            } else {
                loginResponse.setStatus(Boolean.FALSE);
                loginResponse.setMessage("Mật khẩu không chính xác!");
                return loginResponse;
            }
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            loginResponse.setStatus(Boolean.FALSE);
            loginResponse.setMessage("Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!");
            return loginResponse;
        } finally {
            close(session);
        }
    }

    public String signup(SignUpRequest signUpRequest) {
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        try {
            NativeQuery<User> queryUser = session.createNativeQuery("SELECT * FROM users WHERE username = :username", User.class);
            queryUser.setParameter("username", signUpRequest.getCitizenIdentityNumber());
            List<User> users = queryUser.getResultList();
            if (CollectionUtils.isNotEmpty(users)) {
                return "Số CMND/CCCD này đã được đăng ký!";
            }
            NativeQuery<Student> queryStudent = session.createNativeQuery("SELECT * FROM students WHERE email = :email", Student.class);
            queryStudent.setParameter("email", signUpRequest.getEmail());
            List<Student> students = queryStudent.getResultList();
            if (CollectionUtils.isNotEmpty(students)) {
                return "Email này đã được đăng ký!";
            }
            Student student = studentMapper.signUpRequestToStudent(signUpRequest);
            session.saveOrUpdate(student);
            User user = new User();
            user.setUsername(signUpRequest.getCitizenIdentityNumber());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRoleName(RoleConstant.ROLE_USER);
            user.setStudent(student);
            session.saveOrUpdate(user);
            tx.commit();
            return CommonConstant.SUCCESS;
        } catch (Exception e) {
            rollback(tx);
            logger.error(e.getMessage());
            e.printStackTrace();
            return "Hệ thống đã xảy ra lỗi. Vui lòng quay lại sau!";
        } finally {
            close(session);
        }
    }

}
