package itf;

import java.util.List;
import java.util.Set;

import display.Display;

public interface EngineService {
	
	public EnvironmentService getEnvi();
	public PlayerService getPlayer();
	public Set<CharacterService> getGuards();
	//public Set<Treasure> getTreasures();
	public Display getStatus();
	public Move getNextCommand();
	public List<Move> getCommands();
	
	public void step();
	
	/*
	 * \post: getEnvi() == es
	 * \post: getPlayer() == player
	 * \post: forall Chararcter g in guards, getGuards() contains g 
	 */
	public void init(EnvironmentService es, PlayerService player, Set<CharacterService> guards);
	
	/*
	 * TODO
	 */
	public void initWithTxt(String file);
	
	/*
	 * \post: getNextCommand() == m
	 */
	public void addCommand(Move m);
	
	public void setDisplayOn(boolean displayOn);
}
