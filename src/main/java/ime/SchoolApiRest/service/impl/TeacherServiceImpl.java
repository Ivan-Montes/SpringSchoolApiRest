package ime.SchoolApiRest.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ime.SchoolApiRest.dto.TeacherDto;
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

}
