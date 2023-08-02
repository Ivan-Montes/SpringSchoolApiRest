package ime.SchoolApiRest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name = "SUBJECTS_STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectStudent {

	@EmbeddedId
	private SubjectStudentId id;
	
	@ManyToOne
	@MapsId("subjectId")
	private Subject subject;
	
	@ManyToOne
	@MapsId("studentId")
	private Student student;
	
	
	
}
