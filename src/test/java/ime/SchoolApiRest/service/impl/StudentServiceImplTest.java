package ime.SchoolApiRest.service.impl;

import static org.junit.jupiter.api.Assertions.*;
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
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {
		
		studentTest = new Student();
		studentTest.setStudentId(1L);
		studentTest.setName(nameStu);
		studentTest.setSurname(surnameStu);
		studentTest.setSubjects(new HashSet<>());
		
		
		
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
	public void studentServiceImpl_deleteStudentDtoById_ReturnVoid() {
		
	}
	
	@Test
	public void studentServiceImpl_createStudent_ReturnStudentBasicDto() {
		
		
	}
	
	@Test
	public void studentServiceImpl_updateStudent_ReturnStudentBasicDto() {
		
		
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
