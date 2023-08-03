package ime.SchoolApiRest.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.io.Serializable;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2331130797456051841L;

	private Long studentId;	
	
	@Size(min = 1, max = 50)
	private String name;
	
	@Size(min = 1, max = 50)
	private String surname;
	
	private Set<SubjectDto>subjects = new HashSet<>();
	
}
