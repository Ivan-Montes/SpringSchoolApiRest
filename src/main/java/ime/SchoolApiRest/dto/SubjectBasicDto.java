package ime.SchoolApiRest.dto;


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
public class SubjectBasicDto {	

	@NotNull(message="{NotNull.Identity.id}")
	private Long subjectId;
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z0-9\\s\\-&]+", message="{Pattern.Subject.name}")
	private String name;


}
