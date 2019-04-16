package itf;

import java.util.Set;

import display.Display;

public interface EngineService {
	
	public ScreenService getEnvi();
	public CharacterService getPlayer();
	public Set<CharacterService> getGuards();
	//public Set<Treasure> getTreasures();
	public Display getStatus();
	public Move getNextCommand();
	
	public void step();
	public void init();
}
