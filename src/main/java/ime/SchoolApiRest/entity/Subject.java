package ime.SchoolApiRest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import java.util.HashSet;
import java.util.Objects;

@Entity
@Table ( name = "SUBJECTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private Long subjectId;
	
	@Column(nullable = false, length = 50)
	@Size(min = 1, max = 50)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "subject")
	private Set<SubjectStudent>students = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(name, students, subjectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return Objects.equals(name, other.name) && Objects.equals(students, other.students)
				&& Objects.equals(subjectId, other.subjectId);
	}

	@Override
	public String toString() {
		return "Subject [subjectId=" + subjectId + ", name=" + name + "]";
	}
	
	
}
