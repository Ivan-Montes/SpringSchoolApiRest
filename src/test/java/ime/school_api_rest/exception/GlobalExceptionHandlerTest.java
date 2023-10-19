package ime.school_api_rest.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

class GlobalExceptionHandlerTest {

	private final long treintaYTres = 33L;
	
	@Test
	void globalExceptionHandler_generalException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		GeneralException ex = new GeneralException("general","exception", treintaYTres);
		ResponseEntity<String> response = globalExceptionHandler.generalException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.EXPECTATION_FAILED)
				);
	}
	
	@Test
	void globalExceptionHandler_resourceNotFoundException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		ResourceNotFoundException ex = new ResourceNotFoundException(treintaYTres);
		ResponseEntity<String> response = globalExceptionHandler.resourceNotFoundException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND)
				);
	}
	
	@Test
	void globalExceptionHandler_elementAssociatedException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		GeneralException ex = new SubjectAssociatedException(treintaYTres);
		ResponseEntity<String> response = globalExceptionHandler.elementAssociatedException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PRECONDITION_REQUIRED)
				);
	}

	@Test
	void globalExceptionHandler_methodArgumentTypeMismatchException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		MethodArgumentTypeMismatchException ex = new MethodArgumentTypeMismatchException(null, null, null, null, new Exception());
		ResponseEntity<String> response = globalExceptionHandler.methodArgumentTypeMismatchException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST)
				);
	}

	@Test
	void globalExceptionHandler_badCredentialsException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		BadCredentialsException ex = new BadCredentialsException(null);
		ResponseEntity<String> response = globalExceptionHandler.badCredentialsException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.PROXY_AUTHENTICATION_REQUIRED)
				);
	}

	@Test
	void globalExceptionHandler_jakartaValidationConstraintViolationException_ReturnResponseEntity() {
		
		GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
		ConstraintViolationException ex = new ConstraintViolationException(null);
		ResponseEntity<String> response = globalExceptionHandler.jakartaValidationConstraintViolationException(ex);
		
		assertAll(
				()->Assertions.assertThat(response).isNotNull(),
				()->Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST)
				);
	}
}
