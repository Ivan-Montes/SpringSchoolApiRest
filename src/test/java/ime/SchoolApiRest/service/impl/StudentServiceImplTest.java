package ime.SchoolApiRest.service.impl;

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

import ime.SchoolApiRest.dto.StudentBasicCreationDto;
import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.entity.Student;
import ime.SchoolApiRest.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

	@Mock
	private StudentRepository studentRepo;
	
	@InjectMocks
	private StudentServiceImpl studentService;

	private Student studentTest;
	private String nameStu = "Philip J";
	private String surnameStu = "Fry";
	private StudentBasicDto sbDtoTest;
	private StudentBasicCreationDto sbcDtoTest;
	
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
				
				
		
		
	}
	
	@Test
	public void studentServiceImpl_getAllStudent_ReturnList() {
		
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
	public void studentServiceImpl_getStudentDtoById_ReturnStudent() {
		
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
	public void studentServiceImpl_deleteStudentById_ReturnVoid() {
		
		Optional<Student> optStu = Optional.ofNullable(studentTest);
		doReturn(optStu).when(studentRepo).findById(Mockito.anyLong());
		doNothing().when(studentRepo).deleteById(Mockito.anyLong());
		
		studentService.deleteStudentById(Mockito.anyLong());
		
		verify(studentRepo,times(1)).findById(Mockito.anyLong());
		verify(studentRepo,times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	public void studentServiceImpl_createStudent_ReturnStudentBasicDto() {
		
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
	public void studentServiceImpl_updateStudent_ReturnStudentBasicDto() {
		
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
	public void studentServiceImpl_addStudentToSubject_ReturnStudentDto() {
		
	}
	

	@Test
	public void studentServiceImpl_addStudentToSubjectWithMark_ReturnStudentDto() {
		
		
	}
	
	@Test
	public void studentServiceImpl_removeStudentToSubject_ReturnStudentDto() {
		
		
	}
}
