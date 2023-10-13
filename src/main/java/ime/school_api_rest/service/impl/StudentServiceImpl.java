package ime.school_api_rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.dto.SubjectStudentDto;
import ime.school_api_rest.entity.Student;
import ime.school_api_rest.entity.Subject;
import ime.school_api_rest.entity.SubjectStudent;
import ime.school_api_rest.entity.SubjectStudentId;
import ime.school_api_rest.mapper.StudentMapper;
import ime.school_api_rest.repository.StudentRepository;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.SubjectStudentRepository;
import ime.school_api_rest.service.StudentService;
import ime.school_api_rest.exception.*;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SubjectStudentRepository subjectStudentRepo;

	@Autowired
	private SubjectRepository subjectRepo;
	
	
	@Override
	public List<StudentDto> getAllStudent() {
		
		return StudentMapper.toListStudentDto(studentRepo.findAll());
		
	}

	@Override
	public StudentDto getStudentDtoById(Long id) {
		
		return StudentMapper.toStudentDto( studentRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException(id) ) );
	}

	@Override
	public void deleteStudentById(Long id) {
		
		Student studentFound = studentRepo.findById(id).orElseThrow( ()-> new ResourceNotFoundException(id) );
		
		if( studentFound.getSubjects() != null && !studentFound.getSubjects().isEmpty() ) throw new SubjectAssociatedException(id);
		
		studentRepo.deleteById(id);
		
	}

	@Override
	public StudentBasicDto createStudent(StudentBasicCreationDto student) {		
		
		return StudentMapper.fromStudentToStudentBasicDto(studentRepo.save(StudentMapper.fromStudentBasicCreationDtoToStudent(student)));
		
	}

	@Override
	public StudentBasicDto updateStudent(StudentBasicDto student) {
		
		Student studentFound = studentRepo.findById(student.getStudentId()).orElseThrow( ()-> new ResourceNotFoundException(student.getStudentId()) );
		studentFound.setName(student.getName());
		studentFound.setSurname(student.getSurname());
		
		return StudentMapper.fromStudentToStudentBasicDto(studentRepo.save(studentFound));
	}

	@Override
	public StudentDto addStudentToSubject(Long studentId, Long subjectId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new RequirementNotMetException(0l);
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, null);		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
	}

	@Override
	public StudentDto addStudentToSubjectWithMark(Long studentId, Long subjectId, Double averageGrade) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new RequirementNotMetException(0l);
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, averageGrade);		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
		
	}
	
	@Override
	public StudentDto addStudentToSubjectWithMark(SubjectStudentDto subjectStudentDto) {
		
		Subject subject = subjectRepo.findById(subjectStudentDto.getSubjectId()).orElseThrow( () -> new ResourceNotFoundException(subjectStudentDto.getSubjectId()));
		Student student = studentRepo.findById(subjectStudentDto.getStudentId()).orElseThrow( () -> new ResourceNotFoundException(subjectStudentDto.getStudentId()));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new RequirementNotMetException(0l);
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, subjectStudentDto.getAverageGrade());		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
	}
	
	@Override
	public StudentDto removeStudenFromSubject(Long studentId, Long subjectId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(),student.getStudentId());
		if ( subjectStudentRepo.existsById(ssId) ) throw new ResourceNotFoundException( student.getStudentId() );
		subjectStudentRepo.deleteById(ssId);
		
		return StudentMapper.toStudentDto(student);
	}
	
}
