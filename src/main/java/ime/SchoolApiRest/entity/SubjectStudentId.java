package ime.SchoolApiRest.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectStudentId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1355583068667871590L;
	
	@Column(name = "subject_id")
	private Long subjectId;
	
	@Column(name = "student_id")
	private Long studentId;
	
}
