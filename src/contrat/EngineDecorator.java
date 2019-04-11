package contrat;

import java.util.Set;

import display.Display;
import itf.CharacterService;
import itf.EngineService;
import itf.Move;
import itf.ScreenService;

public class EngineDecorator implements EngineService{
	private EngineService delegate;
	
	protected EngineDecorator(EngineService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public ScreenService getEnvi() {
		return delegate.getEnvi();
	}

	@Override
	public CharacterService getPlayer() {
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
	public void init() {
		delegate.init();
	}

}
