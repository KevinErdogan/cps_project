package impl;

import itf.HoleService;

public class HoleImpl implements HoleService{
    private int x;
    private int y;
    private int time;
	
	public HoleImpl() {
		super();
		this.x = -1;
		this.y = -1;
		this.time = -1;
	}
	
	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getTime() {
		return this.time;
	}

	@Override
	public void init(int x, int y) {
		this.x = x;
		this.y = y;
		this.time = 0;
	}

	@Override
	public void step() {
		this.time++;
	}

}
