package impl;

import itf.Cell;
import itf.Command;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

public class PlayerImpl extends CharacterImpl implements PlayerService{

	private EngineService engine=null;

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
	}

	@Override
	public void step() {
		Command nextMove = this.engine.getNextCommand();
		
		if( (getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD
				&& getEnvi().cellNature(getWdt(), getHgt()) != Cell.HDR)
			&&
			(getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HDR
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HOL
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.EMP)
			&&
			(getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter() == false)
		  )
		{//alors on tombe
			goDown();
		}
		else if(nextMove == Command.Right)
		{
			goRight();
		}
		else if(nextMove == Command.Left)
	    {
			goLeft();
		}
		else if(nextMove == Command.Up)
		{
			goUp();
		}
		else if(nextMove == Command.Down)
		{
			goDown();
		}
		else if( (nextMove == Command.DigL)
				 && ( (getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.MTL
					     || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.PLT)
					 || getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter() == true)
				 && ( getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.MTL
				 		 || getEnvi().cellNature( getWdt()-1, getHgt()) != Cell.PLT)
				 && getEnvi().cellNature( getWdt()-1, getHgt()-1 ) == Cell.PLT
			   )
		{
			getEnvi().setNature(getWdt()-1, getHgt()-1, Cell.HOL);
		}
		else if( (nextMove == Command.DigR)
				 && ( (getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.MTL
			            || getEnvi().cellNature( getWdt(), getHgt()-1) == Cell.PLT)
				    || getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter() == true)
				 && ( getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.MTL
		 		        || getEnvi().cellNature( getWdt()+1, getHgt()) != Cell.PLT)
				 && getEnvi().cellNature( getWdt()+1, getHgt()-1 ) == Cell.PLT
				)

		{
			getEnvi().setNature( getWdt()+1, getHgt()-1, Cell.HOL);
		}
	}


}
