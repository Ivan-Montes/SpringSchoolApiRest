package ime.SchoolApiRest.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDto{

	@NotNull
	private Long teacherId;

	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Teacher.name}")
	private String name;
	
	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Teacher.surname}")
	private String surname;
	
	private Set<SubjectBasicDto>subjects = new HashSet<>();
	
}
