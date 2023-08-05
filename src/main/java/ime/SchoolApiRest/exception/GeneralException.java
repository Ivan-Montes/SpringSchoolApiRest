package ime.SchoolApiRest.exception;


public class GeneralException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2217904719176665639L;
	
	private String name;
	private String message;
	private Long identifier;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}
	
	
}
