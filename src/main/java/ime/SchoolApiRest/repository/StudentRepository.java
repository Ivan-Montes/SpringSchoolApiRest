package ime.SchoolApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.SchoolApiRest.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Long>{

}
