package contrat;

import java.util.Set;

import Decorator.EngineDecorator;
import itf.CharacterService;
import itf.GuardService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.Item;
import itf.Move;
import itf.PlayerService;

public class EngineContrat extends EngineDecorator{

	protected EngineContrat(EngineService service) {
		super(service);
	}

	@Override
	public void init(EnvironmentService es, PlayerService player, Set<GuardService> guards, Set<Item> treasures, Item key) {
		
		super.init(es, player, guards, treasures, key);
		
		//post: getEnvi() == es
		
		if(! (getEnvi() == es)) {
			throw new PostconditionError("Init Engine, environment non initialise");
		}
		
		//post: getPlayer() == player
		
		if(! (getPlayer() == player)) {
			throw new PostconditionError("Init Engine, player non initialise");
		}
		
		//post: forall Chararcter g in guards, getGuards() contains g
		
		for(CharacterService g : guards) {
			if(!(super.getGuards().contains(g))) {
				throw new PostconditionError("Init Engine, guard absent");
			}
		}
		
		//post: forall Treasure t in treasures, getTreasures() contains t
		for(Item t : treasures) {
			if(!(super.getTreasures().contains(t))){
				throw new PostconditionError("Init Engine, treasure absent");
			}
		}
		
		//post: getKey() == key
		if(! (getKey() == key)) {
			throw new PostconditionError("Init Engine, key non initialise");
		}
		
	}

	@Override
	public void addCommand(Move m) {
		
		super.addCommand(m);
		
		//post: getNextCommand() == m
		
		if(! (super.getCommands().get(super.getCommands().size()-1) == m)) {
			throw new PostconditionError("addCommand ne place pas la nouvelle commande en fin de liste");
		}
	}
	
	
	
}
