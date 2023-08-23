package ime.SchoolApiRest.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ime.SchoolApiRest.dto.SubjectBasicCreationDto;
import ime.SchoolApiRest.dto.SubjectBasicDto;
import ime.SchoolApiRest.dto.SubjectDto;
import ime.SchoolApiRest.entity.Subject;

public class SubjectMapper {

	public static Set<SubjectDto> toListSubjectDto(List<Subject> subjects) {
		return subjects.stream()
						.map(SubjectMapper::toSubjectDto)
						.collect(Collectors.toSet());
		
	}
	
	public static SubjectDto toSubjectDto(Subject subject) {
		
		return subject != null? SubjectDto.builder()
											.subjectId(subject.getSubjectId())
											.name(subject.getName())
											.teacher(TeacherMapper.toTeacherBasicDto(subject.getTeacher()))
											.subjectStudents(SubjectStudentMapper.toSetSubjectStudentDto(subject.getStudents()))
											.build()
								: new SubjectDto();
							
	}

	public static SubjectBasicDto toSubjectBasicDto(Subject subject) {
		
		return subject != null? SubjectBasicDto.builder()
												.subjectId(subject.getSubjectId())
												.name(subject.getName())
												.build()
											:new SubjectBasicDto();
	}

	public static Subject dtoCreationToSubject(SubjectBasicCreationDto sbcDto) {
		Subject subject = new Subject();
		subject.setName(sbcDto.getName());
		return subject;
	}
	
	

}
