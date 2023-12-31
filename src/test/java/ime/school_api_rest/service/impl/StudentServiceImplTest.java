package ime.school_api_rest.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.dto.SubjectStudentDto;
import ime.school_api_rest.entity.Student;
import ime.school_api_rest.entity.Subject;
import ime.school_api_rest.entity.SubjectStudent;
import ime.school_api_rest.entity.SubjectStudentId;
import ime.school_api_rest.exception.RequirementNotMetException;
import ime.school_api_rest.exception.ResourceNotFoundException;
import ime.school_api_rest.exception.SubjectAssociatedException;
import ime.school_api_rest.repository.StudentRepository;
import ime.school_api_rest.repository.SubjectRepository;
import ime.school_api_rest.repository.SubjectStudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

	@Mock
	private StudentRepository studentRepo;

	@Mock
	private SubjectRepository subjectRepo;	

	@Mock
	private SubjectStudentRepository subjectStudentRepo;
	
	@InjectMocks
	private StudentServiceImpl studentService;

	private Student studentTest;
	private String nameStu = "Philip J";
	private String surnameStu = "Fry";
	private StudentBasicDto sbDtoTest;
	private StudentBasicCreationDto sbcDtoTest;
	private Subject subjectTest;
	private SubjectStudent subjectStudentTest;
	private SubjectStudentDto ssDtoTest;
	private Double mark9 = 9.9;
	private final long treintaYTres = 33L;
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {
		
		studentTest = new Student();
		studentTest.setStudentId(1L);
		studentTest.setName(nameStu);
		studentTest.setSurname(surnameStu);
		studentTest.setSubjects(new HashSet<>());
		
		sbDtoTest = StudentBasicDto.builder()
							.studentId(1L)
							.name(nameStu)
							.surname(surnameStu)
							.build();
		
		sbcDtoTest = StudentBasicCreationDto.builder()
										.name(nameStu)
										.surname(surnameStu)
										.build();
				

		subjectTest = new Subject();
		subjectTest.setSubjectId(1L);
		subjectTest.setName("101");
		subjectTest.setTeacher(null);
		subjectTest.setStudents(new HashSet<>());		
		

		subjectStudentTest = new SubjectStudent();
		subjectStudentTest.setId(new SubjectStudentId(1L,1L));
		subjectStudentTest.setStudent(null);
		subjectStudentTest.setSubject(null);
		subjectStudentTest.setAverageGrade(mark9);
		
		ssDtoTest = SubjectStudentDto.builder()
				.studentId(1L)
				.subjectId(1L)
				.averageGrade(mark9)
				.build();
	}
	
	@Test
	void studentServiceImpl_getAllStudent_ReturnList() {
		
		List<Student>students = List.of(studentTest);
		doReturn(students).when(studentRepo).findAll();
		
		List<StudentDto>studentsFound = studentService.getAllStudent();
		
		assertAll(
				()->Assertions.assertThat(studentsFound).isNotNull(),
				()->Assertions.assertThat(studentsFound).isNotEmpty(),
				()->Assertions.assertThat(studentsFound).hasSize(1),
				()->Assertions.assertThat(studentsFound.get(0).getName()).isEqualTo(nameStu),
				()->Assertions.assertThat(studentsFound).first().extracting(StudentDto::getStudentId).isEqualTo(1L)
				);
		verify(studentRepo,times(1)).findAll();
		
	}

	@Test
	void studentServiceImpl_getStudentDtoById_ReturnStudent() {
		
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		
		StudentDto studentDto = studentService.getStudentDtoById(Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(studentDto).isNotNull(),
				()->Assertions.assertThat(studentDto.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(studentDto.getSurname()).isEqualTo(surnameStu)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		
	}
	
	@Test
	void studentServiceImpl_getStudentDtoById_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows( ResourceNotFoundException.class,
				() -> studentService.getStudentDtoById( treintaYTres ) );
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)	
		);		
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_deleteStudentById_ReturnVoid() {
		
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doNothing().when(studentRepo).deleteById(Mockito.anyLong());
		
		studentService.deleteStudentById(Mockito.anyLong());
		
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_deleteStudentById_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()-> studentService.deleteStudentById( treintaYTres ));
		
		assertAll(
				()-> Assertions.assertThat(ex).isNotNull(),
				()-> Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_deleteStudentById_ReturnSubjectAssociatedException() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(SubjectAssociatedException.class,
				()-> studentService.deleteStudentById( treintaYTres ));
		
		assertAll(
				()-> Assertions.assertThat(ex).isNotNull(),
				()-> Assertions.assertThat(ex.getClass()).isEqualTo(SubjectAssociatedException.class)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
		
	@Test
	void studentServiceImpl_createStudent_ReturnStudentBasicDto() {
		
		doReturn(studentTest).when(studentRepo).save(Mockito.any(Student.class));
		
		StudentBasicDto sbDtoCreated = studentService.createStudent(sbcDtoTest);
		
		assertAll(
				()->Assertions.assertThat(sbDtoCreated).isNotNull(),
				()->Assertions.assertThat(sbDtoCreated.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(sbDtoCreated.getSurname()).isEqualTo(surnameStu)
				);
		verify(studentRepo,times(1)).save(Mockito.any(Student.class));
	}
	
	@Test
	void studentServiceImpl_updateStudent_ReturnStudentBasicDto() {
		
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(studentTest).when(studentRepo).save(Mockito.any(Student.class));
		
		StudentBasicDto sbDtoCreated = studentService.updateStudent(sbDtoTest);
		
		assertAll(
				()->Assertions.assertThat(sbDtoCreated).isNotNull(),
				()->Assertions.assertThat(sbDtoCreated.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(sbDtoCreated.getSurname()).isEqualTo(surnameStu)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).save(Mockito.any(Student.class));
		
	}
	
	@Test
	void studentServiceImpl_updateStudent_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				() -> studentService.updateStudent(sbDtoTest));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_addStudentToSubject_ReturnStudentDto() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		Optional<Subject>optSub = Optional.ofNullable(subjectTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(optSub).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));		
		doReturn(subjectStudentTest).when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		
		StudentDto studentModified = studentService.addStudentToSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(studentModified).isNotNull(),
				()->Assertions.assertThat(studentModified.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSurname()).isEqualTo(surnameStu),
				()->Assertions.assertThat(studentModified.getName()).isEqualTo(nameStu),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).hasSize(1),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getStudentId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getSubjectId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getAverageGrade).isEqualTo(mark9)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).findById(Mockito.any(SubjectStudentId.class));	
		verify(subjectStudentRepo,times(1)).save(Mockito.any(SubjectStudent.class));
	}
	
	@Test
	void studentServiceImpl_addStudentToSubject_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()-> studentService.addStudentToSubject(1L, treintaYTres ));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
	}

	@Test
	void studentServiceImpl_addStudentToSubject_ReturnResourceNotFoundException2() {

		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()-> studentService.addStudentToSubject(1L,  treintaYTres ));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_addStudentToSubject_ReturnRequirementNotMetException() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		Optional<Subject>optSub = Optional.ofNullable(subjectTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(optSub).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.ofNullable(subjectStudentTest)).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));
		
		Exception ex = assertThrows(RequirementNotMetException.class,
				()-> studentService.addStudentToSubject(1L,  treintaYTres ));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(RequirementNotMetException.class)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).findById(Mockito.any(SubjectStudentId.class));
	}
	
	@Test
	void studentServiceImpl_addStudentToSubjectWithMark_ReturnStudentDto() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		Optional<Subject>optSub = Optional.ofNullable(subjectTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(optSub).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));		
		doReturn(subjectStudentTest).when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		
		StudentDto studentModified = studentService.addStudentToSubjectWithMark(1L, treintaYTres, mark9);
		
		assertAll(
				()->Assertions.assertThat(studentModified).isNotNull(),
				()->Assertions.assertThat(studentModified.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSurname()).isEqualTo(surnameStu),
				()->Assertions.assertThat(studentModified.getName()).isEqualTo(nameStu),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).hasSize(1),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getStudentId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getSubjectId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getAverageGrade).isEqualTo(mark9)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).findById(Mockito.any(SubjectStudentId.class));	
		verify(subjectStudentRepo,times(1)).save(Mockito.any(SubjectStudent.class));
		
	}	

	@Test
	void studentServiceImpl_addStudentToSubjectWithMark_ReturnResourceNotFoundException() {
		
		doReturn(Optional.empty()).when(subjectRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()-> studentService.addStudentToSubjectWithMark(1L, treintaYTres, mark9));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
	}

	@Test
	void studentServiceImpl_addStudentToSubjectWithMark_ReturnResourceNotFoundException2() {

		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(studentRepo).findById(Mockito.anyLong());
		
		Exception ex = assertThrows(ResourceNotFoundException.class,
				()-> studentService.addStudentToSubjectWithMark(1L, treintaYTres, mark9));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(ResourceNotFoundException.class)
				);
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
	}
	
	@Test
	void studentServiceImpl_addStudentToSubjectWithMark_ReturnRequirementNotMetException() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		Optional<Subject>optSub = Optional.ofNullable(subjectTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(optSub).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.ofNullable(subjectStudentTest)).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));
		
		Exception ex = assertThrows(RequirementNotMetException.class,
				()-> studentService.addStudentToSubjectWithMark(1L, treintaYTres, mark9));
		
		assertAll(
				()->Assertions.assertThat(ex).isNotNull(),
				()->Assertions.assertThat(ex.getClass()).isEqualTo(RequirementNotMetException.class)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).findById(Mockito.any(SubjectStudentId.class));
		
	}	
	
	@Test
	void studentServiceImpl_addStudentToSubjectWithMarkOverload1_ReturnStudentDto() {
		
		studentTest.getSubjects().add(subjectStudentTest);
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		Optional<Subject>optSub = Optional.ofNullable(subjectTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doReturn(optSub).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(Optional.empty()).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));		
		doReturn(subjectStudentTest).when(subjectStudentRepo).save(Mockito.any(SubjectStudent.class));
		
		StudentDto studentModified = studentService.addStudentToSubjectWithMark(ssDtoTest);
		
		assertAll(
				()->Assertions.assertThat(studentModified).isNotNull(),
				()->Assertions.assertThat(studentModified.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSurname()).isEqualTo(surnameStu),
				()->Assertions.assertThat(studentModified.getName()).isEqualTo(nameStu),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).hasSize(1),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getStudentId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getSubjectId).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).first().extracting(SubjectStudentDto::getAverageGrade).isEqualTo(mark9)
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).findById(Mockito.any(SubjectStudentId.class));	
		verify(subjectStudentRepo,times(1)).save(Mockito.any(SubjectStudent.class));
		
	}
	
	@Test
	void studentServiceImpl_removeStudenFromSubject_ReturnStudentDto() {
		
		doReturn(Optional.ofNullable(studentTest)).when(studentRepo).findById(Mockito.anyLong());
		doReturn(Optional.ofNullable(subjectTest)).when(subjectRepo).findById(Mockito.anyLong());
		doReturn(false).when(subjectStudentRepo).existsById(Mockito.any(SubjectStudentId.class));	
		doNothing().when(subjectStudentRepo).deleteById(Mockito.any(SubjectStudentId.class));
		
		StudentDto studentModified = studentService.removeStudenFromSubject(1L, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(studentModified).isNotNull(),
				()->Assertions.assertThat(studentModified.getStudentId()).isEqualTo(1L),
				()->Assertions.assertThat(studentModified.getSurname()).isEqualTo(surnameStu),
				()->Assertions.assertThat(studentModified.getName()).isEqualTo(nameStu),
				()->Assertions.assertThat(studentModified.getSubjectStudents()).isEmpty()
				);
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectRepo,times(1)).findById(Mockito.anyLong());
		verify(subjectStudentRepo,times(1)).existsById(Mockito.any(SubjectStudentId.class));
		verify(subjectStudentRepo,times(1)).deleteById(Mockito.any(SubjectStudentId.class));		
	}
	
	
}
