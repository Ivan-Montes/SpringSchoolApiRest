package ime.school_api_rest.service.impl;

import java.util.List;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.*;
import ime.school_api_rest.entity.Subject;
import ime.school_api_rest.entity.Teacher;
import ime.school_api_rest.exception.*;
import ime.school_api_rest.mapper.TeacherMapper;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.TeacherRepository;
import ime.school_api_rest.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepo;	

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Override
	public List<TeacherDto> getAllEagerTeachersDto() {		
		
		return TeacherMapper.listToTeacherDto(teacherRepo.findAllEager());
	}

	@Override
	public TeacherBasicDto getTeacherDtoById(Long teacherId) {
		
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		return TeacherMapper.toTeacherBasicDto(teacher);
		
	}

	@Override
	public void deleteTeacherById(Long teacherId) {
		
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		if ( teacher.getSubjects() != null && !teacher.getSubjects().isEmpty()) throw new SubjectAssociatedException(teacherId);
		teacherRepo.deleteById(teacherId);
		
	}

	@Override
	public TeacherBasicDto createTeacher(TeacherBasicCreationDto tbcDto) {
		
		return TeacherMapper.toTeacherBasicDto(teacherRepo.save(TeacherMapper.dtoCreationToTeacher(tbcDto)));
	}

	@Override
	public TeacherBasicDto updateTeacher(Long teacherId, TeacherBasicDto tbDto) {
		
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		teacher.setName(tbDto.getName());
		teacher.setSurname(tbDto.getSurname());
		Teacher teacherUpdated = teacherRepo.save(teacher);
		return TeacherMapper.toTeacherBasicDto(teacherUpdated);
	}

	@Override
	public TeacherDto addSubjectToTeacher(Long teacherId, Long subjectId) {
		
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( ()-> new ResourceNotFoundException(subjectId) );
		
		subject.setTeacher(teacher);
		if ( teacher.getSubjects() == null ) teacher.setSubjects( new HashSet<>() ); 
		teacher.getSubjects().add(subject);
		
		Teacher teacherUpdated = teacherRepo.save(teacher);
		return TeacherMapper.toTeacherDto(teacherUpdated);
	}

	@Override
	public TeacherDto removeSubjectFromTeacher(Long teacherId, Long subjectId) {
		
		Teacher teacher = teacherRepo.findById(teacherId).orElseThrow( ()-> new ResourceNotFoundException(teacherId) );
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( ()-> new ResourceNotFoundException(subjectId) );
		
		if( ( subject.getTeacher() != null && subject.getTeacher().getTeacherId().equals(teacher.getTeacherId() ) ) &&
			( teacher.getSubjects() != null && teacher.getSubjects().stream().anyMatch( s -> s.getSubjectId().equals( subject.getSubjectId() ) ) ) 
		) {
		
			subject.setTeacher(null);							
			teacher.getSubjects().remove(subject);
		
		}
		
		Teacher teacherUpdated = teacherRepo.save(teacher);
		
		return TeacherMapper.toTeacherDto(teacherUpdated);
	}

}
