package ime.school_api_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.school_api_rest.dto.TeacherBasicCreationDto;
import ime.school_api_rest.dto.TeacherBasicDto;
import ime.school_api_rest.dto.TeacherDto;

@Service
public interface TeacherService {
	
	List<TeacherDto> getAllEagerTeachersDto();
	
	TeacherBasicDto getTeacherDtoById(Long teacherId);
	
	void deleteTeacherById(Long teacherId);
	
	TeacherBasicDto createTeacher(TeacherBasicCreationDto tbcDto);
	
	TeacherBasicDto updateTeacher(Long teacherId, TeacherBasicDto tbDto);
	
	TeacherDto addSubjectToTeacher(Long teacherId, Long subjectId);
	
	TeacherDto removeSubjectFromTeacher(Long teacherId, Long subjectId);
}
