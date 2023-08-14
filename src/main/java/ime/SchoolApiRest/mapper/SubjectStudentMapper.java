package ime.SchoolApiRest.mapper;


import java.util.Set;
import java.util.stream.Collectors;

import ime.SchoolApiRest.dto.SubjectStudentDto;
import ime.SchoolApiRest.entity.SubjectStudent;

public class SubjectStudentMapper {

	
	public static Set<SubjectStudentDto> toListSubjectStudentDto(Set<SubjectStudent>subjectStudents){
		
		return subjectStudents.stream()
								.map(SubjectStudentMapper::toSubjectStudentDto)
								.collect(Collectors.toSet());
	}
	
	public static SubjectStudentDto toSubjectStudentDto(SubjectStudent subjectStudent) {
		
		return SubjectStudentDto.builder()
				.studentId(subjectStudent.getId().getStudentId())
				.subjectId(subjectStudent.getId().getSubjectId())
				.averageGrade(subjectStudent.getAverageGrade())
				.build();
		
	}
}
