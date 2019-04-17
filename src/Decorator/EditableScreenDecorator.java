package Decorator;

import itf.Cell;
import itf.EditableScreenService;

public class EditableScreenDecorator implements EditableScreenService{
	private EditableScreenService delegate;
	
	protected EditableScreenDecorator(EditableScreenService delegate) {
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
	public void init(int h, int w) {
		delegate.init(h, w);
	}

	@Override
	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		delegate.setNature(x, y, c);
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
