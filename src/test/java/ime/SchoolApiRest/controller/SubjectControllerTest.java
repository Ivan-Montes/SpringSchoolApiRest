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
	private TeacherBasicDto tbDto;
	private final String NAME_SUBJ = "Programacion JAVA";
	
	
	@BeforeEach
	public void createUsersTest() {
		
		subjectDto = SubjectDto.builder()
					.subjectId(1L)
					.name(NAME_SUBJ)
					.teacher(null)
					.subjectStudents(new HashSet<>())
					.build();
					
		subjectBasicDto = SubjectBasicDto.builder()
						.subjectId(1L)
						.name(NAME_SUBJ)
						.build();
		
		sbcDto = SubjectBasicCreationDto.builder()
										.name(NAME_SUBJ)
										.build();
		tbDto = TeacherBasicDto.builder()
				.teacherId(1L)
				.name("Mrs")
				.surname("Smith")
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
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)));
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
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)));
		verify(subjectService,times(1)).getSubjectBasicDtoById(Mockito.anyLong());

	}
	
	@Test
	public void subjectController_deleteSubjectById_ReturnString() throws Exception{
		
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
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)));
		verify(subjectService,times(1)).createSubject(Mockito.any(SubjectBasicCreationDto.class));
		
	}
	
	@Test
	public void subjectController_updateSubject_ReturnSubjectBasicDto() throws Exception{
		
		doReturn(subjectBasicDto).when(subjectService).updateSubject(Mockito.anyLong(), Mockito.any(SubjectBasicDto.class));
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders
				.put(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(subjectBasicDto))
				);
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)));
		
		verify(subjectService,times(1)).updateSubject(Mockito.anyLong(), Mockito.any(SubjectBasicDto.class));
		
	}
	
	@Test
	public void subjectController_addTeacher_ReturnSubjectDto() throws Exception{
		
		subjectDto.setTeacher(tbDto);
		doReturn(subjectDto).when(subjectService).addTeacherToSubject(Mockito.anyLong(), Mockito.anyLong());
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders
				.get(path + "/{subjectId}/{teacherId}", Mockito.anyLong(), Mockito.anyLong())
				.contentType(MediaType.APPLICATION_JSON)
				);

		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.teacher.name", org.hamcrest.Matchers.equalTo("Mrs")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.teacher.surname", org.hamcrest.Matchers.equalTo("Smith")));
		verify(subjectService,times(1)).addTeacherToSubject(Mockito.anyLong(), Mockito.anyLong());
		
	}

	@Test
	public void subjectController_addStudentToSubject_ReturnSubjectDto() throws Exception{
		
		subjectDto.setTeacher(tbDto);
		subjectDto.getSubjectStudents().add(new SubjectStudentDto());		
		doReturn(subjectDto).when(subjectService).addStudentToSubject(Mockito.anyLong(), Mockito.anyLong());
		
		ResultActions result = mvc.perform( MockMvcRequestBuilders
				.put(path + "/{subjectId}/students/{studentId}", Mockito.anyLong(), Mockito.anyLong())
				.contentType(MediaType.APPLICATION_JSON)
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.hasSize(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.collection.IsCollectionWithSize.hasSize(1)));
		verify(subjectService,times(1)).addStudentToSubject(Mockito.anyLong(), Mockito.anyLong());
		
	}
	
	@Test
	public void subjectController_removeStudentFromSubject_ReturnSubjectDto() throws Exception{
		
		doReturn(subjectDto).when(subjectService).removeStudentFromSubject(Mockito.anyLong(), Mockito.anyLong());

		ResultActions result = mvc.perform( MockMvcRequestBuilders
				.delete(path + "/{subjectId}/students/{studentId}", Mockito.anyLong(), Mockito.anyLong())
				.contentType(MediaType.APPLICATION_JSON)
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo(NAME_SUBJ)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.Matchers.hasSize(0)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.subjectStudents", org.hamcrest.collection.IsCollectionWithSize.hasSize(0)));
		verify(subjectService,times(1)).removeStudentFromSubject(Mockito.anyLong(), Mockito.anyLong());
		
	}
}