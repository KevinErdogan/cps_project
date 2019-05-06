package itf;

/**
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 			forall Character c1, c2 in cellContent(x,y),
 * 				 c1 = c2
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 			cellNature(x,y) in {Cell.MTL, Cell.PLT} => cellContent(x,y) = empty
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 				exist Treasure t in cellContent(x,y)
 * 					=> (cellNature(x,y) = Cell.EMP &&
 * 						cellNature(x,y-1) in {Cell.PLT, Cell.MTL})
 */
public interface EnvironmentService extends EditableScreenService{
	
	/*
	 * \pre: (0 <= y && y < getHeight())
	 * 		&& ( 0 <= x && x < getWidth())
	 */
	public Content cellContent(int x, int y);
	
	
	/*
	 * \post: \forall (x,y) in [0, getWidth()[ x [0, getHeight()[
	 * 		  cellNature(x,y) = e.cellNature(x,y)
	 */
	public void init(EditableScreenService e);
	
	
	/*
	 * \pre: (0 <= y && y < getHeight())
	 * 		&& ( 0 <= x && x < getWidth())
	 * 
	 * \post: exist Character C in getEnvi().cellContent(x,y) => true
	 * 
	 * \post: not exist Character C in getEnvi().cellContent(x,y) => false
	 
	public boolean hasCharacter(int x, int y);
	*/
}
