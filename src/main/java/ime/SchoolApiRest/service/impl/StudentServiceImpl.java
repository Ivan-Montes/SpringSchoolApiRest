package ime.SchoolApiRest.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.StudentBasicCreationDto;
import ime.SchoolApiRest.dto.StudentBasicDto;
import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Override
	public List<StudentDto> getAllStudent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto getStudentDtoById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudentDtoById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StudentBasicDto createStudent(StudentBasicCreationDto student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentBasicDto updateStudent(StudentBasicDto student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto addStudentToSubject(Long subjectId, Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto addStudentToSubjectWithMark(Long subjectId, Long studentId, Double averageGrade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto removeStudentToSubject(Long subjectId, Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
