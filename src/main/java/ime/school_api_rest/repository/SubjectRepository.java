package ime.school_api_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.school_api_rest.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{

	@Query("SELECT s FROM Subject s LEFT JOIN FETCH s.students")
	public List<Subject> getAllEagerSubject();
	
	
}
