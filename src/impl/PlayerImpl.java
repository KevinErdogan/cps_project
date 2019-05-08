package impl;

import itf.Cell;
import itf.EngineService;
import itf.EnvironmentService;
import itf.HoleService;
import itf.Item;
import itf.Move;
import itf.PlayerService;

public class PlayerImpl extends CharacterImpl implements PlayerService{

	private EngineService engine=null;
	private int nbTreasure;
	private int hp;
	private Item key;

	public PlayerImpl() {
		super();
	}

	@Override
	public EngineService getEngine() {
		return this.engine;
	}

	@Override
	public void init(int w, int h, EnvironmentService envS, EngineService engS) {
		super.init(envS, w, h);
		this.engine = engS;
		this.nbTreasure = 0;
		this.hp = 3;
		this.getEnvi().getIn(this, w, h);
	}

	@Override
	public void step() {
		Move nextMove = this.engine.getNextCommand();

		if( (getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD
				&& getEnvi().cellNature(getWdt(), getHgt()) != Cell.HDR)
			&&
			(getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HDR
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HOL
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.EMP
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.DOR)
			&&
			(getEnvi().hasCharacter(getWdt(), getHgt()-1) == false)
		  )
		{//alors on tombe
			goDown();
		}
		else if(nextMove == Move.Right)
		{
			goRight();
		}
		else if(nextMove == Move.Left)
	    {
			goLeft();
		}
		else if(nextMove == Move.Up)
		{
			goUp();
		}
		else if(nextMove == Move.Down)
		{
			goDown();
		}
		else if( (nextMove == Move.DigL)
				 && getWdt() != 0
				 && ( (getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.MTL
					     || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.PLT
					     || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.LAD)
					 || getEnvi().hasCharacter(getWdt(), getHgt()-1) == true)
				 && ( getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.MTL
				 		 && getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.PLT)
				 && getEnvi().cellNature( getWdt()-1, getHgt()-1 ) == Cell.PLT
			   )
		{
			getEnvi().dig(getWdt()-1, getHgt()-1);
			HoleService h = new HoleImpl();
			h.init(getWdt()-1, getHgt()-1);
			getEngine().addNewHole(h);
		}
		else if( (nextMove == Move.DigR)
				 && getWdt() != getEnvi().getWidth()-1
				 && ( (getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.MTL
			            || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.PLT
			            || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.LAD)
				    || getEnvi().hasCharacter(getWdt(), getHgt()-1) == true)
				 && ( getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.MTL
		 		        && getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.PLT)
				 && getEnvi().cellNature( getWdt()+1, getHgt()-1 ) == Cell.PLT
				)

		{
			getEnvi().dig(getWdt()+1, getHgt()-1);
			HoleService h = new HoleImpl();
			h.init(getWdt()+1, getHgt()-1);
			getEngine().addNewHole(h);
		}
	}

	@Override
	public int getNbTreasure() {
		return this.nbTreasure;
	}

	@Override
	public void pickUpTreasure() {
		this.nbTreasure++;
	}

	/*
	 *perdre un pv et init tout
	 */
	@Override
	public void die() {
		this.hp--;
		this.nbTreasure = 0;
		this.key = null;
		this.reset();	
	}
	
	public int getHP() {
		return this.hp;
	}

	@Override
	public boolean hasKey() {
		return key != null;
	}

	@Override
	public void pickUpKey(Item k) {
		this.key = k;
		
	}

	@Override
	public Item getKey() {
		return this.key;
	}

	@Override
	public void resetNbTreasure() {
		this.nbTreasure = 0;
	}

	@Override
	public void resetKey() {
		this.key = null;
	}

	@Override
	public void resetHP() {
		this.hp = 3;
		
	}	
}
