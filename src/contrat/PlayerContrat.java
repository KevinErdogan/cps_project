package contrat;

import Decorator.PlayerDecorator;
import itf.PlayerService;

public class PlayerContrat extends PlayerDecorator{

	protected PlayerContrat(PlayerService service) {
		super(service);
	}

}
