package ime.SchoolApiRest.service.impl;

import java.util.Set;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.entity.*;
import ime.SchoolApiRest.mapper.SubjectMapper;
import ime.SchoolApiRest.repository.*;
import ime.SchoolApiRest.service.SubjectService;

import ime.SchoolApiRest.exception.*;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private SubjectStudentRepository subjectStudentRepo;
	
	@Override
	public Set<SubjectDto> getAllEagerSubjectDto() {
		
		return SubjectMapper.toListSubjectDto(subjectRepo.getAllEagerSubject());
	}

	@Override
	public SubjectBasicDto getSubjectBasicDtoById(Long subjectId) {
		
		return SubjectMapper.toSubjectBasicDto(subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId)));
		
	}

	@Override
	public void deleteSubjectById(Long subjectId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		if ( subject.getTeacher() != null ) throw new TeacherAssociatedException(subjectId);
		if ( subject.getStudents() != null && !subject.getStudents().isEmpty() ) throw new StudentAssociatedException(subjectId);
		subjectRepo.deleteById(subjectId);
		
	}

	@Override
	public SubjectBasicDto createSubject(SubjectBasicCreationDto sbcDto) {
		
		return SubjectMapper.toSubjectBasicDto(subjectRepo.save(SubjectMapper.dtoCreationToSubject(sbcDto)));
		
	}

	@Override
	public SubjectBasicDto updateSubject(Long subjectId, SubjectBasicDto sbDto) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		subject.setName(sbDto.getName());
		Subject subjectUpdated = subjectRepo.save(subject);
		
		return SubjectMapper.toSubjectBasicDto(subjectUpdated);
		
	}

	@Override
	public SubjectDto addTeacherToSubject(Long subjectId, Long teacherId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		subject.setTeacher(teacher);
		Subject subjectUpdated = subjectRepo.save(subject);
		
		return SubjectMapper.toSubjectDto(subjectUpdated);
		
	}

	@Override
	public SubjectDto addStudentToSubject(Long subjectId, Long studentId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		SubjectStudentId ssId = new SubjectStudentId(subject.getSubjectId(),student.getStudentId());
		SubjectStudent ss = new SubjectStudent(ssId,subject,student,null);
		
		if( subject.getStudents() == null ) {		
			subject.setStudents(new HashSet<>());
		}
		
		subject.getStudents().add(ss);
		student.getSubjects().add(ss);
		subjectStudentRepo.save(ss);
		Subject subjectUpdated = subjectRepo.save(subject);
		//Student studentUpdated = studentRepo.save(student);
		
		return SubjectMapper.toSubjectDto(subjectUpdated);
	}

	@Override
	public SubjectDto removeStudentFromSubject(Long subjectId, Long studentId) {
		
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( () -> new ResourceNotFoundException(subjectId));
		Student student = studentRepo.findById(studentId).orElseThrow( () -> new ResourceNotFoundException(studentId));
		
		return null;
	}
	
	

}
