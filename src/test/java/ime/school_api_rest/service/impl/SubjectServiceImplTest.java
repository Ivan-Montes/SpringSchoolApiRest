package ime.school_api_rest.service.impl;

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

import ime.school_api_rest.dto.SubjectBasicCreationDto;
import ime.school_api_rest.dto.SubjectBasicDto;
import ime.school_api_rest.dto.SubjectDto;
import ime.school_api_rest.dto.SubjectStudentDto;
import ime.school_api_rest.entity.Student;
import ime.school_api_rest.entity.Subject;
import ime.school_api_rest.entity.SubjectStudent;
import ime.school_api_rest.entity.SubjectStudentId;
import ime.school_api_rest.entity.Teacher;
import ime.school_api_rest.exception.ResourceNotFoundException;
import ime.school_api_rest.exception.StudentAssociatedException;
import ime.school_api_rest.exception.TeacherAssociatedException;
import ime.school_api_rest.repository.StudentRepository;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.SubjectStudentRepository;
import ime.school_api_rest.repository.TeacherRepository;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {

	@Mock
	private SubjectRepository subjectRepo;	
	
	@Mock
	private TeacherRepository teacherRepo;
	
	@Mock
	private StudentRepository studentRepo;
	
	@Mock
	private SubjectStudentRepository subjectStudentRepo;
	
	@InjectMocks
	private SubjectServiceImpl subjectService;
	
	private Subject subjectTest;
	private SubjectBasicCreationDto sbcDtoTest; 
	private SubjectBasicDto sbDtoTest;
	private Teacher teacherTest;
	private Student studentTest;
	private final long treintaYTres = 33L;
	
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
	void subjectServiceImpl_getAllEagerSubjectDto_ReturSetSubjectDto() {
		
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
	void subjectServiceImpl_getSubjectDtoById_ReturnSubjectBasicDto() {
		
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
	void subjectServiceImpl_getSubjectDtoById_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.getSubjectBasicDtoById( treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void subjectServiceImpl_deleteSubjectById_ReturnVoid() {
		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doNothing().when(subjectRepo).deleteById( Mockito.anyLong() );
		
		subjectService.deleteSubjectById(Mockito.anyLong());
		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
		verify(subjectRepo, times(1)).deleteById(Mockito.anyLong());
	}

	@Test
	void subjectServiceImpl_deleteSubjectById_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.deleteSubjectById( treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	@Test
	void subjectServiceImpl_deleteSubjectById_ReturnTeacherAssociatedException() {
		
		subjectTest.setTeacher(teacherTest);
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(TeacherAssociatedException.class,
				()->subjectService.deleteSubjectById( treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(TeacherAssociatedException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void subjectServiceImpl_deleteSubjectById_ReturnStudentAssociatedException() {
		
		subjectTest.getStudents().add(new SubjectStudent());
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(StudentAssociatedException.class,
				()->subjectService.deleteSubjectById( treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(StudentAssociatedException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void subjectServiceImpl_createSubject_ReturnSubjectBasicDto() {
		
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectBasicDto sbDtoCreated = subjectService.createSubject(sbcDtoTest);
		
		assertAll(
					()->Assertions.assertThat(sbDtoCreated).isNotNull(),
					()->Assertions.assertThat(sbDtoCreated.getSubjectId()).isEqualTo(1L)
				
				);
		verify(subjectRepo, times(1)).save(Mockito.any(Subject.class));
	}

	@Test
	void subjectServiceImpl_updateSubject_ReturnSubjectBasicDto() {
		
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
	void subjectServiceImpl_updateSubject_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.updateSubject( treintaYTres, sbDtoTest)
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
		
	@Test
	void subjectServiceImpl_addTeacherToSubject_ReturnSubjectDto() {

		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Teacher>optT = Optional.ofNullable(teacherTest);
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
	void subjectServiceImpl_addTeacherToSubject_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.addTeacherToSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}

	
	@Test
	void subjectServiceImpl_addTeacherToSubject_ReturnResourceNotFoundException2() {
		
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(teacherRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.addTeacherToSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());	
		verify(teacherRepo, times(1)).findById(Mockito.anyLong());
	}
		
	@Test
	void subjectServiceImpl_addStudentToSubject_ReturnSubjectDto() {
		
		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Student>optStu = Optional.ofNullable(studentTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(new SubjectStudent()).when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectDto subjectDtoUpdated = subjectService.addStudentToSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(subjectDtoUpdated).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher().getTeacherId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).hasSize(1),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).first().extracting(SubjectStudentDto::getSubjectId).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).first().extracting(SubjectStudentDto::getStudentId).isEqualTo(studentTest.getStudentId())
			);
	verify(subjectRepo,times(1)).findById(Mockito.anyLong());
	verify(studentRepo,times(1)).findById(Mockito.anyLong());
	verify(subjectStudentRepo,times(1)).save(Mockito.any(SubjectStudent.class));
	verify(subjectRepo,times(1)).save(Mockito.any(Subject.class));
	}
	
	@Test
	void subjectServiceImpl_addStudentToSubject_ReturnSubjectDto2() {
		subjectTest.setStudents(null);
		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Student>optStu = Optional.ofNullable(studentTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(new SubjectStudent()).when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		doReturn(subjectTest).when(subjectRepo).save(Mockito.any(Subject.class));
		
		SubjectDto subjectDtoUpdated = subjectService.addStudentToSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(subjectDtoUpdated).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher().getTeacherId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).hasSize(1),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).first().extracting(SubjectStudentDto::getSubjectId).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).first().extracting(SubjectStudentDto::getStudentId).isEqualTo(studentTest.getStudentId())
			);
	verify(subjectRepo,times(1)).findById(Mockito.anyLong());
	verify(studentRepo,times(1)).findById(Mockito.anyLong());
	verify(subjectStudentRepo,times(1)).save(Mockito.any(SubjectStudent.class));
	verify(subjectRepo,times(1)).save(Mockito.any(Subject.class));
	}
	@Test
	void subjectServiceImpl_addStudentToSubject_ReturnResourceNotFoundException() {		

		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.addStudentToSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	

	@Test
	void subjectServiceImpl_addStudentToSubject_ReturnResourceNotFoundException2() {		
		
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.addStudentToSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void subjectServiceImpl_removeStudentFromSubject_ReturnSubjectDto() {

		subjectTest.setTeacher(teacherTest);
		teacherTest.getSubjects().add(subjectTest);	
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		Optional<Student>optStu = Optional.ofNullable(studentTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doNothing().when(subjectStudentRepo).deleteById(Mockito.any(SubjectStudentId.class));
		
		SubjectDto subjectDtoUpdated = subjectService.removeStudentFromSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(subjectDtoUpdated).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectId()).isEqualTo(1L),
				()->Assertions.assertThat(subjectDtoUpdated.getTeacher()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).isNotNull(),
				()->Assertions.assertThat(subjectDtoUpdated.getSubjectStudents()).isEmpty()				
				);
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).deleteById(Mockito.any(SubjectStudentId.class));
	}
	

	@Test
	void subjectServiceImpl_removeStudentFromSubject_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.removeStudentFromSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());
	}
	

	@Test
	void subjectServiceImpl_removeStudentFromSubject_ReturnResourceNotFoundException2() {
		
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()->subjectService.removeStudentFromSubject(1L, treintaYTres )
				);
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);		
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());		
		verify(studentRepo, times(1)).findById(Mockito.anyLong());
	}
	
}