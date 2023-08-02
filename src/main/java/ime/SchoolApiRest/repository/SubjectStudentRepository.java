package ime.SchoolApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.SchoolApiRest.entity.SubjectStudent;
import ime.SchoolApiRest.entity.SubjectStudentId;

public interface SubjectStudentRepository extends JpaRepository<SubjectStudent,SubjectStudentId>{

}
