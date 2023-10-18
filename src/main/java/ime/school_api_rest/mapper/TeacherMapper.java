package ime.school_api_rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import ime.school_api_rest.dto.TeacherBasicCreationDto;
import ime.school_api_rest.dto.TeacherBasicDto;
import ime.school_api_rest.dto.TeacherDto;
import ime.school_api_rest.entity.Teacher;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class TeacherMapper {
	
	public static List<TeacherDto>listToTeacherDto(List<Teacher>list){
		
		return list.stream()
						.map( t -> {
							TeacherDto teacherDto = new TeacherDto();
							teacherDto.setTeacherId(t.getTeacherId());
							teacherDto.setName(t.getName());
							teacherDto.setSurname(t.getSurname());
							teacherDto.setSubjects(t.getSubjects()
													.stream()
													.map(SubjectMapper::toSubjectBasicDto)
													.collect(Collectors.toSet())
											);
							return teacherDto;
						})
						.toList();
	}
	
	public static TeacherBasicDto toTeacherBasicDto(Teacher teacher) {
		
		return teacher != null? TeacherBasicDto.builder()
								.teacherId(teacher.getTeacherId())
								.name(teacher.getName())
								.surname(teacher.getSurname())
								.build()
								:new TeacherBasicDto();
				
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
				.map(SubjectMapper::toSubjectBasicDto)
				.collect(Collectors.toSet())
				);
		return teacherDto;
	}
}
