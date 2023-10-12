package ime.school_api_rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.SubjectStudentCreationDto;
import ime.school_api_rest.dto.SubjectStudentCuteDto;
import ime.school_api_rest.entity.Student;
import ime.school_api_rest.entity.Subject;
import ime.school_api_rest.entity.SubjectStudent;
import ime.school_api_rest.entity.SubjectStudentId;
import ime.school_api_rest.exception.ResourceNotFoundException;
import ime.school_api_rest.mapper.SubjectStudentMapper;
import ime.school_api_rest.repository.StudentRepository;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.SubjectStudentRepository;
import ime.school_api_rest.service.SubjectStudentService;

@Service
public class SubjectStudentServiceImpl implements SubjectStudentService {

	@Autowired
	private SubjectStudentRepository subjectStudentRepo;
	
	@Autowired
	private SubjectRepository subjectRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Override
	public List<SubjectStudentCuteDto> getAllSubjectStudent() {
		
		return SubjectStudentMapper.toListSubjectStudentCuteDto(subjectStudentRepo.findAll());
		
	}

	@Override
	public SubjectStudentCuteDto getSubjectStudentCuteDtoById(Long subjectId, Long studentId) {
		
		SubjectStudent subjectStudent = subjectStudentRepo.findById(new SubjectStudentId(subjectId,studentId)).orElseThrow( ()-> new ResourceNotFoundException( subjectId ) );
		return SubjectStudentMapper.toSubjectStudentCuteDto(subjectStudent);
	}

	@Override
	public void deleteSubjectStudentById(Long subjectId, Long studentId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		subjectStudentRepo.deleteById(ssId);
		
	}

	@Override
	public SubjectStudentCuteDto createSubjectStudent(SubjectStudentCreationDto sscDto) {
		
		Subject subject = subjectRepo.findById(sscDto.getSubjectId()).orElseThrow( () -> new ResourceNotFoundException(sscDto.getSubjectId()));
		Student student = studentRepo.findById(sscDto.getStudentId()).orElseThrow( () -> new ResourceNotFoundException(sscDto.getStudentId()));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, sscDto.getAverageGrade());
		
		return SubjectStudentMapper.toSubjectStudentCuteDto(subjectStudentRepo.save(ss));
		
	}

	@Override
	public SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto) {

		subjectRepo.findById(sscDto.getSubjectId()).orElseThrow( () -> new ResourceNotFoundException(sscDto.getSubjectId()));
		studentRepo.findById(sscDto.getStudentId()).orElseThrow( () -> new ResourceNotFoundException(sscDto.getStudentId()));
		SubjectStudent subjectStudentFound = subjectStudentRepo.findById(new SubjectStudentId(sscDto.getSubjectId(),sscDto.getStudentId())).orElseThrow( ()-> new ResourceNotFoundException( sscDto.getStudentId() ) );
		subjectStudentFound.setAverageGrade(sscDto.getAverageGrade());
		
		return SubjectStudentMapper.toSubjectStudentCuteDto(subjectStudentRepo.save(subjectStudentFound));
	}
	
	

}
