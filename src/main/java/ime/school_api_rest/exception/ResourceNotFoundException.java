package ime.school_api_rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GeneralException{
	
	private static final long serialVersionUID = 2238013027986946420L;

	public ResourceNotFoundException(Long identifier) {

		super("ResourceNotFoundException",
				"Resource impossible to find",
				identifier);
	}	
}