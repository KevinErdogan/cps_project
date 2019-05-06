package itf;

import java.util.List;
import java.util.Set;

import display.Display;

public interface EngineService {
	
	public EnvironmentService getEnvi();
	public PlayerService getPlayer();
	public Set<GuardService> getGuards();
	public Set<Item> getTreasures();
	public Item getKey();
	public Display getStatus();
	public Move getNextCommand();
	public List<Move> getCommands();
	public List<HoleService> getHoles();
	
	/*
	 * TODO spec sur les trous t == 15 -> fill le trou et le trou n'existe plus dans getHoles
	 */
	public void step();
	
	/*
	 * \post: getEnvi() == es
	 * \post: getPlayer() == player
	 * \post: forall Chararcter g in guards, getGuards() contains g 
	 * \post: forall Treasure t in treasures, getTreasures() contains t
	 * \post: getKey() == key
	 */
	public void init(EnvironmentService es, PlayerService player, Set<GuardService> guards, Set<Item> treasures, Item key);
	
	public void initFirstMap();
	
	public void initMap1();
	public void initMap2();
	public void initMap3();
	
	/*
	 * \post: getNextCommand() == m
	 */
	public void addCommand(Move m);
	
	/*
	 * \post: getHoles() contains h
	 */
	public void addNewHole(HoleService h);
	
	public void reset();
	
	public void setDisplayOn(boolean displayOn);
	
	public GameState getGameState();
}
