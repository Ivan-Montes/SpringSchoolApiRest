package ime.SchoolApiRest.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = "SUBJECTS_STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectStudent {

	@EmbeddedId
	private SubjectStudentId id;
	
	@ManyToOne
	@MapsId("subjectId")
	@JoinColumn(name = "sub_id")
	private Subject subject;
	
	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(name = "stu_id")
	private Student student;
	
	@Column(name = "avegare_grade")
	@Min(value=0)
	@Max(value=10)
	private Double averageGrade;
	
	
}
