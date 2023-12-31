package ime.school_api_rest.entity;

import jakarta.persistence.CascadeType;
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
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import java.util.Set;


import java.util.HashSet;

@Entity
@Table( name = "TEACHERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Generated
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teacher_id")
	private Long teacherId;
	
	@Column(nullable = false, length = 50 )
	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.name}")
	private String name;
	
	@Column(nullable=false, length = 50 )
	@Size(min = 1, max = 50)
	@Pattern( regexp = "[a-zA-Z\\s\\-&]+", message="{Pattern.Identity.surname}")
	private String surname;
	
	@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    //@Fetch(value = FetchMode.JOIN)
	//@JsonIgnoreProperties({"teacher"})
	private Set<Subject>subjects = new HashSet<>();
	
	
	
}
