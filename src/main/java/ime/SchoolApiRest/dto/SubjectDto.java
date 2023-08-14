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
public class SubjectDto{

	@NotNull(message="{NotNull.Subject.name}")
	private Long subjectId;
	
	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Subject.name}")
	private String name;

	private TeacherBasicDto teacher;
	
	@Default
	private Set<SubjectStudentDto>subjectStudents = new HashSet<>();
	
}
