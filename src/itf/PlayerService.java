package itf;

public interface PlayerService extends CharacterService{
	
	public EngineService getEngine();
	
	
	/*
	 * \post: getEngine() == engS
	 */
	public void init(int w, int h, EnvironmentService envS, EngineService engS);
	
	/*
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
	 * 		  => goDown()@Pre
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
	public void step();
}
