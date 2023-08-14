package ime.SchoolApiRest.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ime.SchoolApiRest.entity.Subject;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SubjectRepositoryTest {

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Test
	public void SubjectRepository_getAllEagerSubject_ReturnListSubjectDto() {
		List<Subject> subjects = subjectRepo.getAllEagerSubject();
		Assertions.assertThat(subjects).isNotNull();
		Assertions.assertThat(subjects).hasSizeGreaterThanOrEqualTo(0);
	}

}
