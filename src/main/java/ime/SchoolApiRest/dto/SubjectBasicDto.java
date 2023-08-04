package ime.SchoolApiRest.dto;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectBasicDto {	

	private Long subjectId;
	
	@Size(min = 1, max = 50)
	private String name;


}
