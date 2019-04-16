package impl;

import itf.Cell;
import itf.ScreenService;

public class ScreenImpl implements ScreenService{

	protected int height;
	protected int width;
	protected Cell[][] cells;
	
	public  ScreenImpl(int h, int w) {
		this.init(h,w);
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
	public void init(int h, int w) {
		this.height = h;
		this.width = w;
		this.cells = new Cell[h][w];
		for(int i = 0; i < h; i++)
			for(int j = 0; j < w; j++)
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
