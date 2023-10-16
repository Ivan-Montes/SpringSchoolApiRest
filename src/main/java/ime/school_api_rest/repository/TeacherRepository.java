package ime.school_api_rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.school_api_rest.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	@Query("SELECT t FROM Teacher t left join fetch t.subjects")
	List<Teacher>findAllEager();
	
}
