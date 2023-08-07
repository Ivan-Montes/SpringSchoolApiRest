package ime.SchoolApiRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class SubjectAssociatedException extends GeneralException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4415543423472297913L;

	public SubjectAssociatedException(Long identifier) {
		super();
		this.setName("SubjectAssociatedException");
		this.setMessage("Some Subject is still associated in the Teacher");
		this.setIdentifier(identifier);
	}
}
