package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

import ime.SchoolApiRest.entity.Teacher;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectDto{

	private Long subjectId;
	
	@Size(min = 1, max = 50)
	private String name;

	private Teacher teacher;
	
	private Set<StudentDto>students = new HashSet<>();
	
}
