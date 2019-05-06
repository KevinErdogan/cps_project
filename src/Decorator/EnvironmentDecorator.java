package Decorator;

import java.util.Set;

import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;
import itf.Item;

public class EnvironmentDecorator implements EnvironmentService{

	private EnvironmentService delegate;

	protected EnvironmentDecorator(EnvironmentService delegate) {
		this.delegate = delegate;
	}
	
	
	public void getOut(CharacterService c, int x, int y) {
		delegate.getOut(c, x, y);
	}

	public void getIn(CharacterService c, int x, int y) {
		delegate.getIn(c, x, y);
	}
	
	public void getOut(Item i, int x, int y) {
		delegate.getOut(i, x, y);
	}


	public void getIn(Item i, int x, int y) {
		delegate.getIn(i, x, y);
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
	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	@Override
	public Set<Content> cellContent(int x, int y) {
		return delegate.cellContent(x, y);
	}

	@Override
	public void fill(int x, int y) {
		delegate.fill(x, y);
	}
	
	/*@Override
	public void init(EditableScreenService es) {
		delegate.init(es);	
	}*/

	@Override
	public boolean hasCharacter(int x, int y) {
		return delegate.hasCharacter(x, y);
	}

	@Override
	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		delegate.setNature(x, y, c);
	}


	public boolean hasItem(int x, int y) {
		return delegate.hasItem(x, y);
	}


	public Item getItem(int x, int y) {
		return delegate.getItem(x, y);
	}


}
