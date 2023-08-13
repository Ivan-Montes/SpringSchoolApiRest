package ime.SchoolApiRest.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeacherDto{

	@NotNull(message="{NotNull.Teacher.id}")
	private Long teacherId;

	@Size(min = 1, max = 50, message="{Size.Teacher.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Teacher.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.Teacher.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Teacher.surname}")
	private String surname;
	
	@Default
	private Set<SubjectBasicDto>subjects = new HashSet<>();
	
}
