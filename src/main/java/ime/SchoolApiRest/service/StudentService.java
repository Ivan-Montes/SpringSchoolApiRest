package ime.SchoolApiRest.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.StudentBasicCreationDto;
import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;

@Service
public interface StudentService {

	List<StudentDto>getAllStudent();
	
	StudentDto getStudentDtoById(Long id);
	
	void deleteStudentById(Long id);
	
	StudentBasicDto createStudent(StudentBasicCreationDto student);
	
	StudentBasicDto updateStudent(StudentBasicDto student);
	
	StudentDto addStudentToSubject(Long subjectId, Long studentId);
	
	StudentDto addStudentToSubjectWithMark(Long subjectId, Long studentId, Double averageGrade);
	
	StudentDto removeStudentToSubject(Long subjectId, Long studentId);
	
}
