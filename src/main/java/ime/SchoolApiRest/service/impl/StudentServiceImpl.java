package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.StudentBasicCreationDto;
import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.dto.SubjectStudentDto;
import ime.SchoolApiRest.entity.Student;
import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.entity.SubjectStudentId;
import ime.SchoolApiRest.mapper.StudentMapper;
import ime.SchoolApiRest.repository.StudentRepository;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.SubjectStudentRepository;
import ime.SchoolApiRest.service.StudentService;
import ime.SchoolApiRest.exception.*;

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
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new GeneralException();
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, null);		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
	}

	@Override
	public StudentDto addStudentToSubjectWithMark(Long studentId, Long subjectId, Double averageGrade) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new GeneralException();
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, averageGrade);		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
		
	}
	
	@Override
	public StudentDto addStudentToSubjectWithMark(SubjectStudentDto subjectStudentDto) {
		
		Subject subject = subjectRepo.findById(subjectStudentDto.getSubjectId()).orElseThrow( () -> new ResourceNotFoundException(subjectStudentDto.getSubjectId()));
		Student student = studentRepo.findById(subjectStudentDto.getStudentId()).orElseThrow( () -> new ResourceNotFoundException(subjectStudentDto.getStudentId()));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(), student.getStudentId());
		if ( subjectStudentRepo.findById(ssId).isPresent() ) throw new GeneralException();
		SubjectStudent ss = new SubjectStudent(ssId,subject,student, subjectStudentDto.getAverageGrade());		
		subjectStudentRepo.save(ss);		
		
		return StudentMapper.toStudentDto(student);
	}
	
	@Override
	public StudentDto removeStudentToSubject(Long studentId, Long subjectId) {
		// TODO Auto-generated method stub
		return null;
	}


}
