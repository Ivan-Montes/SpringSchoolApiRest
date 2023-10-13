package ime.school_api_rest.repository;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.school_api_rest.entity.Teacher;
import ime.school_api_rest.repository.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.junit.jupiter.api.DisplayName;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TeacherRepositoryTest {

	@Autowired
	private TeacherRepository teacherRepo;
	
	@Test
	@DisplayName("Test for findAllEager method")
	public void TeacherRepository_findAllEager_ReturnListTeacher() {
		List<Teacher>teachers = teacherRepo.findAllEager();
		Assertions.assertThat(teachers).isNotNull();
		Assertions.assertThat(teachers.size()).isGreaterThanOrEqualTo(0);
	}
	

}
