package impl;

import itf.Item;
import itf.ItemType;

public class ItemImpl implements Item{
		private int x;
		private int y;
		private int initialX;
		private int initialY;
		private ItemType it;
		private boolean onFloor;

		public ItemImpl(int x, int y, ItemType type, boolean isOnFloor) {
			this.x = x;
			this.y = y;
			this.initialX = x;
			this.initialY = y;
			this.it = type;
			this.onFloor = isOnFloor;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public ItemType getIt() {
			return it;
		}

		@Override
		public boolean isOnFloor() {
			return this.onFloor;
		}

		@Override
		public ItemType getItemType() {
			return this.it;
		}

		public int getInitialX() {
			return initialX;
		}

		public int getInitialY() {
			return initialY;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setOnFloor(boolean onFloor) {
			this.onFloor = onFloor;
		}	
}
