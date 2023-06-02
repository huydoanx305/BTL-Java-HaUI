package com.hit.admission.mapper;

import com.hit.admission.dto.AdmissionResultDTO;
import java.util.LinkedList;
import java.util.List;
import org.mapstruct.Mapper;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface AdmissionMapper {

    default AdmissionResultDTO objectToAdmissionResultDto(Object[] object) {
        String lastName = (String) object[0];
        String firstName = (String) object[1];
        String orderNumber = (String) object[2];
        String citizenIdentityNumber = (String) object[3];
        String email = (String) object[4];
        String phoneNumber = (String) object[5];
        String gender = (String) object[6];
        String address = (String) object[7];
        Integer orders = (Integer) object[8];
        String block = (String) object[9];
        Float totalScore = (Float) object[10];
        return new AdmissionResultDTO(lastName, firstName, orderNumber, citizenIdentityNumber,
                email, phoneNumber, gender, address, orders, block, totalScore);
    }

    default List<AdmissionResultDTO> objectsToAdmissionResultDtos(List<Object[]> objects) {
        List<AdmissionResultDTO> results = new LinkedList<>();
        objects.forEach(object -> {
            results.add(objectToAdmissionResultDto(object));
        });
        return results;
    }

}
