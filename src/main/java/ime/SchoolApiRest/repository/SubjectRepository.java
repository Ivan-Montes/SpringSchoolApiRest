package ime.SchoolApiRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.SchoolApiRest.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{

	@Query("SELECT s FROM Subject s LEFT JOIN FETCH s.students")
	public List<Subject> getAllEagerSubject();
	
	
}
