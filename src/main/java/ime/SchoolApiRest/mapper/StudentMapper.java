package ime.SchoolApiRest.mapper;

import java.util.List;

import ime.SchoolApiRest.dto.StudentDto;
import ime.SchoolApiRest.entity.Student;

public class StudentMapper {


	public static List<StudentDto> toListStudentDto(List<Student> students) {
		
		return students.stream()
						.map(StudentMapper::toStudentDto)
						.toList();
	}
	
	public static StudentDto toStudentDto(Student student){
		
		return student == null? new StudentDto():StudentDto.builder()
													.studentId(student.getStudentId())
													.name(student.getName())
													.surname(student.getSurname())
													.subjectStudents(SubjectStudentMapper.toSetSubjectStudentDto(student.getSubjects()))
													.build();
	}

}
