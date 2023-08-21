package ime.SchoolApiRest.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.repository.SubjectStudentRepository;

@ExtendWith(MockitoExtension.class)
class SubjectStudentServiceImplTest {

	@Mock
	private SubjectStudentRepository subjecStudentRepo;	
	
	@InjectMocks
	private SubjectStudentServiceImpl subjectService;
	
	private SubjectStudent subjectStudentTest;
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {
		subjectStudentTest = new SubjectStudent();
		
	}
	
	/*
	@Test
	public void subjectStudentServiceImpl_getAllEagerSubjectStudentDto_ReturSetSubjectStudentDto() {
		
	}
	
	@Test
	public void subjectStudentServiceImpl_getSubjectStudentDtoById_ReturnSubjectStudentDto() {
		
		

	}
	
	@Test
	public void subjectStudentServiceImpl_deleteSubjectStudentById_ReturnVoid() {
		
		
	}
	
	@Test
	public void subjectStudentServiceImpl_createSubjectStudent_ReturnSubjectStudentBasicDto() {
		
	
	}*/
}
