package com.hit.admission.dto;

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
public class AdmissionCreateDTO {
    
    private Integer orders;
    
    private Integer studentId;
    
    private String majorCode;
    
    private String block;
    
}
