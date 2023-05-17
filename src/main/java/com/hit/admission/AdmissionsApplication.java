package com.hit.admission;

import com.hit.admission.utils.SessionUtil;
import com.hit.admission.view.Login;
import org.hibernate.Session;

/**
 *
 * @author Huy Doan
 */
public class AdmissionsApplication {

    public static void main(String[] args) {
        Session session = SessionUtil.getSession();
        SessionUtil.close(session);
        Login login = new Login();
        login.setLocationRelativeTo(login);
        login.setVisible(true);
    }

}
