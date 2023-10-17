package ime.school_api_rest.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class SubjectStudentId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1355583068667871590L;
	
	@Column(name = "subject_id")
	@NotNull
	private Long subjectId;
	
	@Column(name = "student_id")
	@NotNull
	private Long studentId;
	
}
