package itf;

public class Content {
	private CharacterService character=null;
	private Item item=null;

	public Content(CharacterService c, Item item) {
		this.character = c;
		this.item = item;
	}

	public CharacterService getCharacter() {
		return character;
	}

	public Item getItem() {
		return item;
	}

	public boolean isCharacter() {
		return character != null;
	}

	public boolean isItem() {
		return item != null;
	}
}
