package contrat;

public class InvariantError extends ContratError{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5203056835942398990L;

	public InvariantError(String message) {
		super("Invariant failed: "+message);
	}
}
