package ime.SchoolApiRest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table ( name = "SUBJECTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
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
	
	
}
