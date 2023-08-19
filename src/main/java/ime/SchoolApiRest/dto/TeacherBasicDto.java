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
public class TeacherBasicDto {

	@NotNull(message="{NotNull.Identity.id}")
	private Long teacherId;

	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.name}")
	private String name;
	
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.surname}")
	private String surname;
}
