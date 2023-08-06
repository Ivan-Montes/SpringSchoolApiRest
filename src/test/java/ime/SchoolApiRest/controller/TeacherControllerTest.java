package ime.SchoolApiRest.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
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
	
	private TeacherBasicDto teacherBasicDto;
	private TeacherBasicDto teacherBasicDtoMock;
	
	@BeforeEach
	public void beforeEach() {
		teacherBasicDtoMock = Mockito.mock(TeacherBasicDto.class);		
		teacherBasicDto = TeacherBasicDto.builder()
										.teacherId(1L)
										.name("John")
										.surname("Doe")
										.build();
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

}
