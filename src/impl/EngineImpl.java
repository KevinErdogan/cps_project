package impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import display.Display;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EngineService;
import itf.EnvironmentService;
import itf.GameState;
import itf.GuardService;
import itf.HoleService;
import itf.Item;
import itf.ItemType;
import itf.Move;
import itf.PlayerService;
import maps.MAPS;

public class EngineImpl implements EngineService{
	private GameState gameState;
	private EnvironmentService es;
	private PlayerService player;
	private Set<GuardService> guards;//remplacer plus tard par GuardService
	private Display display;
	private List<Move> commands;
	private boolean displayOn = true;
	private List<HoleService> holes;
	private Set<Item> treasures;
	private Item key;
	private int doorX;
	private int doorY;
	private static MAPS maps = new MAPS();
	private int currentMap;
	private int gameScore;

	public EngineImpl() {
		es = null;
		player = null;
		guards = null;
		display = null;
		commands = null;
		holes = null;
		treasures = null;
		gameState = null;
	}

	@Override
	public EnvironmentService getEnvi() {
		return es;			
	}

	@Override
	public PlayerService getPlayer() {
		return player;
	}

	@Override
	public Set<GuardService> getGuards() {
		return guards;
	}

	@Override
	public Display getStatus() {
		return display;
	}

	@Override
	public Move getNextCommand() {
		if(this.commands.size() != 0) {
			return commands.remove(commands.size()-1);
		}

		return Move.Neutral;
	}

	public void addCommand(Move m) {
		this.commands.add(m);
	}

	@Override
	public void step() {
		boolean gameover = false;
		if(player.hasKey() && getEnvi().cellNature(player.getWdt(), player.getHgt()) == Cell.DOR) {
			if(currentMap == 3) {
				this.gameScore += treasures.size()*50;
				this.commands.clear();
				this.holes.clear();
				initMap1();
			}else {
				if(currentMap == 1) {
					this.gameScore += treasures.size()*50;
					this.commands.clear();
					this.holes.clear();
					initMap2();
				}else if (currentMap == 2) {
					this.gameScore += treasures.size()*50;
					this.commands.clear();
					this.holes.clear();
					initMap3();
				}
			}
		}
		if(getEnvi().hasItem(player.getWdt(), player.getHgt())) {
			Item t = getEnvi().getItem(player.getWdt(), player.getHgt());
			getEnvi().getOut(t, t.getX(), t.getY());
			t.setOnFloor(false);
			if(t.getItemType() == ItemType.Treasure){
				player.pickUpTreasure();
				if(player.getNbTreasure() == treasures.size()) {
					key.setOnFloor(true);
					getEnvi().getIn(key, key.getX(), key.getY());
					getEnvi().setNature(doorX, doorY, Cell.DOR);
				}
			}else if(t.getItemType() == ItemType.Key) {
				player.pickUpKey(t);
			}
			
		}
		for(GuardService guard : guards) {
			if(guard.hasItem() == false  && getEnvi().hasItem(guard.getWdt(), guard.getHgt())) {
				Item t = getEnvi().getItem(guard.getWdt(), guard.getHgt());
				getEnvi().getOut(t, t.getX(), t.getY());
				t.setOnFloor(false);
				guard.pickUpItem(t);
			}else if(guard.hasItem() == true && getEnvi().cellNature(guard.getWdt(), guard.getHgt()) == Cell.HOL
					&& getEnvi().hasItem(guard.getWdt(), guard.getHgt()+1) == false) {
				Item t = guard.getItem();
				getEnvi().getIn(t, guard.getWdt(), guard.getHgt()+1);
				t.setX(guard.getWdt());
				t.setY(guard.getHgt()+1);
				t.setOnFloor(true);
				guard.dropItem();
			}
		}
		

		 player.step();
		 forg:
		 for(CharacterService guard : guards) {
			 guard.step();
			 if(player.getWdt() == guard.getWdt() && player.getHgt() == guard.getHgt()) {
				 player.die();
				 if(player.getHP() == 0) {
					 gameover = true;
				 }
				 for(CharacterService g : guards)
					g.reset();
				 this.reset();
				 break forg;
			 }
		 }

		 List<HoleService> toRemove = new ArrayList<>();
		 forhole:
		 for(HoleService h : this.holes) {
			 h.step();
			 if(h.getTime() == 15) {
				 getEnvi().fill(h.getX(), h.getY());
				 toRemove.add(h);
				 for(Content c: getEnvi().cellContent(h.getX(), h.getY())) {
					 if(c.isCharacter()) {
						 CharacterService character = c.getCharacter();
						 if(guards.contains(character)) {
							 character.die();
						 }else if(character == player){
							 player.die();
							 if(player.getHP() == 0) {
								
								 gameover = true;
								 
							 }
							 for(CharacterService g : guards)
								g.reset();
							 toRemove.clear();
							 this.reset();
							 break forhole;
						 }
					 }
				 }
			 }
		 }
		 this.holes.removeAll(toRemove);
		 
		 if(gameover == true) {
			 System.out.println("GAME OVER");
			 System.out.println("Your score is "+gameScore);
			 System.out.println("Starting new game");
			 this.holes.clear();
			 this.commands.clear();
			 initMap1();
			 player.resetHP();
			 gameScore = 0;
			 
		 }
		 
		if(displayOn) {
			display.step();
		}
	}
	
