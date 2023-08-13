package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Override
	public List<SubjectDto> getAllEagerSubjectDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectBasicDto getSubjectDtoById(Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
