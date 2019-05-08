package impl;

import itf.Cell;
import itf.CharacterService;
import itf.EnvironmentService;
import itf.GuardService;
import itf.Item;
import itf.Move;

public class GuardImpl extends CharacterImpl implements GuardService{

	private int id;
	private int timeInHole;
	private CharacterService target;
	private Item myItem;
	
	
	public GuardImpl() {
		super();
		id = -1;
		timeInHole = -1;
		target = null;
		myItem = null;
	}


	@Override
	public void init(EnvironmentService screen, int x, int y, CharacterService target, int id) {
		super.init(screen, x, y);
		this.target = target;
		this.id = id;
		this.timeInHole = 0;
		this.myItem = null;
		screen.getIn(this, x, y);
	}
	
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public CharacterService getTarget() {
		return target;
	}

	@Override
	public int getTimeInHole() {
		return timeInHole;
	}

	@Override
	public Move getBehavior() {
		boolean LAD_On_NonFreeCell = getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
				 			&& (( (getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.HOL
				 				  && getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.HDR
				 				  && getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.EMP)
						  		  || getEnvi().hasCharacter(getWdt(), getHgt()-1) == true )
				 				|| (getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.MTL
				 					|| getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.PLT));
				 			
		boolean Down_Non_Free_Cell = (getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.MTL
									  || getEnvi().cellNature( getWdt(), getHgt()-1 ) == Cell.PLT);
		
		boolean Right_Non_Free_Cell = getWdt() != getEnvi().getWidth()-1
									&& (getEnvi().cellNature( getWdt()+1, getHgt() ) == Cell.MTL
									|| getEnvi().cellNature( getWdt()+1, getHgt() ) == Cell.PLT);

		boolean Left_Non_Free_Cell = getWdt() != 0
									&& (getEnvi().cellNature( getWdt()-1, getHgt() ) == Cell.MTL
									|| getEnvi().cellNature( getWdt()-1, getHgt() ) == Cell.PLT);
		
		if(LAD_On_NonFreeCell 
			&& getTarget().getWdt() == getWdt()
			&& getTarget().getHgt() > getHgt()) {
			return Move.Up;
		}
		if(LAD_On_NonFreeCell 
			&& getTarget().getWdt() == getWdt()
			&& getTarget().getHgt() < getHgt()
			&& ! Down_Non_Free_Cell) {
			return Move.Down;
		}
		if(LAD_On_NonFreeCell 
			&& getTarget().getWdt() < getWdt()
			&& getTarget().getHgt() == getHgt()
			&& ! Left_Non_Free_Cell) {
			return Move.Left;
		}
		if(LAD_On_NonFreeCell 
			&& getTarget().getWdt() > getWdt()
			&& getTarget().getHgt() == getHgt()
			&& ! Right_Non_Free_Cell) {
			return Move.Right;
		}
		
		if(LAD_On_NonFreeCell 
			&& Math.abs(getWdt() - getTarget().getWdt()) > Math.abs(getHgt() - getTarget().getHgt())
			&& getTarget().getHgt() > getHgt()) {
			return Move.Up;
		}
		
		if(LAD_On_NonFreeCell 
			&& Math.abs(getWdt() - getTarget().getWdt()) > Math.abs(getHgt() - getTarget().getHgt())
			&& getTarget().getHgt() < getHgt()
			&& ! Down_Non_Free_Cell) {
			return Move.Down;
		}
		if(LAD_On_NonFreeCell 
			&& Math.abs(getWdt() - getTarget().getWdt()) < Math.abs(getHgt() - getTarget().getHgt())
			&& getTarget().getWdt() > getWdt()
			&& ! Right_Non_Free_Cell)
			return Move.Right;
		
		if(LAD_On_NonFreeCell 
			&& Math.abs(getWdt() - getTarget().getWdt()) < Math.abs(getHgt() - getTarget().getHgt())
			&& getTarget().getWdt() < getWdt()
			&& ! Left_Non_Free_Cell)
			return Move.Left;
		
		if(getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
			&& getTarget().getHgt() > getHgt()) {
			return Move.Up;
		}
		if(getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
			&& getTarget().getHgt() < getHgt()
			&& ! Down_Non_Free_Cell) {
			return Move.Down;
		}
		if(getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
			&& getTarget().getHgt() == getHgt()) {
			return Move.Neutral;
		}
		boolean canMove = ( (getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL 
							  || getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HDR)
							|| (getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.HOL
								&& getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.HDR
								&& getEnvi().cellNature( getWdt(), getHgt()-1 ) != Cell.EMP)
							|| getEnvi().hasCharacter(getWdt(), getHgt()-1) == true);
		
		if(canMove && getTarget().getWdt() > getWdt())
			return Move.Right;
		
		if(canMove && getTarget().getWdt() < getWdt())
			return Move.Left;
		
		if(canMove && getTarget().getHgt() < getHgt())
			return Move.Down;
		
		if(canMove && getTarget().getWdt() == getWdt())
			return Move.Neutral;
		
		return Move.Neutral;
	}

