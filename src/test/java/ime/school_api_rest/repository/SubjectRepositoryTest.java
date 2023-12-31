package ime.school_api_rest.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ime.school_api_rest.entity.Subject;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SubjectRepositoryTest {

	@Autowired
	private SubjectRepository subjectRepo;
	
	@Test
	void SubjectRepository_getAllEagerSubject_ReturnListSubjectDto() {
		List<Subject> subjects = subjectRepo.getAllEagerSubject();
		
		assertAll(
				()->Assertions.assertThat(subjects).isNotNull(),
				()->Assertions.assertThat(subjects).hasSizeGreaterThanOrEqualTo(0)
				);
	}

}
