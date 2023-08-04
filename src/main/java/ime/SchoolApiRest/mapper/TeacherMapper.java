package ime.SchoolApiRest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ime.SchoolApiRest.dto.TeacherDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.entity.Teacher;

public class TeacherMapper {

	public static List<TeacherDto>ListToTeacherDto(List<Teacher>list){
		
		List<TeacherDto> listDto = list.stream()
										.map( t -> {
											TeacherDto teacherDto = new TeacherDto();
											teacherDto.setTeacherId(t.getTeacherId());
											teacherDto.setName(t.getName());
											teacherDto.setSurname(t.getSurname());
											teacherDto.setSubjects(t.getSubjects()
																	.stream()
																	.map(s -> {
																		SubjectBasicDto sbd = new SubjectBasicDto();
																		sbd.setSubjectId(s.getSubjectId());
																		sbd.setName(s.getName());
																		return sbd;
																	})
																	.collect(Collectors.toSet())
															);
											return teacherDto;
										})
										.toList();
		
		return listDto;
	}
	
	
}
