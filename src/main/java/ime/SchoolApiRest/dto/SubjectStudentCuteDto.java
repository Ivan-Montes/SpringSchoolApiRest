package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SubjectStudentCuteDto {

	@NotNull
	private Long subjectId;

	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z0-9\\s\\-&]+", message="{Pattern.Subject.name}")
	private String subjectName;
	
	@NotNull
	private Long studentId;	
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.name}")
	private String studentName;
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.surname}")
	private String studentSurname;
	
	@Min(value=0)
	@Max(value=10)
	private Double averageGrade;
}
