package contrat;

import Decorator.EnvironmentDecorator;
import itf.EnvironmentService;

public class EnvironmentContrat extends EnvironmentDecorator{
	
	protected EnvironmentContrat(EnvironmentService service) {
		super(service);
	}

	public void checkInvariant() {
		//TODO
	}
}
