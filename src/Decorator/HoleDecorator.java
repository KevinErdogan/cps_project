package Decorator;

import itf.HoleService;

public class HoleDecorator implements HoleService{
	private HoleService delegate;
	
	public HoleDecorator(HoleService delegate) {
		this.delegate = delegate;
	}
	
	public int getX() {
		return delegate.getX();
	}

	public int getY() {
		return delegate.getY();
	}

	public int getTime() {
		return delegate.getTime();
	}

	public void init(int x, int y) {
		delegate.init(x, y);
	}

	public void step() {
		delegate.step();
	}
}
