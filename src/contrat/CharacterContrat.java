package contrat;

import itf.CharacterService;

public class CharacterContrat extends CharacterDecorator{

	protected CharacterContrat(CharacterService service) {
		super(service);
	}

	public void checkInvariant() {
		//TODO
	}
	
}
