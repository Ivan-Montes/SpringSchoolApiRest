package ime.SchoolApiRest.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherDto {

	private Long teacherId;

	@Size(min = 1, max = 50)
	private String name;
	
	@Size(min = 1, max = 50)
	private String surname;
	
	private Set<SubjectDto>subjects = new HashSet<>();
	
}
