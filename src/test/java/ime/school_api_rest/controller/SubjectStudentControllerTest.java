package ime.school_api_rest.controller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
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

import ime.school_api_rest.controller.SubjectStudentController;
import ime.school_api_rest.dto.*;
import ime.school_api_rest.service.impl.SubjectStudentServiceImpl;

@WebMvcTest(SubjectStudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SubjectStudentControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SubjectStudentServiceImpl subjectStudentService;

	@Autowired
    private ObjectMapper objectMapper;
	
	private final String path = "/api/subjectstudents";
	private SubjectStudentCuteDto sscDto;
	private Double mark9 = 9.9;
	private Double mark3 = 3.3;
	private SubjectStudentCreationDto ssCreationDto;
	
	@BeforeEach
	public void createObjectsTest() {
		
		sscDto = SubjectStudentCuteDto.builder()
				.studentId(1L)
				.studentName("")
				.subjectId(1L)
				.subjectName("")
				.averageGrade(mark9)
				.build();
		
		ssCreationDto = SubjectStudentCreationDto.builder()
				.subjectId(1l)
				.studentId(1l)
				.averageGrade(mark9)
				.build();
	}
	
	
	@Test
	void subjectStudentController_getAll_ReturnListSubjectStudentCuteDto() throws Exception {
		
		doReturn(List.of(sscDto)).when(subjectStudentService).getAllSubjectStudent();
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get(path)
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].averageGrade", org.hamcrest.Matchers.equalTo(mark9)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].studentId", org.hamcrest.Matchers.equalTo(1)))
		;
		verify(subjectStudentService,times(1)).getAllSubjectStudent();
	}
	
	@Test
	void subjectStudentController_getSubjectStudentCuteDtoById_ReturnSubjectStudentCuteDto() throws Exception {
		
		doReturn(sscDto).when(subjectStudentService).getSubjectStudentCuteDtoById(Mockito.anyLong(), Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get(path + "/get-by-composite-key")
				.param("subjectId", "1")
				.param("studentId", "1")
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.averageGrade", org.hamcrest.Matchers.equalTo(mark9)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		;
		verify(subjectStudentService,times(1)).getSubjectStudentCuteDtoById(Mockito.anyLong(), Mockito.anyLong());
	}
	
	@Test
	void subjectStudentController_deleteSubjectStudentById_ReturnString() throws Exception{
		
		doNothing().when(subjectStudentService).deleteSubjectStudentById(Mockito.anyLong(), Mockito.anyLong());
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders.delete(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "\"subjectId\":\"2\",\r\n"
						+ "\"studentId\":\"2\"\r\n"
						+ "}")
				
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(subjectStudentService,times(1)).deleteSubjectStudentById(Mockito.anyLong(), Mockito.anyLong());

	}
	
	@Test
	void subjectStudentController_createSubjectStudent_ReturnSubjectStudentCuteDto() throws Exception {
		
		doReturn(sscDto).when(subjectStudentService).createSubjectStudent(Mockito.any(SubjectStudentCreationDto.class));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ssCreationDto))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.averageGrade", org.hamcrest.Matchers.equalTo(mark9)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		;
		verify(subjectStudentService,times(1)).createSubjectStudent(Mockito.any(SubjectStudentCreationDto.class));		
		
	}
	
	@Test
	void subjectStudentController_updateSubjectStudent_ReturnSubjectStudentCuteDto() throws Exception {
		
		sscDto.setAverageGrade(mark3);
		doReturn(sscDto).when(subjectStudentService).updateSubjectStudent(Mockito.any(SubjectStudentCreationDto.class));

		ResultActions result = mvc.perform(MockMvcRequestBuilders.put(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(ssCreationDto))
		);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.averageGrade", org.hamcrest.Matchers.equalTo(mark3)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.studentId", org.hamcrest.Matchers.equalTo(1)))
		;
		verify(subjectStudentService,times(1)).updateSubjectStudent(Mockito.any(SubjectStudentCreationDto.class));	

	}
	
	
}
