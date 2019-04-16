package contrat;

public class PreconditionError extends ContratError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2298674217355248368L;

	public PreconditionError(String message) {
		super("PreCondition failed: "+message);
	}
}
