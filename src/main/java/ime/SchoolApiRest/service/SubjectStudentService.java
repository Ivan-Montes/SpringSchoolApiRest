package ime.SchoolApiRest.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.SchoolApiRest.dto.*;
import ime.SchoolApiRest.entity.SubjectStudentId;

@Service
public interface SubjectStudentService {

	List<SubjectStudentDto>getAllSubjectStudent();
	
	SubjectStudentCuteDto getSubjectStudentCuteDtoById(Long subjectId, Long studentId);
	
	void deleteSubjectStudentById(SubjectStudentId subjectStudentId);
	
	SubjectStudentCuteDto createSubjectStudent(SubjectStudentCreationDto sscDto);
	
	SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto);
	
}
