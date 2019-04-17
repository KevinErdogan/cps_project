package Decorator;

import java.util.Set;

import display.Display;
import itf.CharacterService;
import itf.EngineService;
import itf.EnvironmentService;
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
	public Set<CharacterService> getGuards() {
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
	public void init(EnvironmentService es, PlayerService player, Set<CharacterService> guards) {
		delegate.init(es, player, guards);
	}

	@Override
	public void initWithTxt(String file) {
		delegate.initWithTxt(file);
	}

	@Override
	public void addCommand(Move m) {
		delegate.addCommand(m);
	}

}
