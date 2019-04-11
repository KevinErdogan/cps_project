package contrat;

import itf.EditableScreenService;

public class EditableScreenContrat extends EditableScreenDecorator{
	
	protected EditableScreenContrat(EditableScreenService service) {
		super(service);
	}

	public void checkInvariant() {
		//TODO
	}
}
