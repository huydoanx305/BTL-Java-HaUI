package com.hit.admission.mapper;

import com.hit.admission.dto.MajorDTO;
import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.model.Major;
import java.util.LinkedList;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface MajorMapper {
    
    @Mapping(target = "blocks", ignore = true)
    MajorDTO toMajorDTO(Major major);
    
    List<MajorDTO> toMajorDTOs(List<Major> major);
    
    @Mapping(target = "blocks", ignore = true)
    Major toMajor(MajorDTO majorDTO);
    
    List<Major> toMajors(List<MajorDTO> major);
    
    @Mapping(target = "blocks", ignore = true)
    void updateMajorFromDTO(MajorDTO majorDTO, @MappingTarget Major major);
    
    default MajorDTO objectToMajorDto(Object[] object) {
        Integer id = (Integer) object[0];
        String code = (String) object[1];
        String name = (String) object[2];
        String blocks = (String) object[3];
        return new MajorDTO(id, code, name, blocks);
    }
    
    default List<MajorDTO> objectsToMajorDtos(List<Object[]> objects) {
        List<MajorDTO> results = new LinkedList<>();
        objects.forEach(object -> {
            results.add(objectToMajorDto(object));
        });
        return results;
    }

    default MajorDetailDTO objectToMajorDetailDto(Object[] object) {
        Integer id = (Integer) object[0];
        String code = (String) object[1];
        String name = (String) object[2];
        String blocks = (String) object[3];
        Float benchMark = (Float) object[4];
        Integer amountStudentReceived = (Integer) object[5];
        return new MajorDetailDTO(id, code, name, amountStudentReceived, benchMark, blocks);
    }

    default List<MajorDetailDTO> objectsToMajorDetailDtos(List<Object[]> objects) {
        List<MajorDetailDTO> results = new LinkedList<>();
        objects.forEach(object -> {
            results.add(objectToMajorDetailDto(object));
        });
        return results;
    }

}
