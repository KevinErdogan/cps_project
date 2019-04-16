package contrat;

public class PostconditionError extends ContratError{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8935796758470399295L;

	public PostconditionError(String message) {
		super("PostCondition failed: "+message);
	}
}
