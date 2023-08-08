package ime.SchoolApiRest.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ime.SchoolApiRest.mapper.TeacherMapper;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.service.TeacherService;

import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.dto.*;

import ime.SchoolApiRest.exception.*;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepo;	

	@Override
	public List<TeacherDto> getAllEagerTeachersDto() {		
		
		return TeacherMapper.ListToTeacherDto(teacherRepo.findAllEager());
	}

	@Override
	public TeacherBasicDto getTeacherDtoById(Long teacherId) {
		
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		return TeacherMapper.ToTeacherBasicDto(t);
		
	}

	@Override
	public void deleteTeacherById(Long teacherId) {
		
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		if ( t.getSubjects() != null && !t.getSubjects().isEmpty() ) throw new SubjectAssociatedException(teacherId);
		teacherRepo.deleteById(teacherId);
		
	}

	@Override
	public TeacherBasicDto createTeacher(TeacherBasicCreationDto tbcDto) {
		return TeacherMapper.ToTeacherBasicDto(teacherRepo.save(TeacherMapper.DtoCreationToTeacher(tbcDto)));
	}

	@Override
	public TeacherBasicDto updateTeacher(Long teacherId, TeacherBasicDto tbDto) {
		Teacher t = teacherRepo.findById(teacherId).orElseThrow(()-> new ResourceNotFoundException(teacherId));
		t.setName(tbDto.getName());
		t.setSurname(tbDto.getSurname());
		Teacher teacherUpdated = teacherRepo.save(t);
		return TeacherMapper.ToTeacherBasicDto(teacherUpdated);
	}

}
