package ime.SchoolApiRest.entity;


import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
	@JoinColumn(name = "sub_id")
	private Subject subject;
	
	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(name = "stu_id")
	private Student student;
	
	@Column(name = "avegare_grade")
	@Min(value=0, message = "{Min.SubjectStudent.avegare_grade}")
	@Max(value=10, message = "{Max.SubjectStudent.avegare_grade}")
	private Double averageGrade;	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(averageGrade, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubjectStudent other = (SubjectStudent) obj;
		return Objects.equals(averageGrade, other.averageGrade) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "SubjectStudent [id=" + id + ", averageGrade=" + averageGrade + "]";
	}
	
	
}
