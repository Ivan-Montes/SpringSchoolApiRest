package ime.school_api_rest.exception;

public class RequirementNotMetException extends GeneralException{

	private static final long serialVersionUID = -8171574238455426093L;

	public RequirementNotMetException(Long identifier) {
		
		super("RequirementNotMet",
				"Requirement Not Met",
				identifier);
	}
}