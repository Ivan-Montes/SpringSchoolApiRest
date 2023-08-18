package ime.SchoolApiRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class TeacherAssociatedException extends GeneralException {	
	
	private static final long serialVersionUID = -1312924661868122924L;

	public TeacherAssociatedException(Long identifier) {
		super();
		this.setName("TeacherAssociatedException");
		this.setMessage("Some Teacher is still associated in the element");
		this.setIdentifier(identifier);
	}
}
