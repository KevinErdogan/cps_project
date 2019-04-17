package Decorator;

import itf.Cell;
import itf.ScreenService;

public class ScreenDecorator implements ScreenService{
	private ScreenService delegate;

	protected ScreenDecorator(ScreenService delegate) {
		this.delegate = delegate;
	}	
	
	@Override
	public int getHeight() {
		return delegate.getHeight();
	}

	@Override
	public int getWidth() {
		return delegate.getWidth();
	}

	@Override
	public Cell cellNature(int x, int y) {
		return delegate.cellNature(x, y);
	}

	@Override
	public void init(int w, int h) {
		delegate.init(w, h);
	}

	@Override
	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	@Override
	public void fill(int x, int y) {
		delegate.fill(x, y);
	}

}
