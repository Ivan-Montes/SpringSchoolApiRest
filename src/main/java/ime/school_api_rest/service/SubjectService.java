package ime.school_api_rest.service;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.SubjectBasicCreationDto;
import ime.school_api_rest.dto.SubjectBasicDto;
import ime.school_api_rest.dto.SubjectDto;

@Service
public interface SubjectService {

	Set<SubjectDto> getAllEagerSubjectDto();
	
	SubjectBasicDto getSubjectBasicDtoById(Long subjectId);
	
	void deleteSubjectById(Long subjectId);
	
	SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto);
	
	SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto);
	
	SubjectDto addTeacherToSubject(Long subjectId, Long teacherId);
	
	SubjectDto addStudentToSubject(Long subjectId, Long studentId);

	SubjectDto removeStudentFromSubject(Long subjectId, Long studentId);
}
