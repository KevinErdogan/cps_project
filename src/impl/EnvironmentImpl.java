package impl;

import java.util.HashSet;
import java.util.Set;


import itf.Content;
import itf.EditableScreenService;
import itf.EnvironmentService;

public class EnvironmentImpl extends EditableScreenImpl implements EnvironmentService{

	
	protected Set<Content>[][] contents;
	
	public EnvironmentImpl() {
		super();
		contents = null;
	}

	@Override
	public Set<Content> cellContent(int x, int y) {
		return contents[x][y];
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void init(int w, int h) {
		super.init(w, h);
		contents = (HashSet<Content>[][]) new HashSet[w][h]; //new HashSet<Content>[h][w];
		for(int x = 0; x < w; x++)
			for(int y = 0; y < h; y++)
				contents[x][y] = new HashSet<Content>();
	}
	
	public boolean hasCharacter(int x, int y) {
		HashSet<Content> content = (HashSet<Content>) contents[x][y];
		for(Content c : content) {
			if(c.isCharacter())
				return true;
		}
		return false;
	}

	//@Override
	public void init(EditableScreenService es) {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void init(EditableScreenService es) {
		
		
	}*/

}
