package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class SubjectAssociatedException extends GeneralException {
	
	private static final long serialVersionUID = -4415543423472297913L;

	public SubjectAssociatedException(Long identifier) {
		
		super("SubjectAssociatedException",
				"Some Subject is still associated in the element",
				identifier);
	}
}
