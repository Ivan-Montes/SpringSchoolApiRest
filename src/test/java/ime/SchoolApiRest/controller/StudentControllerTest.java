package ime.SchoolApiRest.controller;


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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.service.impl.StudentServiceImpl;

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
	void studentController_deteteStudentById_ReturnVoid() throws Exception{
		
		doNothing().when(studentService).deleteStudentById(Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.delete(path + "/{id}", 1L));
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(studentService,times(1)).deleteStudentById(Mockito.anyLong());
	}

	@Test
	void studentController_createStudent_ReturnStudentBasicDto() throws Exception{
		
		
	}
}
