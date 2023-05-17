package com.hit.admission.mapper;

import com.hit.admission.dto.MajorDTO;
import com.hit.admission.dto.SignUpRequest;
import com.hit.admission.model.Student;
import java.util.LinkedList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface MajorMapper {

    default MajorDTO objectToMajorDto(Object[] object) {
        Integer id = (Integer) object[0];
        String code = (String) object[1];
        String name = (String) object[2];
        String blocks = "A00, A01";
        Float benchMark = (Float) object[3];
        Integer amountStudentReceived = (Integer) object[4];
        return new MajorDTO(id, code, name, amountStudentReceived, benchMark, blocks);
    }
    
    default List<MajorDTO> objectsToMajorDtos(List<Object[]> objects) {
        List<MajorDTO> results = new LinkedList<>();
        for(Object[] object : objects) {
            results.add(objectToMajorDto(object));
        }
        return results;
    }

}
