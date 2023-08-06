package ime.SchoolApiRest.service.impl;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.SchoolApiRest.entity.*;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.dto.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

	@Mock
	private TeacherRepository teacherRepo;
	
	@InjectMocks
	private TeacherServiceImpl teacherService;
	
	@Test
	public void TeacherServiceImpl_getAllEagerTeachersDto_ReturnListTeacherDto() {
		
		List<Teacher>teachers = List.of(Mockito.mock(Teacher.class));
		doReturn(teachers).when(teacherRepo).findAllEager();
		
		List<TeacherDto>list = teacherService.getAllEagerTeachersDto();
		
		Assertions.assertThat(list).isNotNull();
		Assertions.assertThat(list).isNotEmpty();
		verify(teacherRepo, times(1)).findAllEager();
		
	}
	
	@Test
	public void TeacherServiceImpl_getTeacherDtoById_ReturnTeacherDto() {
		
		Teacher teacher = Mockito.mock(Teacher.class);	
		Optional<Teacher>opt = Optional.of(teacher);
		doReturn(opt).when(teacherRepo).findById(Mockito.anyLong());
		
		TeacherBasicDto tbd = teacherService.getTeacherDtoById(Mockito.anyLong());
		
		Assertions.assertThat(tbd).isNotNull();
		verify(teacherRepo, times(1)).findById(Mockito.anyLong());
		
	}
	
}