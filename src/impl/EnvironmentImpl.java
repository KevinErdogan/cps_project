package impl;

import java.util.HashSet;
import java.util.Set;


import itf.Content;
import itf.EnvironmentService;

public class EnvironmentImpl extends EditableScreenImpl implements EnvironmentService{

	protected Set<Content>[][] contents;
	
	public EnvironmentImpl(int h, int w) {
		super(h, w);
		this.init(h, w);
	}

	@Override
	public Set<Content> cellContent(int x, int y) {
		return contents[x][y];
	}
	
	@Override
	public void init(int h, int w) {
		super.init(h, w);
		contents = new HashSet<Content>[h][w];
	}

}
