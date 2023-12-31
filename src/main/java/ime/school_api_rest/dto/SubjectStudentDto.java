package ime.school_api_rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class SubjectStudentDto {

	@NotNull
	private Long subjectId;

	@NotNull
	private Long studentId;	

	@Min(value=0)
	@Max(value=10)
	private Double averageGrade;
}
