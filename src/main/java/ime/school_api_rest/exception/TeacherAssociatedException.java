package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class TeacherAssociatedException extends GeneralException {	
	
	private static final long serialVersionUID = -1312924661868122924L;

	public TeacherAssociatedException(Long identifier) {

		super("TeacherAssociatedException",
				"Some Teacher is still associated in the element",
				identifier);
	}
}