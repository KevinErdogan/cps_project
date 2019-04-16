package contrat;

import Decorator.ScreenDecorator;
import itf.ScreenService;

public class ScreenContrat extends ScreenDecorator{

	protected ScreenContrat(ScreenService service) {
		super(service);
	}

}
