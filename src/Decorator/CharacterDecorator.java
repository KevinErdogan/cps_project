package Decorator;

import itf.CharacterService;
import itf.EnvironmentService;

public class CharacterDecorator implements CharacterService{
	private CharacterService delegate;
	
	protected CharacterDecorator(CharacterService delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public EnvironmentService getEnvi() {
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
	public void init(EnvironmentService screen, int x, int y) {
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

	@Override
	public void step() {
		delegate.step();
	}
}
