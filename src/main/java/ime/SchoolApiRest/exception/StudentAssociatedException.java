package ime.SchoolApiRest.exception;

public class StudentAssociatedException extends GeneralException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8569738722832031733L;

	public StudentAssociatedException(Long identifier) {
		super();
		this.setName("StudentAssociatedException");
		this.setMessage("Some Student is still associated in the element");
		this.setIdentifier(identifier);
	}
}
