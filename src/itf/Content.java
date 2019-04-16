package itf;

public class Content {
	CharacterService character;
	Item item;
	
	public Content(CharacterService c) {
		this.character = c;
		this.item = null;
	}
	
	public Content(Item i) {
		this.item = i;
		this.character = null;
	}

	public CharacterService getCharacter() {
		return character;
	}

	public Item getItem() {
		return item;
	}
	
	public boolean isCharacter() {
		return this.character != null;
	}
	
	public boolean isItem() {
		return this.item != null;
	}
}
