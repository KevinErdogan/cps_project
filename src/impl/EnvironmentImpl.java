package impl;


import itf.Content;
import itf.EditableScreenService;
import itf.EnvironmentService;

public class EnvironmentImpl extends EditableScreenImpl implements EnvironmentService{

	
	protected Content[][] contents;
	
	public EnvironmentImpl() {
		super();
		contents = null;
	}

	@Override
	public Content cellContent(int x, int y) {
		return contents[x][y];
	}
	
	/*
	public boolean hasCharacter(int x, int y) {
		HashSet<Content> content = (HashSet<Content>) contents[x][y];
		for(Content c : content) {
			if(c.isCharacter())
				return true;
		}
		return false;
	}
	*/
	
	//@Override
	public void init(EditableScreenService es) {
		super.init(es.getWidth(), es.getHeight());
		contents = new Content[es.getWidth()][es.getHeight()];
		for(int x = 0; x < es.getWidth(); x++)
			for(int y = 0; y < es.getHeight(); y++) {
				contents[x][y] = new Content();
				setNature(x, y, es.cellNature(x, y));
			}
		
	}

}
