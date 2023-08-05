package ime.SchoolApiRest.service.impl;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.SchoolApiRest.entity.*;
import ime.SchoolApiRest.repository.TeacherRepository;
import ime.SchoolApiRest.dto.*;

import java.util.List;
import java.util.HashSet;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

	@Mock
	private TeacherRepository teacherRepo;
	
	@InjectMocks
	private TeacherServiceImpl teacherService;
	
	@Test
	public void TeacherServiceImpl_getAllEagerTeachersDto_ReturnListTeacherDto() {
		
	}
}
