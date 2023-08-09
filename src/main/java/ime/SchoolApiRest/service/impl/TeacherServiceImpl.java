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
		
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		return TeacherMapper.toTeacherBasicDto(t);
		
	}

	@Override
	public void deleteTeacherById(Long teacherId) {
		
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		if ( t.getSubjects() != null && !t.getSubjects().isEmpty() ) throw new SubjectAssociatedException(teacherId);
		teacherRepo.deleteById(teacherId);
		
	}

	@Override
	public TeacherBasicDto createTeacher(TeacherBasicCreationDto tbcDto) {
		return TeacherMapper.toTeacherBasicDto(teacherRepo.save(TeacherMapper.dtoCreationToTeacher(tbcDto)));
	}

	@Override
	public TeacherBasicDto updateTeacher(Long teacherId, TeacherBasicDto tbDto) {
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		t.setName(tbDto.getName());
		t.setSurname(tbDto.getSurname());
		Teacher teacherUpdated = teacherRepo.save(t);
		return TeacherMapper.toTeacherBasicDto(teacherUpdated);
	}

	@Override
	public TeacherDto addSubjectToTeacher(Long teacherId, Long subjectId) {
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		Subject subject = subjectRepo.findById(subjectId).orElseThrow( ()-> new ResourceNotFoundException(subjectId));
		
		subject.setTeacher(t);
		if ( t.getSubjects() == null ) t.setSubjects(new HashSet<>()); 
		t.getSubjects().add(subject);
		
		Teacher teacherUpdated = teacherRepo.save(t);
		return TeacherMapper.toTeacherDto(teacherUpdated);
	}

	@Override
	public void removeSubjectFromTeacher(Long teacherId, Long subjectId) {
		// TODO Auto-generated method stub
		
	}

}
