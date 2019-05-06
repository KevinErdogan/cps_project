package itf;

/*
 * \inv : ( getEnvi().cellNature(getWdt(), getHgt()) == Cell.LAD
 * 		&& getHgt() < getTarget().getHgt()
 * 		&& getEnvi().cellNature(getWdt(), getHgt()+1) not in { Cell.PLT, Cell.MTL }
 * 			) => getBehavior() = Move.Up
 * 		   					 ### || ( exist Character c in getEnvi().cellContent(getWdt(), getHgt()+1)
 * 							 ###	=> getTarget().getHgt() - getHgt() < Math.abs ( getTarget().getWdt() - getWdt() ) )
 * 		   
 * \inv : (getEnvi().cellNature(getWdt(), getHgt()) == Cell.LAD
 * 		&& getHgt() > getTarget().getHgt()
 * 		&& ( getEnvi().cellNature(getWdt(), getHgt()-1) not in { Cell.PLT, Cell.MTL }
 * 			) => getBehavior() = Move.Down
 * 		   					 ### || ( exist Character c in getEnvi().cellContent(getWdt(), getHgt()-1)
 * 							 ###	=> getTarget().getHgt() - getHgt() < Math.abs ( getTarget().getWdt() - getWdt() ) )
 * \inv : ( getEnvi().cellNature(getWdt(), getHgt()) == Cell.LAD
 * 		&& getHgt() == getTarget().getHgt()
 * 	   		) => getBehavior() = Move.Neutral
 * 
 * \inv : (getEnvi().cellNature(getWdt(), getHgt()) in {Cell.Hol, Cell.HDR} 
 * 			|| getEnvi().cellNature(getWdt(), getHgt()-1) in { Cell.MTL, Cell.PLT } 
 * 			|| exist Character c in getEnvi().cellContent(getWdt(), getHgt()-1))
 * 			&& ( getTarget().getWdt() < getWdt() => getBehavior() = Move.Left 
 * 				|| getTarget().getWdt() > getWdt() => getBehavior() = Move.Right
 * 				|| getTarget().getWdt() == getWdt() => getBehavior() = Move.Neutral)
 * 		
 * \inv : (getEnvi().cellNature(getWdt(), getHgt()) == Cell.LAD
 * 			&& (getEnvi().cellNature(getWdt(), getHgt()-1) in { Cell.MTL, Cell.PLT } 
 * 				|| exist Character c in getEnvi().cellContent(getWdt(), getHgt()-1))
 * 			) => ...
 * 
 */

public interface GuardService extends CharacterService{

	public int getId();
	public CharacterService getTarget();
	public Move getBehaviour();
	public int getTimeInHole();
	
	/*
	 * \pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
	 */
	public void climbLeft();
	
	/*
	 * \pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
	 */
	public void climbRight();
	
	public void step();
	
}
