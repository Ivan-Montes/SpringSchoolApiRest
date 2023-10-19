package ime.school_api_rest.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneralExceptionTest {

	@Test
	void generalException_GeneralException_ReturnObject() {

		GeneralException generalException = new GeneralException("general","exception",33L);
		
		assertAll(
				()->Assertions.assertThat(generalException.getName()).isEqualTo("general"),
				()->Assertions.assertThat(generalException.getMessageInfo()).isEqualTo("exception"),
				()->Assertions.assertThat(generalException.getIdentifier()).isEqualTo(33L)
				);		
	}

}