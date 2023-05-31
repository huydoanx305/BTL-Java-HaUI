package com.hit.admission.mapper;

import com.hit.admission.dto.SubjectDTO;
import com.hit.admission.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 *
 * @author Huy Doan
 */
@Mapper
public interface SubjectMapper {

    SubjectDTO toSubjectDTO(Subject subject);

    Subject toSubject(SubjectDTO subjectDTO);
    
    void updateSubjectFromDTO(SubjectDTO subjectDTO, @MappingTarget Subject subject);
    
}
