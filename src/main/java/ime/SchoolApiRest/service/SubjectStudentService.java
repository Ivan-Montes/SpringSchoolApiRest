package ime.SchoolApiRest.service;
import org.springframework.stereotype.Service;
import java.util.List;
import ime.SchoolApiRest.dto.*;

@Service
public interface SubjectStudentService {

	List<SubjectStudentDto>getAllSubjectStudent();
	
	
}
