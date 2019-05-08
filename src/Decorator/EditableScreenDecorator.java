package Decorator;

import itf.Cell;
import itf.EditableScreenService;

public class EditableScreenDecorator extends ScreenDecorator implements EditableScreenService{
	private EditableScreenService delegate;
	
	public int getHeight() {
		return delegate.getHeight();
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public Cell cellNature(int x, int y) {
		return delegate.cellNature(x, y);
	}

	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	public void fill(int x, int y) {
		delegate.fill(x, y);
	}

	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	public void setNature(int x, int y, Cell c) {
		delegate.setNature(x, y, c);
	}

	public void init(int w, int h) {
		delegate.init(w, h);
	}

	protected EditableScreenDecorator(EditableScreenService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
 

}
