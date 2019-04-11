package contrat;

import itf.EngineService;
import itf.PlayerService;
import itf.ScreenService;

public class PlayerDecorator implements PlayerService{
	private PlayerService delegate;

	protected PlayerDecorator(PlayerService delegate) {
		this.delegate = delegate;
	}	

	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public ScreenService getEnvi() {
		return delegate.getEnvi();
	}

	@Override
	public int getHgt() {
		return delegate.getHgt();
	}

	@Override
	public int getWdt() {
		return delegate.getWdt();
	}

	@Override
	public void init(ScreenService screen, int x, int y) {
		delegate.init(screen, x, y);
	}

	@Override
	public void goLeft() {
		delegate.goLeft();
	}

	@Override
	public void goRight() {
		delegate.goRight();
	}

	@Override
	public void goUp() {
		delegate.goUp();
	}

	@Override
	public void goDown() {
		delegate.goDown();
	}
}
