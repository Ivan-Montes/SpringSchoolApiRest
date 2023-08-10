package ime.SchoolApiRest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.TeacherBasicCreationDto;
import ime.SchoolApiRest.dto.TeacherBasicDto;
import ime.SchoolApiRest.dto.TeacherDto;

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
