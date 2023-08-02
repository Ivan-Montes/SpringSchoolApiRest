package ime.SchoolApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.SchoolApiRest.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long>{

}
