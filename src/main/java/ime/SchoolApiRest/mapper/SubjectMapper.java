package ime.SchoolApiRest.mapper;

import java.util.List;

import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.entity.Subject;

public class SubjectMapper {

	public static List<SubjectDto> toListSubjectDto(List<Subject> subjects) {
		return null;
		
	}
	
	public static SubjectDto toSubjectDto(Subject subject) {
		
		return SubjectDto.builder()
							.subjectId(subject.getSubjectId())
							.name(subject.getName())
							.teacher(TeacherMapper.toTeacherBasicDto(subject.getTeacher()))
							.subjectStudents(SubjectStudentMapper.toListSubjectStudentDto(subject.getStudents()))
							.build();
							
	}
	
	

}
