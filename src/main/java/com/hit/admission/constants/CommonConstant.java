package com.hit.admission.constants;

/**
 *
 * @author Huy Doan
 */
public class CommonConstant {

    public static final String SUCCESS = "SUCCESS";

    public static final String REGEX_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    
    public static final String REGEX_PHONE = "^\\d{9,11}$";
    
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$";
    
}
