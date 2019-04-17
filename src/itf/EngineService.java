package itf;

import java.util.Set;

import display.Display;

public interface EngineService {
	
	public EnvironmentService getEnvi();
	public PlayerService getPlayer();
	public Set<CharacterService> getGuards();
	//public Set<Treasure> getTreasures();
	public Display getStatus();
	public Move getNextCommand();
	
	public void step();
	public void init(EnvironmentService es, PlayerService player, Set<CharacterService> guards);
	
	/*
	 * TODO
	 */
	public void initWithTxt(String file);
	
	/*
	 * TODO
	 */
	public void addCommand(Move m);
}
