package com.hit.admission.mapper;

import com.hit.admission.dto.UserDTO;
import com.hit.admission.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface UserMapper {

    @Mappings({
        @Mapping(target = "studentId", source = "user.student.id")
    })
    UserDTO toUserDTO(User user);

}
