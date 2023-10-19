package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final String SIMPLE_TEXT_SEPARATOR = " _***- Identifier: ";
	
	@ExceptionHandler(ime.school_api_rest.exception.GeneralException.class)
	public ResponseEntity<String> generalException(GeneralException ex){
		
		return new ResponseEntity<>("GeneralException" + SIMPLE_TEXT_SEPARATOR, HttpStatus.EXPECTATION_FAILED);
	}	
	
	@ExceptionHandler(ime.school_api_rest.exception.ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
		
		return new ResponseEntity<>(ex.getMessage() + SIMPLE_TEXT_SEPARATOR + ex.getIdentifier(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ime.school_api_rest.exception.SubjectAssociatedException.class, 
						ime.school_api_rest.exception.StudentAssociatedException.class,
						ime.school_api_rest.exception.TeacherAssociatedException.class,
						ime.school_api_rest.exception.RequirementNotMetException.class})
	public ResponseEntity<String> elementAssociatedException(GeneralException ex){
		
		return new ResponseEntity<>(ex.getMessage() + SIMPLE_TEXT_SEPARATOR + ex.getIdentifier(), HttpStatus.PRECONDITION_REQUIRED);
	}
	
	//Called due to @Valid tag
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach( error -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String>methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);		
		
	}
	
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public ResponseEntity<String>badCredentialsException(BadCredentialsException ex){
		
		return new ResponseEntity<>("BadCredentialsException" + SIMPLE_TEXT_SEPARATOR, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);		
		
	}	
	
	@ExceptionHandler({
		jakarta.validation.ConstraintViolationException.class,// <== Called by Hibernate after check class/attributes annotations 
		org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException.class})		
	public ResponseEntity<String> jakartaValidationConstraintViolationException(Exception ex){
		
		return new ResponseEntity<>("ConstraintViolationException\n\n" + ex.getMessage() + "\n", HttpStatus.BAD_REQUEST);
		
	}	
	
}