package itf;

import java.util.ArrayList;
import java.util.List;

public class Content {
	private List<CharacterService> characters = null;
	private List<Item> items=null;
	
	public Content() {
		characters = new ArrayList<CharacterService>();
		items = new ArrayList<Item>();
	}

	public void addCharacter(CharacterService c) {
		characters.add(c);
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeCharacter(CharacterService c) {
		characters.remove(c);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public ArrayList<CharacterService> getCharacters() {
		return new ArrayList<CharacterService>(characters);
	}

	public ArrayList<Item> getItems() {
		return new ArrayList<Item>(items);
	}

	public boolean isCharacter() {
		return !characters.isEmpty();
	}

	public boolean isItem() {
		return !items.isEmpty();
	}

	public boolean isEmpty() {
		return characters.size()==0 && items.size()==0;
	}
}
