package ime.SchoolApiRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ime.SchoolApiRest.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}
