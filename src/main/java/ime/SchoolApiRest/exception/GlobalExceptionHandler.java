package ime.SchoolApiRest.exception;

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

	private final String simpleText = " _***- Identifier: ";
	
	@ExceptionHandler(ime.SchoolApiRest.exception.ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
		
		return new ResponseEntity<String>(ex.getMessage() + simpleText + ex.getIdentifier(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ime.SchoolApiRest.exception.SubjectAssociatedException.class, 
						ime.SchoolApiRest.exception.StudentAssociatedException.class,
						ime.SchoolApiRest.exception.TeacherAssociatedException.class})
	public ResponseEntity<String> elementtAssociatedException(GeneralException ex){
		
		return new ResponseEntity<String>(ex.getMessage() + simpleText + ex.getIdentifier(), HttpStatus.PRECONDITION_REQUIRED);
	}
	//Called due to @Valid tag
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String>methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);		
		
	}
	
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public ResponseEntity<String>badCredentialsException(BadCredentialsException ex){
		
		return new ResponseEntity<String>("BadCredentialsException" + simpleText, HttpStatus.PROXY_AUTHENTICATION_REQUIRED);		
		
	}
	
	
	@ExceptionHandler({
		jakarta.validation.ConstraintViolationException.class,// <== Called by Hibernate after check class/attributes annotations 
		org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException.class})		
	public ResponseEntity<String> jakartaValidationConstraintViolationException(Exception ex){
		
		return new ResponseEntity<String>("ConstraintViolationException\n\n" + ex.getMessage() + "\n", HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
