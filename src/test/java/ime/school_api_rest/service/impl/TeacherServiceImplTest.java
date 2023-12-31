package ime.school_api_rest.service.impl;


import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
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

import ime.school_api_rest.dto.*;
import ime.school_api_rest.entity.*;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.TeacherRepository;
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
	
	@Mock
	private SubjectRepository subjectRepo;
	
	@InjectMocks
	private TeacherServiceImpl teacherService;
	
	private Teacher teacherTest;
	private TeacherBasicCreationDto tbcDto;
	private TeacherBasicDto tbDto;
	private Subject subjectTest;
	
	@BeforeEach
	public void createDiferentTeachers() {
		teacherTest = new Teacher();
		teacherTest.setTeacherId(1L);
		teacherTest.setName("Jane");
		teacherTest.setSurname("Doe");
		teacherTest.setSubjects(new HashSet<>());
		
		tbcDto = TeacherBasicCreationDto.builder().name("John").surname("Doe").build();		
		tbDto = TeacherBasicDto.builder().teacherId(1L).name("Mrs").surname("Smith").build();
		
		subjectTest = new Subject();
		subjectTest.setSubjectId(1L);
		subjectTest.setName("Street University");
	}
	
	@Test
	@DisplayName("Test for getAllEagerTeachersDto method")
	@Order(1)
	void TeacherServiceImpl_getAllEagerTeachersDto_ReturnListTeacherDto() {
		
		List<Teacher>teachers = List.of(Mockito.mock(Teacher.class));
		doReturn(teachers).when(teacherRepo).findAllEager();
		
		List<TeacherDto>list = teacherService.getAllEagerTeachersDto();
		
		assertAll(
					()->Assertions.assertThat(list).isNotNull(),
					()->Assertions.assertThat(list).isNotEmpty(),
					()->Assertions.assertThat(list).hasSize(1)
				);		
		verify(teacherRepo, times(1)).findAllEager();
		
	}
	
	@Test
	@DisplayName("Test for getTeacherDtoById method")
	@Order(2)
	void TeacherServiceImpl_getTeacherDtoById_ReturnTeacherDto() {
		
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
	void TeacherServiceImpl_deteleTeacher_ReturnVoid() {
		
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
	void TeacherServiceImpl_createTeacher_ReturnTeacherBasicDto() {
		
		doReturn(teacherTest).when(teacherRepo).save(Mockito.any(Teacher.class));
		
		TeacherBasicDto tbDto = teacherService.createTeacher(tbcDto);
		
		assertAll(
					()->Assertions.assertThat(tbDto).isNotNull(),
					()->Assertions.assertThat(tbDto.getTeacherId()).isEqualTo(1L)
				);
		
		verify(teacherRepo,times(1)).save(Mockito.any(Teacher.class));
	}
	
	@Test
	@DisplayName("Test for updateTeacher method")
	@Order(5)
	void TeacherServiceImpl_updateTeacher_ReturnTeacherBasicDto() {
		Optional<Teacher>opt = Optional.ofNullable(teacherTest);
		doReturn(opt).when(teacherRepo).findById(Mockito.anyLong());
		doReturn(teacherTest).when(teacherRepo).save(Mockito.any(Teacher.class));
		
		TeacherBasicDto tbDtoUpdated = teacherService.updateTeacher(tbDto.getTeacherId(), tbDto);
		
		assertAll(
					()->Assertions.assertThat(tbDtoUpdated).isNotNull(),
					()->Assertions.assertThat(tbDtoUpdated.getTeacherId()).isEqualTo(1L),
					()->Assertions.assertThat(tbDtoUpdated.getSurname()).isEqualTo("Smith")
				);
		verify(teacherRepo,times(1)).findById(Mockito.anyLong());
		verify(teacherRepo,times(1)).save(Mockito.any(Teacher.class));
	}
	
	@Test
	@DisplayName("Test for addSubjectToTeacher method")
	@Order(6)
	void TeacherServiceImpl_addSubjectToTeacher_ReturnTeacherDto() {
		Optional<Teacher>optT = Optional.ofNullable(teacherTest);
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optT).when(teacherRepo).findById(Mockito.anyLong());
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(teacherTest).when(teacherRepo).save(Mockito.any(Teacher.class));
		
		TeacherDto tDto = teacherService.addSubjectToTeacher(1L, Mockito.anyLong());
		//TeacherDto tDto = teacherService.addSubjectToTeacher(Mockito.anyLong(), Mockito.anyLong()); //Find out why fails
		
		assertAll(
					()->Assertions.assertThat(tDto).isNotNull(),
					()->Assertions.assertThat(tDto.getTeacherId()).isEqualTo(1L),
					()->Assertions.assertThat(tDto.getName()).isEqualTo("Jane"),
					()->Assertions.assertThat(tDto.getSurname()).isEqualTo("Doe"),
					()->Assertions.assertThat(tDto.getSubjects()).isNotEmpty(),
					()->Assertions.assertThat(tDto.getSubjects()).first().extracting(SubjectBasicDto::getSubjectId).isEqualTo(1L),
					()->Assertions.assertThat(tDto.getSubjects()).first().extracting(SubjectBasicDto::getName).isEqualTo("Street University")
				);		
		verify(teacherRepo,times(1)).findById(Mockito.anyLong());
		verify(teacherRepo,times(1)).save(Mockito.any(Teacher.class));
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		
	}
	
	@Test
	@DisplayName("Test for removeSubjectFromTeacher method")
	@Order(7)
	void TeacherServiceImpl_removeSubjectFromTeacher_ReturnTeacherDto() {
		
		teacherTest.getSubjects().add(subjectTest);
		subjectTest.setTeacher(teacherTest);
		Optional<Teacher>optT = Optional.ofNullable(teacherTest);
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optT).when(teacherRepo).findById(Mockito.anyLong());
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(teacherTest).when(teacherRepo).save(Mockito.any(Teacher.class));
		
		TeacherDto tDto = teacherService.removeSubjectFromTeacher(1L, Mockito.anyLong());
		
		Condition<TeacherDto>conditionTeacherSurname = new Condition<>( t -> t.getSurname().equals(teacherTest.getSurname()), "**Condition Teacher Surname \"Doe\" ");
		Condition<SubjectBasicDto>conditionSubjectId = new Condition<>( s -> s.getSubjectId() == subjectTest.getSubjectId(), "**Condition SubjectId 1L");
		Condition<SubjectBasicDto>conditionSubjectName = new Condition<>( s-> s.getName().equals("Street University"), "**Condition SubjectName: Street University");
		assertAll(
					()->Assertions.assertThat(tDto.getTeacherId()).isEqualTo(teacherTest.getTeacherId()),
					()->Assertions.assertThat(tDto).is(conditionTeacherSurname),
					()->Assertions.assertThat(tDto.getSubjects()).haveExactly(0, conditionSubjectName),
					()->Assertions.assertThat(tDto.getSubjects()).doNotHave(conditionSubjectId),
					()->Assertions.assertThat(tDto.getSubjects()).doNotHave(conditionSubjectName)
				
				);
		verify(teacherRepo,times(1)).findById(Mockito.anyLong());
		verify(teacherRepo,times(1)).save(Mockito.any(Teacher.class));
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());		
		
	}
}