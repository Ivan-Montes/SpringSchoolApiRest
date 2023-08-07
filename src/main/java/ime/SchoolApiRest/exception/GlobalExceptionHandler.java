package ime.SchoolApiRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final String simpleText = " _***- Identifier: ";
	
	@ExceptionHandler(ime.SchoolApiRest.exception.ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage() + simpleText + ex.getIdentifier(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ime.SchoolApiRest.exception.SubjectAssociatedException.class)
	public ResponseEntity<String> subjectAssociatedException(SubjectAssociatedException ex){
		return new ResponseEntity<String>(ex.getMessage() + simpleText + ex.getIdentifier(),HttpStatus.PRECONDITION_REQUIRED);
	}
}
