package ime.SchoolApiRest.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.repository.SubjectStudentRepository;
import ime.SchoolApiRest.dto.*;

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
	public void subjectStudentServiceImpl_getAllEagerSubjectDto_ReturSetSubjectDto() {
		
		List<SubjectStudent>subjects = List.of(subjectStudentTest);
		doReturn(subjects).when(subjecStudentRepo).getAllEagerSubjectStudent();
		
		Set<SubjectDto>subjectList = subjectService.getAllEagerSubjectDto();
		
		assertAll(
					()->Assertions.assertThat(subjectList).isNotNull(),
					()->Assertions.assertThat(subjectList).hasSizeGreaterThanOrEqualTo(1)
				);		
		verify(subjecStudentRepo,times(1)).getAllEagerSubjectStudent();
		
	}
	
	@Test
	public void subjectStudentServiceImpl_getSubjectDtoById_ReturnSubjectBasicDto() {
		
		Optional<SubjectStudent>optS = Optional.ofNullable(subjectStudentTest);
		doReturn(optS).when(subjecStudentRepo).findById(Mockito.anyLong());
		
		SubjectBasicDto sbDto = subjectService.getSubjectStudentBasicDtoById(Mockito.anyLong());
		
		assertAll(
					()->Assertions.assertThat(sbDto).isNotNull(),
					()->Assertions.assertThat(sbDto.getSubjectId()).isEqualTo(1L),
					()->Assertions.assertThat(sbDto.getName()).isEqualTo("101")
				);
		verify(subjecStudentRepo, times(1)).findById(Mockito.anyLong());

	}
	
	@Test
	public void subjectStudentServiceImpl_deleteSubjectById_ReturnVoid() {
		
		
	}
	
	@Test
	public void subjectStudentServiceImpl_createSubject_ReturnSubjectBasicDto() {
		
	
	}*/
}
