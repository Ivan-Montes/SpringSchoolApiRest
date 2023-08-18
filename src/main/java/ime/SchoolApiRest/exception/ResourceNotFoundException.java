package ime.SchoolApiRest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GeneralException{
	
	private static final long serialVersionUID = 2238013027986946420L;

	public ResourceNotFoundException(Long identifier) {
		super();
		this.setName("ResourceNotFoundException");
		this.setMessage("Resource impossible to find");
		this.setIdentifier(identifier);
	}
	
}
