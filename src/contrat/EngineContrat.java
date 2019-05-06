package contrat;

import java.util.Set;

import Decorator.EngineDecorator;
import itf.CharacterService;
import itf.Command;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

public class EngineContrat extends EngineDecorator{

	protected EngineContrat(EngineService service) {
		super(service);
	}

	@Override
	public void init(EnvironmentService es, PlayerService player, Set<CharacterService> guards) {
		
		super.init(es, player, guards);
		
		//post: getEnvi() == es
		
		if(! (getEnvi() == es)) {
			throw new PostconditionError("Init Engine, environment non initialise");
		}
		
		//post: getPlayer() == player
		
		if(! (getPlayer() == player)) {
			throw new PostconditionError("Init Engine, player non initialise");
		}
		
		//post: forall Chararcter g in guards, getGuards() contains g
		
		//TODO 
	}

	@Override
	public void addCommand(Command m) {
		
		super.addCommand(m);
		
		//post: getNextCommand() == m
		
		if(! (super.getCommands().get(super.getCommands().size()-1) == m)) {
			throw new PostconditionError("addCommand ne place pas la nouvelle commande en fin de liste");
		}
	}
	
	
	
}
