package itf;

/**
 * LAD_On_NonFreeCell() defined by 
 * 			getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
 * 			&& ((getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
 * 		          not in {Cell.HOL, Cell.HDR, Cell.EMP}
 * 				  || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
 * 				|| getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre in {Cell.PLT, Cell.MTL})
 * 
 * Down_Non_Free_Cell() defined by 
 * 			getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre in {Cell.PLT, Cell.MTL}
 * 
 * Right_Non_Free_Cell() defined by
 * 			getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre in {Cell.PLT, Cell.MTL}
 * 
 * Left_Non_Free_Cell() defined by 
 * 			getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre in {Cell.PLT, Cell.MTL}
 * 
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&& getTarget().getWdt() == getWdt()@Pre 
 * 		    && getTarget().getHgt() > getHgt()@Pre
 * 			=> getBehavior() ==  Move.Up
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&& getTarget().getWdt() == getWdt()@Pre 
 * 		    && getTarget().getHgt() < getHgt()@Pre
 * 			&& !Down_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Down
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&& getTarget().getWdt() < getWdt()@Pre 
 * 		    && getTarget().getHgt() == getHgt()@Pre
 * 			&& !Left_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Left
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&& getTarget().getWdt() > getWdt()@Pre 
 * 		    && getTarget().getHgt() == getHgt()@Pre
 * 			&& !Right_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Right
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&&  |getWdt()@Pre -getTarget().getWdt()| > |getHgt()@Pre -getTarget().getHgt()| 
 * 			&& getTarget().getHgt() > getHgt()@Pre
 * 			=> getBehavior() ==  Move.Up 
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&&  |getWdt()@Pre -getTarget().getWdt()| > |getHgt()@Pre -getTarget().getHgt()| 
 * 			&& getTarget().getHgt() < getHgt()@Pre
 *  		&& !Down_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Down
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&&  |getWdt()@Pre -getTarget().getWdt()| < |getHgt()@Pre -getTarget().getHgt()| 
 * 			&& getTarget().getWdt() > getWdt()@Pre
 * 			&& !Right_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Right
 * 
 * \inv: LAD_On_NonFreeCell()
 * 			&&  |getWdt()@Pre -getTarget().getWdt()| < |getHgt()@Pre -getTarget().getHgt()| 
 * 			&& getTarget().getWdt() < getWdt()@Pre
 * 			&& !Left_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Left
 * 
 * \inv:   ! LAD_On_NonFreeCell()
 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
 * 			&& getTarget().getHgt() > getHgt()@Pre
 * 			=> getBehavior() ==  Move.Up
 *   
 * \inv:   ! LAD_On_NonFreeCell()
 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
 * 			&& getTarget().getHgt() < getHgt()@Pre
 * 			&& !Down_Non_Free_Cell()
 * 			=> getBehavior() ==  Move.Down
 * 
 * \inv:   ! LAD_On_NonFreeCell()
 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
 * 			&& getTarget().getHgt() == getHgt()@Pre
 * 			=> getBehavior() ==  Move.Neutral
 * 
 * \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
 * 			   in {Cell.HOL, Cell.HDR}
 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
 * 		  && getTarget().getWdt() > getWdt()@Pre
 * 			=> getBehavior() ==  Move.Right
 * 
 * \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
 * 			   in {Cell.HOL, Cell.HDR}
 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
 * 		  && getTarget().getWdt() < getWdt()@Pre
 * 			=> getBehavior() ==  Move.Left
 * 
 * \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
 * 			   in {Cell.HOL, Cell.HDR}
 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
 * 		  && getTarget().getHgt() < getHgt()@Pre && getTarget().getWdt() == getWdt()@Pre
 * 			=> getBehavior() ==  Move.Down
 * 
 * \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
 * 			   in {Cell.HOL, Cell.HDR}
 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
 * 		  && getTarget().getWdt() == getWdt()@Pre
 * 		  && getTarget().getHgt() == getHgt()@Pre
 * 			=> getBehavior() ==  Move.Neutral 
 * 
 * \inv : timeinhole entre 0 et 15 (inclus)
 */
