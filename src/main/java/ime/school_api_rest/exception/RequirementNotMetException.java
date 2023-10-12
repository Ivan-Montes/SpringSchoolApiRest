package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_REQUIRED)
public class RequirementNotMetException extends GeneralException{

	private static final long serialVersionUID = -8171574238455426093L;

	public RequirementNotMetException(Long identifier) {
		
		super("RequirementNotMet",
				"Requirement Not Met",
				identifier);
	}
}