package impl;

import itf.Cell;
import itf.EngineService;
import itf.EnvironmentService;
import itf.Move;
import itf.PlayerService;

public class PlayerImpl extends CharacterImpl implements PlayerService{

	private EngineService engine;
	
	public PlayerImpl(EnvironmentService screen, int x, int y) {
		super(screen, x, y);
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
	
	/*
	 
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Right
	 * 		  => goRight()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Left
	 * 		  => goLeft()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Up
	 * 		  => goUp()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Down
	 * 		  => goDown()@Pre
	 * 
	 * \post: getEngine().getNextCommand()@Pre == Move.DigL 
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			in {Cell.MTL, Cell.PLT}
	 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
	 * 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 )@Pre
	 * 			== Cell.PLT
	 * 		  => getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 ) == Cell.HOL
	 * 
	 * \post: getEngine().getNextCommand()@Pre == Move.DigR
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			in {Cell.MTL, Cell.PLT}
	 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
	 * 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 )@Pre
	 * 			== Cell.PLT
	 * 		  => getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 ) == Cell.HOL
	 */
	/* \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Right
	 * 		  => goRight()@Pre
	*/
	@Override
	public void step() {
		Move nextMove = this.engine.getNextCommand();
		if( (getEnvi().cellNature(getWdt(), getHgt()) != Cell.LAD 
				&& getEnvi().cellNature(getWdt(), getHgt()) != Cell.HDR)
			&&
			(getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HDR 
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.HOL
				|| getEnvi().cellNature(getWdt(), getHgt()-1) == Cell.EMP)
			&& ( getEnvi().hasCharacter(getWdt(), getHgt()-1) )
		  ) 
		{//alors on tombe
			goDown();
		}
		else if( (nextMove == Move.Right)
				 
				 
				
				
				) {
			goRight();
		}
		else if(nextMove == Move.Left) {
			goLeft();
		}
		else if(nextMove == Move.Up) {
			goUp();
		}
		else if(nextMove == Move.Down) {
			goDown();
		}
		else if(nextMove == Move.DigL) {
			
		}
		else if(nextMove == Move.DigR) {
			
		}
	}


}
