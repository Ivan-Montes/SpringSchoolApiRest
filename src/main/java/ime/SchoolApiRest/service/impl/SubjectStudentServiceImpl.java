package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectStudentDto;
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
	
	

}
