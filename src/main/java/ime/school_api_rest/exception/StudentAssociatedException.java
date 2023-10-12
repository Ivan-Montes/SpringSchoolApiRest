package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class StudentAssociatedException extends GeneralException{
	
	private static final long serialVersionUID = -8569738722832031733L;

	public StudentAssociatedException(Long identifier) {
		
		super("StudentAssociatedException",
				"Some Student is still associated in the element",
				identifier);
	}	
}