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
public class UserDTO {
    
    private Integer id;

    private String username;

    private RoleConstant roleName;
    
    private Integer studentId;
    
}
