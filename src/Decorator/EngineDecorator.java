package Decorator;

import java.util.List;
import java.util.Set;

import display.Display;
import itf.CharacterService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.GameState;
import itf.GuardService;
import itf.HoleService;
import itf.Item;
import itf.Move;
import itf.PlayerService;

public class EngineDecorator implements EngineService{
	private EngineService delegate;
	
	

	protected EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public EnvironmentService getEnvi() {
		return delegate.getEnvi();
	}

	@Override
	public PlayerService getPlayer() {
		return delegate.getPlayer();
	}

	@Override
	public Set<GuardService> getGuards() {
		return delegate.getGuards();
	}

	@Override
	public Display getStatus() {
		return delegate.getStatus();
	}

	@Override
	public Move getNextCommand() {
		return delegate.getNextCommand();
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public void init(EnvironmentService es, PlayerService player, Set<GuardService> guards, Set<Item> treasures,
			Item key) {
		delegate.init(es, player, guards, treasures, key);
	}

	@Override
	public void initMap1() {
		delegate.initMap1();
	}
	
	@Override
	public void initMap2() {
		delegate.initMap2();
	}
	
	@Override
	public void initMap3() {
		delegate.initMap3();
	}

	@Override
	public void addCommand(Move m) {
		delegate.addCommand(m);
	}

	@Override
	public List<Move> getCommands() {
		return delegate.getCommands();
	}

	@Override
	public void setDisplayOn(boolean displayOn) {
		delegate.setDisplayOn(displayOn);
	}

	@Override
	public Set<Item> getTreasures() {
		return delegate.getTreasures();
	}

	@Override
	public List<HoleService> getHoles() {
		return delegate.getHoles();
	}
	
	public void addNewHole(HoleService h) {
		delegate.addNewHole(h);
	}

	public Item getKey() {
		return delegate.getKey();
	}

	@Override
	public void reset() {
		delegate.reset();
	}

	public GameState getGameState() {
		return delegate.getGameState();
	}

	public void initFirstMap() {
		delegate.initFirstMap();
	}
	
	
}
