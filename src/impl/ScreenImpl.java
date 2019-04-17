package impl;

import itf.Cell;
import itf.ScreenService;

public class ScreenImpl implements ScreenService{

	protected int height;
	protected int width;
	protected Cell[][] cells;
	
	public  ScreenImpl() {
		height = -1;
		width = -1;
		cells = null;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public Cell cellNature(int x, int y) {
		return cells[x][y];
	}

	@Override
	public void init(int w, int h) {
		this.height = h;
		this.width = w;
		this.cells = new Cell[w][h];
		for(int i = 0; i < w; i++)
			for(int j = 0; j < h; j++)
				cells[i][j] = Cell.EMP;
	}

		
	@Override
	public void dig(int x, int y) {
		cells[x][y] = Cell.HOL;
	}

	@Override
	public void fill(int x, int y) {
		cells[x][y] = Cell.PLT;
	}
}
