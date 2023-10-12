package ime.school_api_rest.service;
import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.*;

import java.util.List;

@Service
public interface SubjectStudentService {

	List<SubjectStudentCuteDto>getAllSubjectStudent();
	
	SubjectStudentCuteDto getSubjectStudentCuteDtoById(Long subjectId, Long studentId);
	
	void deleteSubjectStudentById(Long subjectId, Long studentId);
	
	SubjectStudentCuteDto createSubjectStudent(SubjectStudentCreationDto sscDto);
	
	SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto);
	
}
