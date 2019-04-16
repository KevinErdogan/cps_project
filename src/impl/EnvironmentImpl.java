package impl;

import java.util.HashSet;
import java.util.Set;


import itf.Content;
import itf.EditableScreenService;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(int h, int w) {
		super.init(h, w);
		contents = (HashSet<Content>[][]) new HashSet[w][h]; //new HashSet<Content>[h][w];
	}
	
	public boolean hasCharacter(int x, int y) {
		for(Content c : contents[x][y]) {
			if(c.isCharacter())
				return false;
		}
		return true;
	}

	/*@Override
	public void init(EditableScreenService es) {
		super.init(h, w);
		
	}*/

}
