package ime.SchoolApiRest.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
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

import ime.SchoolApiRest.entity.Subject;
import ime.SchoolApiRest.repository.SubjectRepository;
import ime.SchoolApiRest.dto.*;

@ExtendWith(MockitoExtension.class)
class SubjectStudentServiceImplTest {

	@Mock
	private SubjectRepository subjectRepo;
	
	
	@InjectMocks
	private SubjectServiceImpl subjectService;
	
	private Subject subjectTest;
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {
		subjectTest = new Subject();
		subjectTest.setSubjectId(1L);
		subjectTest.setName("101");
		subjectTest.setTeacher(null);
		subjectTest.setStudents(new HashSet<>());
		
	}
	
	
	@Test
	public void subjectStudentServiceImpl_getAllEagerSubjectDto_ReturSetSubjectDto() {
		
		List<Subject>subjects = List.of(subjectTest);
		doReturn(subjects).when(subjectRepo).getAllEagerSubject();
		
		Set<SubjectDto>subjectList = subjectService.getAllEagerSubjectDto();
		
		assertAll(
					()->Assertions.assertThat(subjectList).isNotNull(),
					()->Assertions.assertThat(subjectList).hasSizeGreaterThanOrEqualTo(1)
				);		
		verify(subjectRepo,times(1)).getAllEagerSubject();
		
	}
	
	@Test
	public void subjectStudentServiceImpl_getSubjectDtoById_ReturnSubjectBasicDto() {
		
		Optional<Subject>optS = Optional.ofNullable(subjectTest);
		doReturn(optS).when(subjectRepo).findById(Mockito.anyLong());
		
		SubjectBasicDto sbDto = subjectService.getSubjectBasicDtoById(Mockito.anyLong());
		
		assertAll(
					()->Assertions.assertThat(sbDto).isNotNull(),
					()->Assertions.assertThat(sbDto.getSubjectId()).isEqualTo(1L),
					()->Assertions.assertThat(sbDto.getName()).isEqualTo("101")
				);
		verify(subjectRepo, times(1)).findById(Mockito.anyLong());

	}
	

}
