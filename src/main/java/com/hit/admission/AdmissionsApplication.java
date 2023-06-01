package com.hit.admission;

import com.hit.admission.view.Login;

/**
 *
 * @author Huy Doan
 */
public class AdmissionsApplication {

    public static void main(String[] args) {
        Login login = new Login();
        login.setLocationRelativeTo(login);
        login.setVisible(true);
    }

}
