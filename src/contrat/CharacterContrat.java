package contrat;

import Decorator.CharacterDecorator;
import itf.CharacterService;

public class CharacterContrat extends CharacterDecorator{

	protected CharacterContrat(CharacterService service) {
		super(service);
	}

	public void checkInvariant() {
		
	}
	
}
