package ime.SchoolApiRest.service;
import java.util.Set;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;

@Service
public interface SubjectService {

	Set<SubjectDto> getAllEagerSubjectDto();
	
	SubjectBasicDto getSubjectDtoById(Long subjectId);
	
	void deleteSubjectById(Long subjectId);
	
	SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto);
	
	SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto);
	
}
