package contrat;

import java.util.ArrayList;
import java.util.Set;

import Decorator.EngineDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.GuardService;
import itf.HoleService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.Item;
import itf.Move;
import itf.PlayerService;

public class EngineContrat extends EngineDecorator{

	public EngineContrat(EngineService service) {
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
		
		//capture
		ArrayList<Move> commands_at_Pre = new ArrayList<>();
		for(int i = 0; i < super.getCommands().size(); i++)
			commands_at_Pre.add(super.getCommands().get(i));

		
		super.addCommand(m);
		
		for(int i = 0; i < commands_at_Pre.size(); i++)
			if(! (commands_at_Pre.get(i) == super.getCommands().get(i))) {
				throw new PostconditionError("addCommand modifie les commandes deja presentes dans la liste");
			}
		
		if(! (super.getCommands().get(super.getCommands().size()-1) == m)) {
			throw new PostconditionError("addCommand ne place pas la nouvelle commande en fin de liste");
		}
	}

	@Override
	public void addNewHole(HoleService h) {
		//capture
		ArrayList<HoleService> holes_at_Pre = new ArrayList<>();
		holes_at_Pre.addAll(super.getHoles());
		
		super.addNewHole(h);
		
		if(! (super.getHoles().containsAll(holes_at_Pre))) {
			throw new PostconditionError("addNewHole modifie les Hole deja presents dans la liste");
		}
		
		if(! (super.getHoles().contains(h))) {
			throw new PostconditionError("addNewHole n'ajoute pas le nouveau Hole a la liste");
		}
	}

	@Override
	public void reset() {
		
		//capture
		boolean key_isOnFloor_at_Pre = super.getKey().isOnFloor();
		int key_X_at_Pre = super.getKey().getX();
		int key_Y_at_Pre = super.getKey().getY();
		
		super.reset();
		
		EnvironmentService env = super.getEnvi();
		
		for(int x = 0; x < env.getWidth(); x++) {
			for(int y = 0; y < env.getHeight(); y++) {
				if(! (env.cellNature(x, y) != Cell.HOL)) {
					throw new PostconditionError("reset n'a pas rebouche tous les trous");
				}
			}
		}
		
		for(Item t : super.getTreasures()) {
			if(! (t.isOnFloor() == true)) {
				throw new PostconditionError("reset ne remet pas un tresor au sol");
			}
			if(! (t.getX() == t.getInitialX() && t.getY() == t.getInitialY())) {
				throw new PostconditionError("reset ne reinitialise pas la position d'un tresor");
			}
		}
		
		if(! (super.getKey().isOnFloor() == false)) {
			throw new PostconditionError("reset ne retire pas la cle du sol");
		}
		
		if(! (super.getKey().getX() == super.getKey().getInitialX() && super.getKey().getY() == super.getKey().getInitialY())) {
			throw new PostconditionError("reset ne reinitialise pas la position de la cle");
		}
		
		if(key_isOnFloor_at_Pre) {
			if(env.hasItem(key_X_at_Pre, key_Y_at_Pre)) {
				if(! (env.getItem(key_X_at_Pre, key_X_at_Pre) != super.getKey())) {
					throw new PostconditionError("reset ne retire pas la cle du contenu de sa cellule");
				}
			}
		}
		
	}

	@Override
	public void step() {
		
		//capture
		boolean nextMap = super.getPlayer().hasKey() && super.getEnvi().cellNature(super.getPlayer().getWdt(), super.getPlayer().getHgt()) == Cell.DOR;
		int playerNBTreasure_at_Pre = super.getPlayer().getNbTreasure();
		int score_at_pre = super.getScore();
		
		super.step();
			
		if(nextMap) {
			System.out.println(super.getScore());
			if(! (super.getScore() == score_at_pre + (50*playerNBTreasure_at_Pre))) {
				throw new PostconditionError("step le score n'est pas modifie correctement");
			}
			if(! (super.getCommands().isEmpty())) {
				throw new PostconditionError("step la liste de commande n'est pas videe");
			}
			if(! (super.getHoles().isEmpty())) {
				throw new PostconditionError("step la liste de Hole n'est pas videe");
			}
			for(Item t : super.getTreasures()) {
				if(! (t.getX() == t.getInitialX() && t.getY() == t.getInitialY() && t.isOnFloor() == true)) {
					throw new PostconditionError("step un tresor n'est pas a sa position initiale");
				}
			}
			if(! (super.getKey().isOnFloor() == false && super.getKey().getX() == super.getKey().getInitialX() 
					&& super.getKey().getY() == super.getKey().getInitialY())) {
				throw new PostconditionError("step key n'est pas reset correctement");
			}
			if(! (super.getPlayer().getNbTreasure() == 0)) {
				throw new PostconditionError("step le nombre de tresor du joueur n'est pas mit a 0");
			}
			if(! (super.getPlayer().hasKey() == false)) {
				throw new PostconditionError("step le joueur a une cle");
			}
		}
		
		boolean noTreasureOnFloor = true;
		for(Item t : super.getTreasures())
			if(t.isOnFloor() == true) {
				noTreasureOnFloor = false;
				break;
			}
		
		Item key = super.getKey();
		boolean keyIn = false;
		if(super.getEnvi().hasItem(key.getX(), key.getY()) && super.getEnvi().getItem(key.getX(), key.getY()) == key) {
			keyIn = true;
		}
		
		if(noTreasureOnFloor == true) {
			if(! ( (key.isOnFloor() && keyIn) || (key.isOnFloor() == false && keyIn == false) && 
					super.getEnvi().cellNature(super.doorX(), super.doorY()) == Cell.DOR)) {
				throw new PostconditionError("step tous les tresors sont recuperes mais la porte et/ou la cle ne sont pas la");
			}
		}	
	}	
}
