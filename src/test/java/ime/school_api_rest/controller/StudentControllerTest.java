package ime.school_api_rest.controller;


import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ime.school_api_rest.controller.StudentController;
import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.dto.SubjectStudentDto;
import ime.school_api_rest.service.impl.StudentServiceImpl;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private StudentServiceImpl studentService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private final String path = "/api/students";
	private StudentDto studentDto;
	private String nameStu = "Philip J";
	private String surnameStu = "Fry";
	private StudentBasicDto sbDtoTest;
	private StudentBasicCreationDto sbcDtoTest;
	private SubjectStudentDto ssDtoTest;
	private Double mark9 = 9.9;
	
	
	@BeforeEach
	public void createObjectsTest() {
		
		studentDto = StudentDto.builder()
					.studentId(1L)
					.name(nameStu)
					.surname(surnameStu)
					.build();
		
		sbDtoTest = StudentBasicDto.builder()
				.studentId(1L)
				.name(nameStu)
				.surname(surnameStu)
				.build();

		sbcDtoTest = StudentBasicCreationDto.builder()
										.name(nameStu)
										.surname(surnameStu)
										.build();
		
		ssDtoTest = SubjectStudentDto.builder()
									.studentId(1L)
									.subjectId(1L)
									.averageGrade(mark9)
									.build();
	}
	
	@Test
	void studentController_getAllStudent_ReturnListStudentDto() throws Exception{

		doReturn(List.of(studentDto)).when(studentService).getAllStudent();
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get(path));

		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", org.hamcrest.Matchers.equalTo(surnameStu)));
		verify(studentService,times(1)).getAllStudent();
		
	}

	
	@Test
	void studentController_getStudentDtoById_ReturnStudentDto() throws Exception{
		
		doReturn(studentDto).when(studentService).getStudentDtoById(Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get(path + "/{id}", 1L));
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)));
		verify(studentService,times(1)).getStudentDtoById(Mockito.anyLong());		
		
	}
	
	@Test
	void studentController_deteteStudentById_ReturnString() throws Exception{
		
		doNothing().when(studentService).deleteStudentById(Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.delete(path + "/{id}", 1L));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(studentService,times(1)).deleteStudentById(Mockito.anyLong());
	}

	@Test
	void studentController_createStudent_ReturnStudentBasicDto() throws Exception{
		
		doReturn(sbDtoTest).when(studentService).createStudent(Mockito.any(StudentBasicCreationDto.class));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sbcDtoTest))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)));
		verify(studentService,times(1)).createStudent(Mockito.any(StudentBasicCreationDto.class));
	}
	
	@Test
	void studentController_updateStudent_ReturnStudentBasicDto() throws Exception{
		
		doReturn(sbDtoTest).when(studentService).updateStudent(Mockito.any(StudentBasicDto.class));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.put(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sbDtoTest))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)));
		verify(studentService,times(1)).updateStudent(Mockito.any(StudentBasicDto.class));
		
	}
	
	@Test
	void studentController_addStudentToSubject_ReturnStudentDto() throws Exception{
		
		studentDto.getSubjectStudents().add(ssDtoTest);
		doReturn(studentDto).when(studentService).addStudentToSubject(Mockito.anyLong(), Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.put(path + "/{studentId}/subjects/{subjectId}", Mockito.anyLong(), Mockito.anyLong()));
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].studentId",org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].subjectId",org.hamcrest.Matchers.equalTo(1)));
		
		verify(studentService,times(1)).addStudentToSubject(Mockito.anyLong(), Mockito.anyLong());
		
	}
	
	@Test
	void studentController_addStudentToSubjectWithMark_ReturnStudentDto() throws Exception{
		
		studentDto.getSubjectStudents().add(ssDtoTest);
		doReturn(studentDto).when(studentService).addStudentToSubjectWithMark(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyDouble());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.put(path + "/{studentId}/subjects/{subjectId}/mark/{averageGrade}", Mockito.anyLong(), Mockito.anyLong(), Mockito.anyDouble() ));
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].studentId",org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].subjectId",org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].averageGrade",org.hamcrest.Matchers.equalTo(mark9)));
		
		verify(studentService,times(1)).addStudentToSubjectWithMark(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyDouble());		
	}
	

	@Test
	void studentController_addStudentToSubjectWithMarkOverload1_ReturnStudentDto() throws Exception{
		
		studentDto.getSubjectStudents().add(ssDtoTest);
		doReturn(studentDto).when(studentService).addStudentToSubjectWithMark( Mockito.any( SubjectStudentDto.class) );
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post(path + "/subjects")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ssDtoTest)));
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo(surnameStu)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].studentId",org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].subjectId",org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents[0].averageGrade",org.hamcrest.Matchers.equalTo(mark9)));
		verify(studentService,times(1)).addStudentToSubjectWithMark( Mockito.any(SubjectStudentDto.class) );		
		}	
	
	@Test
	void studentController_removeStudenFromSubject_ReturnString() throws Exception{
		
		doReturn(studentDto).when(studentService).removeStudenFromSubject( Mockito.anyLong(), Mockito.anyLong() );
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.delete(path + "/{studentId}/subjects/{subjectId}", Mockito.anyLong(), Mockito.anyLong()));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(studentService,times(1)).removeStudenFromSubject(  Mockito.anyLong(), Mockito.anyLong() );
	}
}
