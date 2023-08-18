package ime.SchoolApiRest.controller;



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

import ime.SchoolApiRest.dto.*;
import ime.SchoolApiRest.service.impl.SubjectServiceImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.Set;

@WebMvcTest(SubjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private SubjectServiceImpl subjectService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private final String path = "/api/subjects";
	private SubjectDto subjectDto;
	private SubjectBasicDto subjectBasicDto;
	private SubjectBasicCreationDto sbcDto;
	
	@BeforeEach
	public void createUsersTest() {
		subjectDto = SubjectDto.builder()
					.subjectId(1L)
					.name("101")
					.teacher(null)
					.subjectStudents(new HashSet<>())
					.build();
					
		subjectBasicDto = SubjectBasicDto.builder()
						.subjectId(1L)
						.name("101")
						.build();
		
		sbcDto = SubjectBasicCreationDto.builder()
										.name("101")
										.build();
				
	}
	
	@Test
	public void subjectController_getAllEagerSubjectDto_ReturnListSubjectDto() throws Exception {
		
		Set<SubjectDto>subjects = Set.of(subjectDto);		
		doReturn(subjects).when(subjectService).getAllEagerSubjectDto();
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get(path)
				.contentType(MediaType.APPLICATION_JSON)
				);
	
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", org.hamcrest.Matchers.equalTo("101")));
		verify(subjectService,times(1)).getAllEagerSubjectDto();
	}
	
	@Test
	public void subjectController_getSubjectBasicDtoById_ReturnSubjectBasicDto() throws Exception{
		
		doReturn(subjectBasicDto).when(subjectService).getSubjectBasicDtoById(Mockito.anyLong());
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders
																.get(path + "/{id}", Mockito.anyLong())
																.contentType(MediaType.APPLICATION_JSON)
											);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo("101")));
		verify(subjectService,times(1)).getSubjectBasicDtoById(Mockito.anyLong());

	}
	
	@Test
	public void subjectController_deleteSubjectByIdReturnString() throws Exception{
		
		doNothing().when(subjectService).deleteSubjectById(Mockito.anyLong());
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders.delete(path + "/{id}", Mockito.anyLong())
																	.contentType(MediaType.APPLICATION_JSON)
											);
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(subjectService,times(1)).deleteSubjectById(Mockito.anyLong());
		
	}
	
	@Test
	public void subjectController_createSubject_ReturnSubjectBasicDto() throws Exception{
		
		doReturn(subjectBasicDto).when(subjectService).createSubject(Mockito.any(SubjectBasicCreationDto.class));
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders
																.post(path)
																.contentType(MediaType.APPLICATION_JSON)
																.content(objectMapper.writeValueAsString(sbcDto))
											);
		
		result.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()));
		verify(subjectService,times(1)).createSubject(Mockito.any(SubjectBasicCreationDto.class));
	}
	
	@Test
	public void subjectController_updateSubject_ReturnSubjectBasicDto() throws Exception{
		
	}
	
	@Test
	public void subjectController_addTeacher_ReturnSubjectDto() throws Exception{
		
	}

}
