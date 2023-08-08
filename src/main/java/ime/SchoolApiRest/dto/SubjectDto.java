package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import ime.SchoolApiRest.entity.Teacher;

import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectDto{

	@NotNull
	private Long subjectId;
	
	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Subject.name}")
	private String name;

	private Teacher teacher;
	
	private Set<StudentDto>students = new HashSet<>();
	
}
