package ime.SchoolApiRest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ime.SchoolApiRest.dto.TeacherDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.TeacherBasicCreationDto;
import ime.SchoolApiRest.dto.TeacherBasicDto;
import ime.SchoolApiRest.entity.Teacher;

public class TeacherMapper {

	public static List<TeacherDto>listToTeacherDto(List<Teacher>list){
		
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
	
	public static TeacherBasicDto toTeacherBasicDto(Teacher teacher) {
		
		return teacher != null? TeacherBasicDto.builder()
								.teacherId(teacher.getTeacherId())
								.name(teacher.getName())
								.surname(teacher.getSurname())
								.build():new TeacherBasicDto();
				
	}
	
	public static Teacher dtoCreationToTeacher(TeacherBasicCreationDto tbcDto) {
		Teacher t = new Teacher();
		t.setName(tbcDto.getName());
		t.setSurname(tbcDto.getSurname());
		return t;
	}

	public static TeacherDto toTeacherDto(Teacher t) {
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
	}
}