	@Override
	public void climbLeft() {
		if(getWdt() == 0) return;
		
		if(getEnvi().cellNature(getWdt()-1, getHgt()+1) == Cell.PLT
			|| getEnvi().cellNature(getWdt()-1, getHgt()+1) == Cell.MTL) return;
		
		if(getWdt() != 0
			&& (getEnvi().cellNature(getWdt()-1, getHgt()+1) != Cell.PLT
				&& getEnvi().cellNature(getWdt()-1, getHgt()+1) != Cell.MTL)){
			getEnvi().getOut(this, wdt, hgt);
			this.wdt = getWdt() -1;
			this.hgt = getHgt() +1;
			getEnvi().getIn(this, wdt, hgt);
			this.timeInHole = 0;
		}
	}

	@Override
	public void climbRight() {
		if(getWdt() == getEnvi().getWidth()-1) return;
		
		if(getEnvi().cellNature(getWdt()+1, getHgt()+1) == Cell.PLT
			|| getEnvi().cellNature(getWdt()+1, getHgt()+1) == Cell.MTL) return;
		
		if(getWdt() != getEnvi().getWidth()-1
				&& (getEnvi().cellNature(getWdt()+1, getHgt()+1) != Cell.PLT
					&& getEnvi().cellNature(getWdt()+1, getHgt()+1) != Cell.MTL)){
				getEnvi().getOut(this, wdt, hgt);
				this.wdt = getWdt() +1;
				this.hgt = getHgt() +1;
				getEnvi().getIn(this, wdt, hgt);
				this.timeInHole = 0;
		}
	}

	@Override
	public void step() {
		boolean willFall = (  (getEnvi().cellNature( getWdt(), getHgt()) != Cell.LAD
							   && getEnvi().cellNature( getWdt(), getHgt()) != Cell.HDR
							   && getEnvi().cellNature( getWdt(), getHgt()) != Cell.HOL)
						   && (getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.HDR
						   	   || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.HOL
						   	   || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.EMP
						   	   || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.DOR)
						   && getEnvi().hasCharacter(getWdt(), getHgt()-1) == false);
		
		if(willFall) goDown();
		
		else if(getEnvi().cellNature( getWdt(), getHgt()) == Cell.HOL
				&& getTimeInHole() < 5) {
			timeInHole = getTimeInHole()+1;
		}
		
		else if(getEnvi().cellNature( getWdt(), getHgt()) == Cell.HOL
				&& getTimeInHole() == 5
				&& getBehavior() == Move.Left) {
			climbLeft();
		}
		else if(getEnvi().cellNature( getWdt(), getHgt()) == Cell.HOL
				&& getTimeInHole() == 5
				&& getBehavior() == Move.Right)
			climbRight();
		
		else if(! willFall
				&& getEnvi().cellNature( getWdt(), getHgt()) != Cell.HOL) {
			if(getBehavior() == Move.Right)
				goRight();
		
			if(getBehavior() == Move.Left)
				goLeft();
			
			if(getBehavior() == Move.Up)
				goUp();
		
			if(getBehavior() == Move.Down)
				goDown();
		}
		
	}

	@Override
	public boolean hasItem() {
		return myItem != null;
	}
	
	public void pickUpItem(Item t) {
		this.myItem = t;
	}
	
	public void dropItem() {
		this.myItem = null;
	}


	@Override
	public void die() {
		getEnvi().getOut(this, wdt, hgt);
		this.wdt = getInitialWdt();
		this.hgt = getInitialHgt();
		this.timeInHole = 0;
		getEnvi().getIn(this, wdt, hgt);
	}
	
	
	public void reset() {
		getEnvi().getOut(this, wdt, hgt);
		this.wdt = getInitialWdt();
		this.hgt = getInitialHgt();
		getEnvi().getIn(this, wdt, hgt);
		this.myItem = null;
		this.timeInHole = 0;
	}


	@Override
	public Item getItem() {
		return this.myItem;
	}
	
	
	
}
