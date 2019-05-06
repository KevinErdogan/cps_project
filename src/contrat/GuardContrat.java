package contrat;

import Decorator.GuardDecorator;
import itf.GuardService;

public class GuardContrat extends GuardDecorator{

	protected GuardContrat(GuardService service) {
		super(service);
	}
	
	

}
