package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectDto {

	private Long subjectId;
	
	@Size(min = 1, max = 50)
	private String name;

	private Long teacherId;

	@Size(min = 1, max = 50)
	private String nameTeacher;

	@Size(min = 1, max = 50)
	private String surname;
	
	private Set<StudentDto>students = new HashSet<>();
	
}
