package ime.school_api_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.school_api_rest.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
