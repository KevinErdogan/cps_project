package contrat;

import java.util.Set;

import itf.Cell;
import itf.Content;
import itf.EnvironmentService;

public class EnvironmentDecorator implements EnvironmentService{

	private EnvironmentService delegate;
	
	protected EnvironmentDecorator(EnvironmentService delegate) {
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

}
