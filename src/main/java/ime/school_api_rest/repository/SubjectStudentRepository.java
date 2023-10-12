package ime.school_api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.school_api_rest.entity.SubjectStudent;
import ime.school_api_rest.entity.SubjectStudentId;

public interface SubjectStudentRepository extends JpaRepository<SubjectStudent,SubjectStudentId>{

}
