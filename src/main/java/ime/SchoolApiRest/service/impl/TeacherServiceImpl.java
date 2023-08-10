package ime.SchoolApiRest.service.impl;

import java.util.List;
import java.util.HashSet;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.mapper.TeacherMapper;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.service.TeacherService;
import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.dto.*;

import ime.SchoolApiRest.exception.*;

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
		
		if ( subject.getTeacher() != null && subject.getTeacher().getTeacherId() == teacher.getTeacherId() ) {
			
			if ( teacher.getSubjects() != null && teacher.getSubjects().stream().anyMatch( (s)->s.getSubjectId() == subject.getSubjectId()) ) {

				subject.setTeacher(null);							
				teacher.getSubjects().remove(subject);
				
			}
		}
		
		
		Teacher teacherUpdated = teacherRepo.save(teacher);
		
		return TeacherMapper.toTeacherDto(teacherUpdated);
	}

}
