package contrat;

import Decorator.EngineDecorator;
import itf.EngineService;

public class EngineContrat extends EngineDecorator{

	protected EngineContrat(EngineService service) {
		super(service);
	}
}
