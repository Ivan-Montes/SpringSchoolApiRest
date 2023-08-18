package ime.SchoolApiRest.dto;

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
public class SubjectBasicCreationDto {
	
	@Size(min = 1, max = 50,  message="{Size.Subject.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Subject.name}")
	private String name;
	
}
