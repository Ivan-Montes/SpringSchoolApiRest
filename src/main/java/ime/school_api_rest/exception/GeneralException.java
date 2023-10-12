package ime.school_api_rest.exception;


public class GeneralException extends RuntimeException{
	
	private static final long serialVersionUID = -2217904719176665639L;
	
	private final String name;
	private final String message;
	private final Long identifier;
	

	public GeneralException(String name, String message, Long identifier) {
		super();
		this.name = name;
		this.message = message;
		this.identifier = identifier;
	}
	
	public String getName() {
		return name;
	}	
	
	public String getMessage() {
		return message;
	}
	
	public Long getIdentifier() {
		return identifier;
	}	
	
}