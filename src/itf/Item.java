package itf;

public class Item {
	public enum ItemType{
		Treasure
	}
	
	private int x;
	private int y;
	private ItemType nature;

	public Item(int x, int y, ItemType nature) {
		super();
		this.x = x;
		this.y = y;
		this.nature=nature;
	}
	
	public ItemType getNature() {
		return nature;
	}

	public int getCol() {
		return x;
	}

	public int getHgt() {
		return y;
	}

}
