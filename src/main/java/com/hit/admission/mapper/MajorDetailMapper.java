package com.hit.admission.mapper;

import com.hit.admission.dto.MajorDetailDTO;
import com.hit.admission.model.MajorDetail;
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
public interface MajorDetailMapper {

    MajorDetail toMajorDetail(MajorDetailDTO majorDetailDTO);

    @Mapping(target = "id", ignore = true)
    void updateMajorDetailFromDTO(MajorDetailDTO majorDetailDTO, @MappingTarget MajorDetail majorDetail);

    default MajorDetailDTO objectToMajorDetailDto(Object[] object) {
        Integer id = (Integer) object[0];
        String code = (String) object[1];
        String name = (String) object[2];
        String blocks = (String) object[3];
        Float benchMark = (Float) object[4];
        Integer amountStudentReceived = (Integer) object[5];
        Integer majorId = (Integer) object[6];
        return new MajorDetailDTO(id, code, name, amountStudentReceived, benchMark, blocks, majorId);
    }

    default List<MajorDetailDTO> objectsToMajorDetailDtos(List<Object[]> objects) {
        List<MajorDetailDTO> results = new LinkedList<>();
        objects.forEach(object -> {
            results.add(objectToMajorDetailDto(object));
        });
        return results;
    }

}
