package ime.SchoolApiRest.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.entity.Student;
import ime.SchoolApiRest.entity.Teacher;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

	@Mock
	private SubjectRepository subjectRepo;	
	
	@Mock
	private TeacherRepository teacherRepo;
	@Mock
	private StudentRepository studentRepo;
	
	@InjectMocks
	private SubjectServiceImpl subjectService;
	
	private Subject subjectTest;
	private SubjectBasicCreationDto sbcDtoTest; 
	private SubjectBasicDto sbDtoTest;
	private Teacher teacherTest;
	private Student studentTest;
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {
		subjectTest = new Subject();
		subjectTest.setSubjectId(1L);
		subjectTest.setName("101");
		subjectTest.setTeacher(null);
		subjectTest.setStudents(new HashSet<>());
		
		sbcDtoTest = SubjectBasicCreationDto.builder().name("101").build();
		
		sbDtoTest = SubjectBasicDto.builder().subjectId(1L).name("101").build();
		
		teacherTest = new Teacher();
		teacherTest.setTeacherId(1L);
		teacherTest.setName("Jane");
		teacherTest.setSurname("Doe");
		teacherTest.setSubjects(new HashSet<>());
		
		studentTest = new Student();
		studentTest.setStudentId(1L);
		studentTest.setName("Philip J");
		studentTest.setSurname("Fry");
		studentTest.setSubjects(new HashSet<>());
	}
	
	
	@Test
	public void subjectServiceImpl_getAllEagerSubjectDto_ReturSetSubjectDto() {
		
		List<Subject>subjects = List.of(subjectTest);
		doReturn(subjects).when(subjectRepo).getAllEagerSubject();
		
		Set<SubjectDto>subjectList = subjectService.getAllEagerSubjectDto();
		
		assertAll(
					()->Assertions.assertThat(subjectList).isNotNull(),
					()->Assertions.assertThat(subjectList).hasSizeGreaterThanOrEqualTo(1)
				);		
		verify(subjectRepo,times(1)).getAllEagerSubject();
		
	}
	
	@Test
	public void subjectServiceImpl_getSubjectDtoById_ReturnSubjectBasicDto() {
		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		
		SubjectBasicDto sbDto = subjectService.getSubjectBasicDtoById(Mockito.anyLong());
		
		assertAll(
					()->Assertions.assertThat(sbDto).isNotNull(),
					()->Assertions.assertThat(sbDto.getSubjectId()).isEqualTo(1L),
					()->Assertions.assertThat(sbDto.getName()).isEqualTo("101")
				);
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());

	}
	
	@Test
	public void subjectServiceImpl_deleteSubjectById_ReturnVoid() {
		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doNothing().when(subjectRepo).deleteById(Mockito.anyLong());
		
		subjectService.deleteSubjectById(Mockito.anyLong());
		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
		verify(subjectRepo, times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	public void subjectServiceImpl_createSubject_ReturnSubjectBasicDto() {
		
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectBasicDto sbDtoCreated = subjectService.createSubject(sbcDtoTest);
		
		assertAll(
					()->Assertions.assertThat(sbDtoCreated).isNotNull(),
					()->Assertions.assertThat(sbDtoCreated.getSubjectId()).isEqualTo(1L)
				
				);
		verify(subjectRepo, times(1)).save(Mockito.any(Subject.class));
	}

	@Test
	public void subjectServiceImpl_updateSubject_ReturnSubjectBasicDto() {
		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectBasicDto sbDtoUpdated = subjectService.updateSubject(Mockito.anyLong(), sbDtoTest);
		
		assertAll(
					()->Assertions.assertThat(sbDtoUpdated).isNotNull(),
					()->Assertions.assertThat(sbDtoUpdated.getSubjectId()).isEqualTo(1L)				
				);

		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
		verify(subjectRepo, times(1)).save(Mockito.any(Subject.class));
		
	}
	
	@Test
	public void subjectServiceImpl_addTeacherToSubject_ReturnSubjectDto() {

		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Teacher>optT = Optional.ofNullable(teacherTest);
		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(optT).when(teacherRepo).findById(Mockito.anyLong());
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectDto subjectDtoUpdated = subjectService.addTeacherToSubject(1L, Mockito.anyLong());
		
		assertAll(
					()->Assertions.assertThat(subjectDtoUpdated).isNotNull(),
					()->Assertions.assertThat(subjectDtoUpdated.getSubjectId()).isEqualTo(1L),
					()->Assertions.assertThat(subjectDtoUpdated.getTeacher()).isNotNull(),
					()->Assertions.assertThat(subjectDtoUpdated.getTeacher().getTeacherId()).isEqualTo(1L)
				);		
		verify(teacherRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).save(Mockito.any(Subject.class));
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	public void subjectServiceImpl_addStudentToSubject_ReturnSubjectDto() {
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Student>optStu = Optional.ofNullable(studentTest);
		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);		
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		//doNothing().when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectDto subjectDtoUpdated = subjectService.addStudentToSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(subjectDtoUpdated).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher().getTeacherId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).hasSize(1)
			);		
	verify(teacherRepo,times(1)).findById(Mockito.anyLong());
	verify(subjectRepo,times(1)).save(Mockito.any(Subject.class));
	verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		
	}
	
}
