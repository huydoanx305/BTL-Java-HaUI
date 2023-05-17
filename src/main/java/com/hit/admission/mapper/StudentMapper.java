package com.hit.admission.mapper;

import com.hit.admission.dto.SignUpRequest;
import com.hit.admission.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface StudentMapper {

    @Mappings({
        @Mapping(target = "citizenIdentityNumber", source = "citizenIdentityNumber"),
        @Mapping(target = "citizenIdentityIssueDate", source = "citizenIdentityIssueDate"),
        @Mapping(target = "citizenIdentityIssuedBy", source = "citizenIdentityIssuedBy"),
        @Mapping(target = "firstName", source = "firstName"),
        @Mapping(target = "lastName", source = "lastName"),
        @Mapping(target = "email", source = "email"),
        @Mapping(target = "phoneNumber", source = "phoneNumber")
    })
    Student signUpRequestToStudent(SignUpRequest signUpRequest);

}
