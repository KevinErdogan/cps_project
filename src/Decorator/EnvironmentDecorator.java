package Decorator;

import java.util.Set;

import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;
import itf.Item;

public class EnvironmentDecorator extends EditableScreenDecorator implements EnvironmentService{

	private EnvironmentService delegate;

	public int getHeight() {
		return delegate.getHeight();
	}

	public int getWidth() {
		return delegate.getWidth();
	}

	public Cell cellNature(int x, int y) {
		return delegate.cellNature(x, y);
	}

	public boolean isPlayable() {
		return delegate.isPlayable();
	}

	public void setNature(int x, int y, Cell c) {
		delegate.setNature(x, y, c);
	}

	public void dig(int x, int y) {
		delegate.dig(x, y);
	}

	public void fill(int x, int y) {
		delegate.fill(x, y);
	}

	public Set<Content> cellContent(int x, int y) {
		return delegate.cellContent(x, y);
	}

	public void init(int w, int h) {
		delegate.init(w, h);
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

	public boolean hasCharacter(int x, int y) {
		return delegate.hasCharacter(x, y);
	}

	public boolean hasItem(int x, int y) {
		return delegate.hasItem(x, y);
	}

	public Item getItem(int x, int y) {
		return delegate.getItem(x, y);
	}

	protected EnvironmentDecorator(EnvironmentService delegate) {
		super(delegate);
		this.delegate = delegate;
	}
	



}
