package ime.school_api_rest.exception;


public class GeneralException extends RuntimeException{
	
	private static final long serialVersionUID = -2217904719176665639L;
	
	private final String name;
	private final String messageInfo;
	private final Long identifier;
	

	public GeneralException(String name, String message, Long identifier) {
		super();
		this.name = name;
		this.messageInfo = message;
		this.identifier = identifier;
	}
	
	public String getName() {
		return name;
	}	
	
	public String getMessageInfo() {
		return messageInfo;
	}
	
	public Long getIdentifier() {
		return identifier;
	}	
	
}