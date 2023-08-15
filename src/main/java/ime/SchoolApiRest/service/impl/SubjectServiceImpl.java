package ime.SchoolApiRest.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.mapper.SubjectMapper;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.service.SubjectService;

import ime.SchoolApiRest.exception.*;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Override
	public Set<SubjectDto> getAllEagerSubjectDto() {
		
		return SubjectMapper.toListSubjectDto(subjectRepo.getAllEagerSubject());
	}

	@Override
	public SubjectBasicDto getSubjectDtoById(Long subjectId) {
		
		return SubjectMapper.toSubjectBasicDto(subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId)));
		
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
