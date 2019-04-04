package itf;

/**
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 			forall Character c1, c2 in cellContent(x,y), c1 = c2
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 			cellNature(x,y) in {Cell.MTL, Cell.PLR} => cellContent(x,y) = empty
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 				exist Treasure t in cellContent(x,y)
 * 					=> (cellNature(x,y) = Cell.EMP &&
 * 						cellNature(x,y-1) in {Cell.PLT, Cell.MTL})
 */
public interface EnvironmentService extends ScreenService{

	public 
	
	
}
