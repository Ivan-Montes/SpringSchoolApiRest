package ime.school_api_rest.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

	@Test
	void globalExceptionHandler_generalException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		GeneralException generalException = new GeneralException("general","exception",33L);
		ResponseEntity<String> response = globalExceptionHandler.generalException(generalException);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.EXPECTATION_FAILED)
				);
	}

}
