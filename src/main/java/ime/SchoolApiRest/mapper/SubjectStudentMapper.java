package ime.SchoolApiRest.mapper;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ime.SchoolApiRest.dto.SubjectStudentCuteDto;
import ime.SchoolApiRest.dto.SubjectStudentDto;
import ime.SchoolApiRest.entity.SubjectStudent;

public class SubjectStudentMapper {

	
	public static Set<SubjectStudentDto> toSetSubjectStudentDto(Set<SubjectStudent>subjectStudents){
		
		return subjectStudents.stream()
								.map(SubjectStudentMapper::toSubjectStudentDto)
								.collect(Collectors.toSet());
	}
	
	public static SubjectStudentDto toSubjectStudentDto(SubjectStudent subjectStudent) {
		
		return subjectStudent == null? new SubjectStudentDto():SubjectStudentDto.builder()
																.studentId(subjectStudent.getId().getStudentId())
																.subjectId(subjectStudent.getId().getSubjectId())
																.averageGrade(subjectStudent.getAverageGrade())
																.build();
		
	}
	
	public static List<SubjectStudentDto> toListSubjectStudentDto(List<SubjectStudent>subjectStudents){
			
			return subjectStudents.stream()
									.map(SubjectStudentMapper::toSubjectStudentDto)
									.collect(Collectors.toList());
	}

	public static SubjectStudentCuteDto toSubjectStudentCuteDto(SubjectStudent subjectStudent) {
		
		return subjectStudent == null? new SubjectStudentCuteDto():SubjectStudentCuteDto.builder()
																.studentId(subjectStudent.getId().getStudentId())
																.subjectId(subjectStudent.getId().getSubjectId())
																.studentName(subjectStudent.getStudent().getName())
																.studentSurname(subjectStudent.getStudent().getSurname())
																.subjectName(subjectStudent.getSubject().getName())
																.averageGrade(subjectStudent.getAverageGrade())
																.build();
	}
	
	public static List<SubjectStudentCuteDto> toListSubjectStudentCuteDto(List<SubjectStudent>subjectStudents){
		
		return subjectStudents.stream()
								.map(SubjectStudentMapper::toSubjectStudentCuteDto)
								.collect(Collectors.toList());
}
}
