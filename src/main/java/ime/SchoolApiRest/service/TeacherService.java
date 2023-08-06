package ime.SchoolApiRest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ime.SchoolApiRest.dto.TeacherBasicDto;
import ime.SchoolApiRest.dto.TeacherDto;

@Service
public interface TeacherService {
	
	List<TeacherDto> getAllEagerTeachersDto();
	
	TeacherBasicDto getTeacherDtoById(Long teacherId);
}