public interface GuardService extends CharacterService{

	public int getId(); 
	public CharacterService getTarget();
	public int getTimeInHole();
	public Move getBehavior();
	public boolean hasItem();
	
	/*
	 * \post: getTimeInHole() == 0
	 * \post: getTarget() == target
	 * \post: getId() == id
	 */
	public void init(EnvironmentService screen, int x, int y, CharacterService target, int id);
	
	/*
	 * \pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
	 * 
	 * \post: getWdt()@Pre == 0 => getWdt() == getWdt()@Pre
	 * 								&& getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre+1 )@Pre in {Cell.PLT, Cell.MTL} 
	 * 		   => getWdt() == getWdt()@Pre 
	 * 			   && getHgt() == getHgt()@Pre
	 * 
	 * \post: getWdt()@Pre != 0 
	 * 			&& getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre+1 )@Pre not in {Cell.PLT, Cell.MTL}
	 * 		   => getWdt() == getWdt()@Pre-1 && getHgt() == getHgt()@Pre+1
	 * 				&& getTimeInHole() == 0
	 * 				&& not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
	 *        		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
	 */
	public void climbLeft();
	
	/*
	 * \pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
	 * 
	 * \post: getWdt()@Pre == getEnvi().getWidth()@Pre-1  => getWdt() == getWdt()@Pre
	 * 															&& getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre+1 )@Pre in {Cell.PLT, Cell.MTL} 
	 * 		   => getWdt() == getWdt()@Pre 
	 * 			   && getHgt() == getHgt()@Pre
	 * 
	 * \post: getWdt()@Pre != getEnvi().getWidth()@Pre-1  
	 * 			&& getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre+1 )@Pre not in {Cell.PLT, Cell.MTL}
	 * 		   => getWdt() == getWdt()@Pre+1 && getHgt() == getHgt()@Pre+1 
	 * 				&& getTimeInHole() == 0
	 * 				&& not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
	 *        		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
	 */
	public void climbRight();
	
	
	/*
	 * WillFall() defined by 
	 * 		 getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR, Cell.HOL}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
	 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
	 * 
	 * 
	 * \post: WillFall()
	 * 		  => goDown()@Pre
	 * 
	 * \post: ! WillFall() 
	 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
	 * 			&& getTimeInHole()@Pre < 5
	 * 		  => getTimeInHole() == getTimeInHole()@Pre+1
	 * 
	 * \post: ! WillFall() 
	 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
	 * 			&& getTimeInHole()@Pre == 5
	 * 			&& getBehavior()@Pre == Move.Left
	 * 		  => climbLeft()
	 * 
	 * \post: ! WillFall() 
	 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
	 * 			&& getTimeInHole()@Pre == 5
	 * 			&& getBehavior()@Pre == Move.Right
	 * 		  => climbRight()
	 * 
	 * \post: not WillFall()
	 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
	 * 			&& getBehavior()@Pre == Move.Right
	 * 		  => goRight()@Pre 
	 * 
	 * \post: not WillFall()
	 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
	 * 			&& getBehavior()@Pre == Move.Left
	 * 		  => goLeft()@Pre 
	 * 
	 * \post: not WillFall()
	 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
	 * 			&& getBehavior()@Pre == Move.Up
	 * 		  => goUp()@Pre 
	 * 
	 * \post: not WillFall()
	 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
	 * 			&& getBehavior()@Pre == Move.Down
	 * 		  => goDown()@Pre 
	 */
	public void step();
	
	/*
	 * \post: getWdt() = getInitialWdt();
	 * \post: getHgt() = getInitialHgt();
	 * \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
     *    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
     * \post: getTimeInHole() == 0
	 */
	public void die();
	
	/*
	 * \post: getWdt() = getInitialWdt();
	 * \post: getHgt() = getInitialHgt();
	 * \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
     *    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
     * \post: hasTreasure() == false
     * \post: getTimeInHole() == 0
	 */
	public void reset();
	
	public void pickUpItem(Item t);
	
	public void dropItem();
	
	public Item getItem();
	
}
