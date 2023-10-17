package ime.school_api_rest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

import java.util.HashSet;
import java.util.Objects;

@Entity
@Table ( name = "STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Generated
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long studentId;	

	@Column(nullable = false, length = 50 )
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.name}")
	private String name;
	
	@Column(nullable=false, length = 50 )
	@Size(min = 1, max = 50, message="{Size.Identity.name}")
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.surname}")
	private String surname;
	
	@OneToMany(mappedBy = "student")
	//@OneToMany(mappedBy = "student", cascade=CascadeType.ALL, orphanRemoval = true)
	private Set<SubjectStudent> subjects = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(name, studentId, subjects, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(name, other.name) && Objects.equals(studentId, other.studentId)
				&& Objects.equals(subjects, other.subjects) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", surname=" + surname + "]";
	}
	
	
}
