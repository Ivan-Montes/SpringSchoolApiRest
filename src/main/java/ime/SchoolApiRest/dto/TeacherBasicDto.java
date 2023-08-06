package ime.SchoolApiRest.dto;

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

	private Long teacherId;

	@Size(min = 1, max = 50)
	private String name;
	
	@Size(min = 1, max = 50)
	private String surname;
}
