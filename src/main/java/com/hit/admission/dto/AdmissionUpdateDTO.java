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
public class AdmissionUpdateDTO {
    
    private Integer oldOrders;
    
    private Integer orders;
    
    private Integer studentId;
    
    private String block;
    
}