	public void reset() {
		for(HoleService h : this.holes) {
			getEnvi().fill(h.getX(), h.getY());
		}
		this.holes.clear();
		for(Item t : treasures) {
			getEnvi().getOut(t, t.getX(), t.getY());
			t.setX(t.getInitialX());
			t.setY(t.getInitialY());
			getEnvi().getIn(t, t.getX(), t.getY());
			t.setOnFloor(true);
		}
		if(key.isOnFloor())
			getEnvi().getOut(key, key.getX(), key.getY());
		key.setX(key.getInitialX());
		key.setY(key.getInitialY());
		key.setOnFloor(false);
		getEnvi().setNature(doorX, doorY, Cell.EMP);
	}

	@Override			
	public void init(EnvironmentService es, PlayerService player, Set<GuardService> guards, Set<Item> treasures, Item key) {
		this.gameState = GameState.PLAYING;
		this.es=es;
		this.player=player;	
		this.es.getIn(player, player.getWdt(), player.getHgt());
		this.guards=guards;
		
		if(displayOn) {
			display = new Display(this);
		}else {
			display = null;
		}
		commands = new ArrayList<Move>();
		holes = new ArrayList<HoleService>();
		this.treasures = treasures;
		for(Item t : treasures){
			getEnvi().getIn(t, t.getX(), t.getY());
		}
		this.key = key;
		this.gameScore = 0;
	}

