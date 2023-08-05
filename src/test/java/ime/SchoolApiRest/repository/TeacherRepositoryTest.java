package ime.SchoolApiRest.repository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.junit.jupiter.api.Order;

import java.util.List;
import ime.SchoolApiRest.entity.Teacher;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(OrderAnnotation.class)
class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Test
	@Order(1)
	public void TeacherRepository_findAllEager_ReturnListTeacher() {
		List<Teacher>teachers = teacherRepo.findAllEager();
		Assertions.assertThat(teachers).isNotNull();
		Assertions.assertThat(teachers.size()).isGreaterThanOrEqualTo(0);
	}
	

}
