package ime.SchoolApiRest.service.impl;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class TeacherServiceImplTest {

	@Mock
	private TeacherRepository teacherRepo;
	
	@InjectMocks
	private TeacherServiceImpl teacherService;
	
	private Teacher teacherTest;
	private TeacherBasicCreationDto tbcDto;
	
	@BeforeEach
	public void createDiferentTeachers() {
		teacherTest = new Teacher();
		teacherTest.setTeacherId(1L);
		teacherTest.setName("Jane");
		teacherTest.setSurname("Doe");
		teacherTest.setSubjects(new HashSet<>());
		
		tbcDto = new TeacherBasicCreationDto();
		tbcDto.setName("John");
		tbcDto.setSurname("Doe");
	}
	
	@Test
	@DisplayName("Test for getAllEagerTeachersDto method")
	@Order(1)
	public void TeacherServiceImpl_getAllEagerTeachersDto_ReturnListTeacherDto() {
		
		List<Teacher>teachers = List.of(Mockito.mock(Teacher.class));
		doReturn(teachers).when(teacherRepo).findAllEager();
		
		List<TeacherDto>list = teacherService.getAllEagerTeachersDto();
		
		assertAll(
					()->Assertions.assertThat(list).isNotNull(),
					()->Assertions.assertThat(list).isNotEmpty()
				);		
		verify(teacherRepo, times(1)).findAllEager();
		
	}
	
	@Test
	@DisplayName("Test for getTeacherDtoById method")
	@Order(2)
	public void TeacherServiceImpl_getTeacherDtoById_ReturnTeacherDto() {
		
		Teacher teacher = Mockito.mock(Teacher.class);	
		Optional<Teacher>opt = Optional.of(teacher);
		doReturn(opt).when(teacherRepo).findById(Mockito.anyLong());
		
		TeacherBasicDto tbd = teacherService.getTeacherDtoById(Mockito.anyLong());
		
		assertAll(
					()->Assertions.assertThat(tbd).isNotNull()
				);		
		verify(teacherRepo, times(1)).findById(Mockito.anyLong());
		
	}
	
	@Test
	@DisplayName("Test for deleteTeacher method")
	@Order(3)
	public void TeacherServiceImpl_deteleTeacher_ReturnVoid() {
		
		Optional<Teacher>opt = Optional.ofNullable(teacherTest);
		doReturn(opt).when(teacherRepo).findById(Mockito.anyLong());
		doNothing().when(teacherRepo).deleteById(Mockito.anyLong());
		
		teacherService.deleteTeacherById(Mockito.anyLong());
		
		verify(teacherRepo, times(1)).findById(Mockito.anyLong());
		verify(teacherRepo, times(1)).deleteById(Mockito.anyLong());		
		
	}
	
	@Test
	@DisplayName("Test for createTeacher method")
	@Order(4)
	public void TeacherServiceImpl_createTeacher_ReturnTeacherBasicDto() {
		
		doReturn(teacherTest).when(teacherRepo).save(Mockito.any(Teacher.class));
		
		TeacherBasicDto tbDto = teacherService.createTeacher(tbcDto);
		
		assertAll(
					()->Assertions.assertThat(tbDto).isNotNull(),
					()->Assertions.assertThat(tbDto.getTeacherId()).isEqualTo(1L)
				);
		
		verify(teacherRepo,times(1)).save(Mockito.any(Teacher.class));
	}
	
}