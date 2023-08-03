package ime.SchoolApiRest.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Objects;

@Entity
@Table( name = "TEACHERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Long teacherId;
	
	@Column(nullable = false, length = 50 )
	@Size(min = 1, max = 50)
	private String name;
	
	@Column(nullable=false, length = 50 )
	@Size(min = 1, max = 50)
	private String surname;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.JOIN)
	@JsonIgnoreProperties({"teacher"})
	private Set<Subject>subjects = new HashSet<>();

	@Override
	public int hashCode() {
		return Objects.hash(name, surname, teacherId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return Objects.equals(name, other.name) 
				&& Objects.equals(surname, other.surname) && Objects.equals(teacherId, other.teacherId);
	}



	
	
	
}
