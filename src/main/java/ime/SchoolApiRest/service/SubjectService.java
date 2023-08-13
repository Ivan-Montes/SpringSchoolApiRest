package ime.SchoolApiRest.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;

@Service
public interface SubjectService {

	List<SubjectDto> getAllEagerSubjectDto();
	
	SubjectBasicDto getSubjectDtoById(Long subjectId);
	
	void deleteSubjectById(Long subjectId);
	
	SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto);
	
	SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto);
	
}
