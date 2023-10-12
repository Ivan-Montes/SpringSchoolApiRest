package ime.school_api_rest.service;
import java.util.List;

import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.dto.SubjectStudentDto;

@Service
public interface StudentService {

	List<StudentDto>getAllStudent();
	
	StudentDto getStudentDtoById(Long id);
	
	void deleteStudentById(Long id);
	
	StudentBasicDto createStudent(StudentBasicCreationDto student);
	
	StudentBasicDto updateStudent(StudentBasicDto student);
	
	StudentDto addStudentToSubject(Long studentId, Long subjectId);
	
	StudentDto addStudentToSubjectWithMark(Long studentId, Long subjectId, Double averageGrade);
	
	StudentDto addStudentToSubjectWithMark(SubjectStudentDto subjectStudentDto);

	StudentDto removeStudenFromSubject(Long studentId, Long subjectId);
	
}
