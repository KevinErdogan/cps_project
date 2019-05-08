package itf;

/**
 * \inv: getNbTreasure() >= 0
 * \inv: getHP() >= 0 && getHP <= 3
 */
public interface PlayerService extends CharacterService{
	
	public EngineService getEngine();
	public int getNbTreasure();
	public int getHP();
	public boolean hasKey();
	
	/*
	 * \post: getEngine() == engS
	 * \post: getNbTreasure() == 0
	 * \post: getHP() == 3
	 */
	public void init(int w, int h, EnvironmentService envS, EngineService engS);
	
	/*
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
	 * 		  => goDown()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Right
	 * 		  => goRight()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Left
	 * 		  => goLeft()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Up
	 * 		  => goUp()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.Down
	 * 		  => goDown()@Pre
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.DigL
	 * 		  && getWdt()@Pre != 0
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			in {Cell.MTL, Cell.PLT, Cell.LAD}
	 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
	 * 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 )@Pre
	 * 			== Cell.PLT
	 * 		  => getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 ) == Cell.HOL
	 * 			 && getEngine().getHoles() contains h with h.getX() == getWdt()@Pre-1 && h.getY() == getHgt()@Pre-1
	 * 
	 * \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
	 * 		  && getEngine().getNextCommand()@Pre == Move.DigR
	 * 		  && getWdt()@Pre != getEnvi().getWidth()-1
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			in {Cell.MTL, Cell.PLT, Cell.LAD}
	 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
	 * 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 )@Pre
	 * 			== Cell.PLT
	 * 		  => getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 ) == Cell.HOL
	 * 			 && getEngine().getHoles() contains h with h.getX() == getWdt()@Pre+1 && h.getY() == getHgt()@Pre-1
	 */
	public void step();
	
	/*
	 * \post: getNbTreasure() == getNbTreasure()@Pre+1
	 * \post: getWdt() = getWdt()@Pre
	 * \post: getHgt() = getHgt()@Pre
	 */
	public void pickUpTreasure();
	
	
	/*
	 * \pre: hasKey() == false
	 * \post: getNbTreasure() == getNbTreasure()@Pre
	 * \post: getWdt() = getWdt()@Pre
	 * \post: getHgt() = getHgt()@Pre
	 * \post: hasKey() == true
	 * \post: getKey() == k
	 */
	public void pickUpKey(Item k);
	
	public void die();
	
	/*
	 * \post: getWdt() = getInitialWdt();
	 * \post: getHgt() = getInitialHgt();
	 * \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
     *    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
     * \post: hasKey() == false
	 */
	public void reset();
	
	/*
	 * \pre: hasKey() == true
	 * \post: hasKey() == false
	 */
	public Item getKey();
	
	public void resetNbTreasure();
	
	public void resetKey();
	
	public void resetHP();
}
