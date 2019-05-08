package impl;

import itf.Cell;
import itf.EditableScreenService;

public class EditableScreenImpl extends ScreenImpl implements EditableScreenService{

	public EditableScreenImpl() {
		super();
	}

	@Override
	public boolean isPlayable() {
		for(int i = 0; i < this.width; i++)
			for(int j = 0; j < this.height; j++) {
				if(cellNature(i, j) == Cell.HOL) {
					return false;
				}
			}
		
		for(int i = 0; i < this.width; i++)
			if(cellNature(i, 0) != Cell.MTL)
				return false;
				
		return true;
	}

	@Override
	public void setNature(int x, int y, Cell c) {
		this.cells[x][y] = c;
	}
	
	@Override
	public void init(int w, int h) {
		super.init(w, h);
	}


}
