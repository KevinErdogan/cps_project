package impl;

import java.util.HashSet;
import java.util.Set;

import itf.CharacterService;
import itf.Content;
import itf.EditableScreenService;
import itf.EnvironmentService;
import itf.Item;
import itf.ItemType;

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
		//ignored
		
	}

	@Override
	public void getOut(CharacterService c, int x, int y) {
		Content toRemove = null;
		for(Content content : contents[x][y]) {
			if(content.isCharacter() && content.getCharacter() == c) {
				toRemove = content;
				break;
			}
		}
		contents[x][y].remove(toRemove);
	}

	@Override
	public void getIn(CharacterService c, int x, int y) {
		Content toAdd = new Content(c, null);
		contents[x][y].add(toAdd);
	}
	
	@Override
	public void getOut(Item i, int x, int y) {
		Content toRemove = null;
		for(Content content : contents[x][y]) {
			if(content.isItem() && content.getItem() == i) {
				toRemove = content;
				break;
			}
		}
		contents[x][y].remove(toRemove);
	}

	@Override
	public void getIn(Item i, int x, int y) {
		Content toAdd = new Content(null, i);
		contents[x][y].add(toAdd);
	}

	@Override
	public Item getItem(int x, int y) {
		HashSet<Content> content = (HashSet<Content>) contents[x][y];
		for(Content c : content) {
			if(c.isItem())
				return c.getItem();
		}
		return null;
	}

	@Override
	public boolean hasItem(int x, int y) {
		HashSet<Content> content = (HashSet<Content>) contents[x][y];
		for(Content c : content) {
			if(c.isItem())
				return true;
		}
		return false;
	}

	

}
