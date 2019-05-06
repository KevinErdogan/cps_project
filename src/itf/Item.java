package itf;


public interface Item {//DATA
	
	public int getX();
	public int getY();
	public boolean isOnFloor();
	public ItemType getItemType();
	public int getInitialX();
	public int getInitialY();
	public void setX(int x);
	public void setY(int y);
	public void setOnFloor(boolean onFloor);
}
