package impl;

import itf.Cell;
import itf.CharacterService;
import itf.EnvironmentService;

public class CharacterImpl implements CharacterService{

	protected EnvironmentService screen;
	protected int wdt;
	protected int hgt;
	protected int initialWdt;
	protected int initialHgt;

	public CharacterImpl() {
		screen = null;
		wdt = -1;
		hgt = -1;
	}

	@Override
	public int getHgt() {
		return this.hgt;
	}

	@Override
	public int getWdt() {
		return this.wdt;
	}

	@Override
	public void init(EnvironmentService screen, int x, int y) {
		this.screen = screen;
		this.wdt = x;
		this.hgt = y;
		this.initialWdt = x;
		this.initialHgt = y;
	}

	@Override
	public void goLeft() {
		if(getWdt() == 0) return;

		if(getEnvi().cellNature(getWdt() -1, getHgt()) == Cell.MTL
				|| getEnvi().cellNature(getWdt() -1, getHgt()) == Cell.PLT)
			return;

		if( (getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD
				&& getEnvi().cellNature(getWdt(), getHgt()) != Cell.HDR)
			&& (getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.PLT
			    && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.MTL
			    && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.LAD)
			&& (getEnvi().hasCharacter(getWdt(), getHgt()-1) == false))
			return;

		if( getWdt() != 0
			&& ( getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.MTL
					&& getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.PLT)
			&& ( (getEnvi().cellNature( getWdt(), getHgt()) == Cell.LAD
			   	    || getEnvi().cellNature( getWdt(), getHgt()) == Cell.HDR)
				 ||
			  	 (getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.PLT
			  	    || getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.MTL
			  	    || getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.LAD)
				 ||
				 getEnvi().hasCharacter(getWdt(), getHgt()-1) == true)
		   )
		{
			getEnvi().getOut(this, this.wdt, this.hgt);
			this.wdt = this.wdt -1;
			getEnvi().getIn(this, this.wdt, this.hgt);
		}

	}


	@Override
	public void goRight() {
		if(getWdt() == (getEnvi().getWidth()-1)) return;

		if(getEnvi().cellNature(getWdt() +1, getHgt()) == Cell.MTL
				|| getEnvi().cellNature(getWdt() +1, getHgt()) == Cell.PLT)
			return;

		if( (getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD
				&& getEnvi().cellNature(getWdt(), getHgt()) != Cell.HDR)
			&& (getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.PLT
			    && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.MTL
			    && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.LAD)
			&& (getEnvi().hasCharacter(getWdt(), getHgt()-1) == false))
			return;

		if( getWdt() != (getEnvi().getWidth()-1)
			&& ( getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.MTL
				     && getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.PLT)
		    && ( (getEnvi().cellNature( getWdt(), getHgt()) == Cell.LAD
		   	         || getEnvi().cellNature( getWdt(), getHgt()) == Cell.HDR)
			     ||
		  	     (getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.PLT
		  	         || getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.MTL
		  	         || getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.LAD)
			     ||
			      getEnvi().hasCharacter(getWdt(), getHgt()-1) == true))
		{
			getEnvi().getOut(this, this.wdt, this.hgt);
			this.wdt = this.wdt + 1;
			getEnvi().getIn(this, this.wdt, this.hgt);
		}
	}

	@Override
	public void goUp() {
		if(getHgt() == (getEnvi().getHeight()-1)) return;

		if( getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD
			|| (getEnvi().cellNature(getWdt(), getHgt()+1) != Cell.HOL
			    && getEnvi().cellNature(getWdt(), getHgt()+1) != Cell.LAD
    			&& getEnvi().cellNature(getWdt(), getHgt()+1) != Cell.HDR
				&& getEnvi().cellNature(getWdt(), getHgt()+1) != Cell.EMP
				&& getEnvi().cellNature(getWdt(), getHgt()+1) != Cell.DOR))
			return;

		if(getEnvi().cellNature(getWdt(), getHgt()) == Cell.LAD
			&& (getEnvi().cellNature(getWdt(), getHgt()+1) == Cell.HOL
			    || getEnvi().cellNature(getWdt(), getHgt()+1) == Cell.LAD
    			|| getEnvi().cellNature(getWdt(), getHgt()+1) == Cell.HDR
				|| getEnvi().cellNature(getWdt(), getHgt()+1) == Cell.EMP
				|| getEnvi().cellNature(getWdt(), getHgt()+1) == Cell.DOR))
		{
			getEnvi().getOut(this, this.wdt, this.hgt);
			this.hgt = this.hgt +1;
			getEnvi().getIn(this, this.wdt, this.hgt);
		}
	}

	@Override
	public void goDown() {
		if(getHgt() == 0) return;

		if( (getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.HOL
			  && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.LAD
			  && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.HDR
			  && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.EMP
			  && getEnvi().cellNature(getWdt(), getHgt()-1) != Cell.DOR))
			return;

		if( (getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HOL
			  || getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.LAD
    	      || getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HDR
		      || getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.EMP
		      || getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.DOR))
		{
			getEnvi().getOut(this, this.wdt, this.hgt);
			this.hgt = this.hgt -1;
			getEnvi().getIn(this, this.wdt, this.hgt);
		}
	}

	@Override
	public void step() {
		//nothing todo
	}

	@Override
	public EnvironmentService getEnvi() {
		return this.screen;
	}

	@Override
	public int getInitialHgt() {
		return this.initialHgt;
	}

	@Override
	public int getInitialWdt() {
		return this.initialWdt;
	}

	@Override
	public void die() {
		//nothing to do
	}

	@Override
	public void reset() {
		getEnvi().getOut(this, this.wdt, this.hgt);
		this.wdt = this.initialWdt;
		this.hgt = this.initialHgt;
		getEnvi().getIn(this, this.wdt, this.hgt);
	}
	
	@Override
	public void setXandInitialX(int x) {
		getEnvi().getOut(this, this.wdt, this.hgt);
		
		this.wdt = x;
		this.initialWdt = x;
		
		getEnvi().getIn(this, this.wdt, this.hgt);
	}
	
	@Override
	public void setYandInitialY(int y) {
		getEnvi().getOut(this, this.wdt, this.hgt);
		
		this.hgt = y;
		this.initialHgt = y;
		
		getEnvi().getIn(this, this.wdt, this.hgt);
	}

	
	
}
