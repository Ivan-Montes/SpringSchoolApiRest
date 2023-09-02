package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectStudentCreationDto;
import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.entity.SubjectStudentId;
import ime.SchoolApiRest.entity.Student;
import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.exception.ResourceNotFoundException;
import ime.SchoolApiRest.mapper.SubjectStudentMapper;
import ime.SchoolApiRest.repository.StudentRepository;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.SubjectStudentRepository;
import ime.SchoolApiRest.service.SubjectStudentService;

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
		SubjectStudent ss = new SubjectStudent(ssId,subject,student,sscDto.getAverageGrade());
		
		return SubjectStudentMapper.toSubjectStudentCuteDto(subjectStudentRepo.save(ss));
		
	}

	@Override
	public SubjectStudentCuteDto updateSubjectStudent(SubjectStudentCreationDto sscDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
