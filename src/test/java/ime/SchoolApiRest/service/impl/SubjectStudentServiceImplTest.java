package ime.SchoolApiRest.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.entity.*;
import ime.SchoolApiRest.repository.SubjectStudentRepository;

@ExtendWith(MockitoExtension.class)
class SubjectStudentServiceImplTest {

	@Mock
	private SubjectStudentRepository subjectStudentRepo;	
	
	@InjectMocks
	private SubjectStudentServiceImpl subjectService;
	
	
	private SubjectStudent subjectStudentTest;
	private Subject subjectTest;
	private Student studentTest;
	private Double mark9 = 9.9;
	
	@BeforeEach
	private void SubjectServiceImpl_createUsers() {		
		
		
		subjectStudentTest = new SubjectStudent();
		subjectStudentTest.setId(new SubjectStudentId(1L,1L));
		subjectStudentTest.setStudent(null);
		subjectStudentTest.setSubject(null);
		subjectStudentTest.setAverageGrade(mark9);
		
		subjectTest = new Subject();
		subjectTest.setSubjectId(1L);
		subjectTest.setName("101");
		subjectTest.setTeacher(null);
		subjectTest.setStudents(new HashSet<>());
		
		studentTest = new Student();
		studentTest.setStudentId(1L);
		studentTest.setName("Philip J");
		studentTest.setSurname("Fry");
		studentTest.setSubjects(new HashSet<>());
	}
	
	
	@Test
	public void subjectStudentServiceImpl_getAllSubjectStudent_ReturListSubjectStudentCuteDto() {
		
		subjectStudentTest.setStudent(studentTest);
		subjectStudentTest.setSubject(subjectTest);		
		List<SubjectStudent>subjectStudentList = List.of(subjectStudentTest);
		doReturn(subjectStudentList).when(subjectStudentRepo).findAll();
		
		List<SubjectStudentCuteDto> subStuDtoFound = subjectService.getAllSubjectStudent();
		
		assertAll(
				()->Assertions.assertThat(subStuDtoFound).isNotNull(),
				()->Assertions.assertThat(subStuDtoFound).hasSize(1)
				);
		verify(subjectStudentRepo, times(1)).findAll();

	}
	
	@Test
	public void subjectStudentServiceImpl_getSubjectStudentCuteDtoById_ReturnSubjectStudentCuteDto() {
		
		subjectStudentTest.setStudent(studentTest);
		subjectStudentTest.setSubject(subjectTest);		
		Optional<SubjectStudent>optSubStu = Optional.ofNullable(subjectStudentTest);		
		doReturn(optSubStu).when(subjectStudentRepo).findById(Mockito.any(SubjectStudentId.class));
		
		SubjectStudentCuteDto subStuDtoFound = subjectService.getSubjectStudentCuteDtoById(1l, Mockito.anyLong());
		
		assertAll(
				()->Assertions.assertThat(subStuDtoFound).isNotNull(),
				()->Assertions.assertThat(subStuDtoFound.getAverageGrade()).isEqualTo(mark9),
				()->Assertions.assertThat(subStuDtoFound.getStudentId()).isEqualTo(studentTest.getStudentId()),
				()->Assertions.assertThat(subStuDtoFound.getSubjectId()).isEqualTo(subjectTest.getSubjectId())
				);
		verify(subjectStudentRepo, times(1)).findById(Mockito.any(SubjectStudentId.class));
	}

	
	@Test
	public void subjectStudentServiceImpl_deleteSubjectStudentById_ReturnVoid() {
		
		
	}
	
	@Test
	public void subjectStudentServiceImpl_createSubjectStudent_ReturnSubjectStudentCuteDto() {
		
	
	}
	
}
