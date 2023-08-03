package ime.SchoolApiRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ime.SchoolApiRest.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

	@Query("SELECT t FROM Teacher t left join fetch t.subjects")
	public List<Teacher>findAllEager();
}
