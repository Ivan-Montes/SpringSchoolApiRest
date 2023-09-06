package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentDto{	
	

	@NotNull(message="{NotNull.Identity.id}")
	private Long studentId;	
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.surname}")
	private String surname;
	
	@Default
	private Set<SubjectStudentDto>subjectStudents = new HashSet<>();
	
}
