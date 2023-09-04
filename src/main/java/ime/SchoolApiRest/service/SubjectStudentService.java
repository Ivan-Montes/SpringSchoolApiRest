package ime.SchoolApiRest.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.SchoolApiRest.dto.*;

@Service
public interface SubjectStudentService {

	List<SubjectStudentCuteDto>getAllSubjectStudent();
	
	SubjectStudentCuteDto getSubjectStudentCuteDtoById(Long subjectId, Long studentId);
	
	void deleteSubjectStudentById(Long subjectId, Long studentId);
	
	SubjectStudentCuteDto createSubjectStudent(SubjectStudentCreationDto sscDto);
	
	SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto);
	
}