	public void initFirstMap() {	
		currentMap = 1;
		int w = maps.map1[0].length;
		int h = maps.map1.length;
		EnvironmentService envs = new EnvironmentImpl();
		
		envs.init(w, h);
		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int cellN = maps.map1[y][x];
				Cell cell = null;
				switch(cellN) {
					case 0:
						cell = Cell.EMP;
						break;
					case 1:
						cell = Cell.PLT;
						break;
					case 2:
						cell = Cell.MTL;
						break;
					case 3:
						cell = Cell.LAD;
						break;
					case 4:
						cell = Cell.HDR;
						break;
					default:
						System.out.println("Format incorrect");
				}
				envs.setNature(x, y, cell);
			}
		}

		int nbGuards = maps.guardMap1.size();
		int x = maps.playerMap1.getX();
		int y = maps.playerMap1.getY();

		PlayerService pl = new PlayerImpl();
		pl.init(x, y, envs, this);

		Set<GuardService> lguards = new HashSet<GuardService>();
		int id = 1;
		for(int i = 0; i < nbGuards; i++) {
			x = maps.guardMap1.get(i).getX();
			y = maps.guardMap1.get(i).getY();
			GuardService guard = new GuardImpl();
			guard.init(envs, x, y, pl, id);
			lguards.add(guard);
		}
		
		int nbTreasure = maps.treasureMap1.size();
		Set<Item> treasures = new HashSet<>();
		for(int i = 0; i < nbTreasure; i++) {
			x = maps.treasureMap1.get(i).getX();
			y = maps.treasureMap1.get(i).getY();
			Item treasure = new ItemImpl(x, y, ItemType.Treasure, true);
			treasures.add(treasure);
		}
		
		x = maps.keyMap1.getX();
		y = maps.keyMap1.getY();
		Item key = new ItemImpl(x, y, ItemType.Key, false);
		
		doorX = maps.doorMap1.getX();
		doorY = maps.doorMap1.getY();
		
		this.init(envs, pl, lguards, treasures, key);

	}
	
	public void initMap1() {
		currentMap = 1;
		int w = maps.map1[0].length;
		int h = maps.map1.length;

		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int cellN = maps.map1[y][x];
				Cell cell = null;
				switch(cellN) {
					case 0:
						cell = Cell.EMP;
						break;
					case 1:
						cell = Cell.PLT;
						break;
					case 2:
						cell = Cell.MTL;
						break;
					case 3:
						cell = Cell.LAD;
						break;
					case 4:
						cell = Cell.HDR;
						break;
					default:
						System.out.println("Format incorrect");
				}
				getEnvi().setNature(x, y, cell);
			}
		}

		int nbGuards = maps.guardMap1.size();
		int x = maps.playerMap1.getX();
		int y = maps.playerMap1.getY();

		player.setXandInitialX(x);
		player.setYandInitialY(y);
		player.resetNbTreasure();
		player.resetKey();
		
		for(GuardService g : guards) {
			getEnvi().getOut(g, g.getWdt(), g.getHgt());
		}
		guards.clear();
		
		int id = 1;
		for(int i = 0; i < nbGuards; i++) {
			x = maps.guardMap1.get(i).getX();
			y = maps.guardMap1.get(i).getY();
			GuardService guard = new GuardImpl();
			guard.init(getEnvi(), x, y, player, id);
			guards.add(guard);
		}
		
		int nbTreasure = maps.treasureMap1.size();
		
		for(Item t : treasures) {
			if(t.isOnFloor())
				getEnvi().getOut(t, t.getX(), t.getY());
		}
		treasures.clear();
		
		for(int i = 0; i < nbTreasure; i++) {
			x = maps.treasureMap1.get(i).getX();
			y = maps.treasureMap1.get(i).getY();
			Item treasure = new ItemImpl(x, y, ItemType.Treasure, true);
			getEnvi().getIn(treasure, x, y);
			treasures.add(treasure);
		}
		
		x = maps.keyMap1.getX();
		y = maps.keyMap1.getY();
		if(key.isOnFloor())
			getEnvi().getOut(key, key.getX(), key.getY());
		this.key = new ItemImpl(x, y, ItemType.Key, false);
		
		doorX = maps.doorMap1.getX();
		doorY = maps.doorMap1.getY();
	}
	
	public void initMap2() {	
		currentMap = 2;
		int w = maps.map2[0].length;
		int h = maps.map2.length;

		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int cellN = maps.map2[y][x];
				Cell cell = null;
				switch(cellN) {
					case 0:
						cell = Cell.EMP;
						break;
					case 1:
						cell = Cell.PLT;
						break;
					case 2:
						cell = Cell.MTL;
						break;
					case 3:
						cell = Cell.LAD;
						break;
					case 4:
						cell = Cell.HDR;
						break;
					default:
						System.out.println("Format incorrect");
				}
				getEnvi().setNature(x, y, cell);
			}
		}

		int nbGuards = maps.guardMap2.size();
		int x = maps.playerMap2.getX();
		int y = maps.playerMap2.getY();

		player.setXandInitialX(x);
		player.setYandInitialY(y);
		player.resetNbTreasure();
		player.resetKey();
		
		for(GuardService g : guards) {
			getEnvi().getOut(g, g.getWdt(), g.getHgt());
		}
		guards.clear();
		
		int id = 1;
		for(int i = 0; i < nbGuards; i++) {
			x = maps.guardMap2.get(i).getX();
			y = maps.guardMap2.get(i).getY();
			GuardService guard = new GuardImpl();
			guard.init(getEnvi(), x, y, player, id);
			guards.add(guard);
		}
		
		int nbTreasure = maps.treasureMap2.size();
		
		for(Item t : treasures) {
			if(t.isOnFloor())
				getEnvi().getOut(t, t.getX(), t.getY());
		}
		treasures.clear();
		
		for(int i = 0; i < nbTreasure; i++) {
			x = maps.treasureMap2.get(i).getX();
			y = maps.treasureMap2.get(i).getY();
			Item treasure = new ItemImpl(x, y, ItemType.Treasure, true);
			getEnvi().getIn(treasure, x, y);
			treasures.add(treasure);
		}
		
		x = maps.keyMap2.getX();
		y = maps.keyMap2.getY();
		if(key.isOnFloor())
			getEnvi().getOut(key, key.getX(), key.getY());
		this.key = new ItemImpl(x, y, ItemType.Key, false);
		
		doorX = maps.doorMap2.getX();
		doorY = maps.doorMap2.getY();
	}
	
	public void initMap3() {	
		currentMap = 3;
		int w = maps.map3[0].length;
		int h = maps.map3.length;

		for(int y = 0; y < h; y++) {
			for(int x = 0; x < w; x++) {
				int cellN = maps.map3[y][x];
				Cell cell = null;
				switch(cellN) {
					case 0:
						cell = Cell.EMP;
						break;
					case 1:
						cell = Cell.PLT;
						break;
					case 2:
						cell = Cell.MTL;
						break;
					case 3:
						cell = Cell.LAD;
						break;
					case 4:
						cell = Cell.HDR;
						break;
					default:
						System.out.println("Format incorrect");
				}
				getEnvi().setNature(x, y, cell);
			}
		}

		int nbGuards = maps.guardMap3.size();
		int x = maps.playerMap3.getX();
		int y = maps.playerMap3.getY();

		player.setXandInitialX(x);
		player.setYandInitialY(y);
		player.resetNbTreasure();
		player.resetKey();
		
		for(GuardService g : guards) {
			getEnvi().getOut(g, g.getWdt(), g.getHgt());
		}
		guards.clear();
		
		int id = 1;
		for(int i = 0; i < nbGuards; i++) {
			x = maps.guardMap3.get(i).getX();
			y = maps.guardMap3.get(i).getY();
			GuardService guard = new GuardImpl();
			guard.init(getEnvi(), x, y, player, id);
			guards.add(guard);
		}
		
		int nbTreasure = maps.treasureMap3.size();
		
		for(Item t : treasures) {
			if(t.isOnFloor())
				getEnvi().getOut(t, t.getX(), t.getY());
		}
		treasures.clear();
		
		for(int i = 0; i < nbTreasure; i++) {
			x = maps.treasureMap3.get(i).getX();
			y = maps.treasureMap3.get(i).getY();
			Item treasure = new ItemImpl(x, y, ItemType.Treasure, true);
			getEnvi().getIn(treasure, x, y);
			treasures.add(treasure);
		}
		
		x = maps.keyMap3.getX();
		y = maps.keyMap3.getY();
		if(key.isOnFloor())
			getEnvi().getOut(key, key.getX(), key.getY());
		this.key = new ItemImpl(x, y, ItemType.Key, false);
		
		doorX = maps.doorMap3.getX();
		doorY = maps.doorMap3.getY();
		

	}

	@Override
	public List<Move> getCommands() {
		return this.commands;
	}

	public void setDisplayOn(boolean displayOn) {
		this.displayOn = displayOn;
	}

	@Override
	public Set<Item> getTreasures() {
		return this.treasures;
	}

	@Override
	public List<HoleService> getHoles() {
		return this.holes;
	}

	@Override
	public void addNewHole(HoleService h) {
		this.holes.add(h);
	}

	@Override
	public Item getKey() {
		return this.key;
	}

	public GameState getGameState() {
		return gameState;
	}

	@Override
	public int getScore() {
		return this.gameScore;
	}

	@Override
	public int doorX() {
		return doorX;
	}

	@Override
	public int doorY() {
		return doorY;
	}

	
	
	
}
