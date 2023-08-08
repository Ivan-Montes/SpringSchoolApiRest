package ime.SchoolApiRest.controller;

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

import ime.SchoolApiRest.service.impl.TeacherServiceImpl;
import ime.SchoolApiRest.dto.*;


@WebMvcTest(TeacherController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TeacherServiceImpl teacherService;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	private TeacherBasicDto teacherBasicDto;
	private TeacherBasicCreationDto tbcDto;
	
	@BeforeEach
	public void beforeEach() {	
		teacherBasicDto = TeacherBasicDto.builder()
										.teacherId(1L)
										.name("Mrs")
										.surname("Smith")
										.build();
		
		tbcDto = new TeacherBasicCreationDto();
		tbcDto.setName("John");
		tbcDto.setSurname("Doe");
	}
	
	
	@Test
	public void TeacherController_getAllEagerTeachersDto_ReturnListTeacherDto() throws Exception{
		
		List<TeacherDto>teachers = List.of(Mockito.mock(TeacherDto.class));		
		doReturn(teachers).when(teacherService).getAllEagerTeachersDto();
		
		ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/api/teachers")
				.contentType(MediaType.APPLICATION_JSON)
				);
		
		response.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$", org.hamcrest.collection.IsCollectionWithSize.hasSize(1)));
		verify(teacherService,times(1)).getAllEagerTeachersDto();
   }
	
	@Test
	public void TeacherController_getTeacherDtoById_ReturnTeacherDto() throws Exception{
			
		doReturn(teacherBasicDto).when(teacherService).getTeacherDtoById(Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/api/teachers/{id}", Mockito.anyLong())
				.contentType(MediaType.APPLICATION_JSON)
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.teacherId", org.hamcrest.Matchers.equalTo(1)));
		
		verify(teacherService,times(1)).getTeacherDtoById(Mockito.anyLong());
	}
	
	@Test
	public void TeacherController_deteteTeacher_ReturnString() throws Exception{
		
		doNothing().when(teacherService).deleteTeacherById(Mockito.anyLong());
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.delete("/api/teachers/{id}", Mockito.anyLong())
				.contentType(MediaType.APPLICATION_JSON)
				);
		
		result.andExpect(MockMvcResultMatchers.status().isOk());
		verify(teacherService,times(1)).deleteTeacherById(Mockito.anyLong());
		
	}
	

	@Test
	public void TeacherController_createTeacher_ReturnTeacherBasicDto() throws Exception {
		
		doReturn(teacherBasicDto).when(teacherService).createTeacher(Mockito.any(TeacherBasicCreationDto.class));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/api/teachers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tbcDto))
				);
		
		result.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo("Mrs")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo("Smith")));		
		verify(teacherService,times(1)).createTeacher(Mockito.any(TeacherBasicCreationDto.class));
		
	}
	
	@Test
	public void TeacherController_updateTeacher_ReturnTeacherBasicDto() throws Exception {
		
		doReturn(teacherBasicDto).when(teacherService).updateTeacher(Mockito.anyLong(), Mockito.any(TeacherBasicDto.class));
		
		ResultActions result = mvc.perform(MockMvcRequestBuilders.put("/api/teachers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(teacherBasicDto))
				);
				
		result.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.teacherId", org.hamcrest.Matchers.equalTo(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name", org.hamcrest.Matchers.equalTo("Mrs")))
		.andExpect(MockMvcResultMatchers.jsonPath("$.surname", org.hamcrest.Matchers.equalTo("Smith")));
		verify(teacherService,times(1)).updateTeacher(Mockito.anyLong(), Mockito.any(TeacherBasicDto.class));
		
	}
}
