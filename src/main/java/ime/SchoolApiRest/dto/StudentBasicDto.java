package ime.SchoolApiRest.dto;

import java.util.Set;

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
public class StudentBasicDto {

	private Long studentId;
	
	@Size(min = 1, max = 50)
	private String name;
	
}
