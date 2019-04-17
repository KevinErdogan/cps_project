package itf;
/**
 * \inv: getEnvi().cellNature(getWdt(), getHgt())
 * 			in {Cell.EMP, Cell.HOL, Cell.LAD, Cell.HDR}
 * \inv: if exist Character x in getEnvi().cellContent(getWdt(), getHgt()) => x == this
 */
public interface CharacterService {

	public EnvironmentService getEnvi();
	public int getHgt();
	public int getWdt();
	
	/*
	 * \pre: screen.cellNature(x,y) == EMP
	 */
	public void init(EnvironmentService screen, int x, int y);
	
	/*
	 * \post: getHgt() == getHgt()@Pre
	 * \post: getWdt()@Pre == 0 => getWdt() == getWdt()@Pre 
	 * \post: getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
	 * 			in {Cell.MTL, Cell.PLT} => getWdt() == getWdt()@Pre
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			not in {Cell.PLT, Cell.MTL, Cell.LAD}
	 * 		  && not exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			=> getWdt() == getWdt()@Pre
	 * \post: getWdt()@Pre != 0 
	 * 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 				in {Cell.LAD, Cell.HDR}
	 * 			  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 				 in {Cell.PLT, Cell.MTL, Cell.LAD}
	 * 			  || exist Character c in
	 * 				 getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre )
	 *        && not (exist Character c in 
	 *        			getEnvi().cellContent( getWdt()@Pre-1, getHgt()@Pre )@Pre )
	 *        => getWdt() == getWdt()@Pre -1
	 */
	public void goLeft();
	
	/*
	 * \post: getHgt() == getHgt()@Pre
	 * \post: getWdt()@Pre == getEnvi().getWidth()@Pre-1 => getWdt() == getWdt()@Pre 
	 *
	 * \post: getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
	 * 			in {Cell.MTL, Cell.PLT} 
	 * 		  => getWdt() == getWdt()@Pre
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			not in {Cell.PLT, Cell.MTL, Cell.LAD}
	 * 		  && not exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			=> getWdt() == getWdt()@Pre
	 * \post: getWdt()@Pre != getEnvi().getWidth()@Pre-1 
	 * 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
	 * 			not in {Cell.MTL, Cell.PLT}
	 * 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 				in {Cell.LAD, Cell.HDR}
	 * 			  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 				 in {Cell.PLT, Cell.MTL, Cell.LAD}
	 * 			  || exist Character c in
	 * 				 getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre )
	 *        && not ( exist Character c in 
	 *        			getEnvi().cellContent( getWdt()@Pre+1, getHgt()@Pre )@Pre )
	 *        => getWdt() == getWdt()@Pre +1
	 */
	public void goRight();
	
	
	/*
	 * \post: getWdt() == getWdt()@Pre
	 * \post: getHgt()@Pre == getEnvi().getHeight()@Pre-1 => getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD}
	 * 		  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre+1 )@Pre
	 * 			not in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
	 * 		  || exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre+1 )@Pre
	 * 		  => getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			in {Cell.LAD}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre+1 )@Pre
	 * 			in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
	 * 		  &&  not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre+1 )@Pre)
	 * 		  => getHgt() == getHgt()@Pre+1
	 */
	public void goUp();
	
	/*
	 * \post: getWdt() == getWdt()@Pre
	 * \post: getHgt()@Pre == 0 => getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			not in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
	 * 		  || exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 		  => getHgt() == getHgt()@Pre
	 * 
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
	 *        && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
	 *        => getHgt() == getHgt()@Pre-1
	 */
	public void goDown();
	
	
	public void step();
}
