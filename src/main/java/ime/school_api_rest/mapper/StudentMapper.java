package ime.school_api_rest.mapper;

import java.util.List;

import ime.school_api_rest.dto.StudentBasicCreationDto;
import ime.school_api_rest.dto.StudentBasicDto;
import ime.school_api_rest.dto.StudentDto;
import ime.school_api_rest.entity.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class StudentMapper {

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
	
	public static Student fromStudentBasicCreationDtoToStudent(StudentBasicCreationDto sbcDto) {
		
		Student student = new Student();
		
		if ( sbcDto != null ) {

			student.setName(sbcDto.getName());
			student.setSurname(sbcDto.getSurname());
			
		}		
		
		return student;
		
	}
	
	public static StudentBasicDto fromStudentToStudentBasicDto(Student student) {
		
		return student == null? new StudentBasicDto():StudentBasicDto.builder()
													.studentId(student.getStudentId())
													.name(student.getName())
													.surname(student.getSurname())
													.build();		
	}

}