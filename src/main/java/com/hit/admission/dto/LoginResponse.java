package com.hit.admission.dto;

import com.hit.admission.constants.RoleConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Huy Doan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private Boolean status;

    private String message;

    private RoleConstant roleName;

}
