package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectStudentCreationDto;
import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.dto.SubjectStudentDto;
import ime.SchoolApiRest.entity.SubjectStudentId;
import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.exception.ResourceNotFoundException;
import ime.SchoolApiRest.mapper.SubjectStudentMapper;
import ime.SchoolApiRest.repository.SubjectStudentRepository;
import ime.SchoolApiRest.service.SubjectStudentService;

@Service
public class SubjectStudentServiceImpl implements SubjectStudentService {

	@Autowired
	private SubjectStudentRepository subjectStudentRepo;
	
	@Override
	public List<SubjectStudentDto> getAllSubjectStudent() {
		
		return SubjectStudentMapper.toListSubjectStudentDto(subjectStudentRepo.findAll());
		
	}

	@Override
	public SubjectStudentCuteDto getSubjectStudentCuteDtoById(Long subjectId, Long studentId) {
		
		SubjectStudent subjectStudent = subjectStudentRepo.findById(new SubjectStudentId(subjectId,studentId)).orElseThrow( ()-> new ResourceNotFoundException( subjectId ) );
		return SubjectStudentMapper.toSubjectStudentCuteDto(subjectStudent);
	}

	@Override
	public void deleteSubjectStudentById(SubjectStudentId subjectStudentId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubjectStudentCuteDto createSubjectStudent(SubjectStudentCreationDto sscDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
