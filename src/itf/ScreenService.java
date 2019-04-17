package itf;

public interface ScreenService{
	
	public int getHeight();
	public int getWidth();
	
	/*
	 * \pre: (0 <= y && y < getHeight()) 
	 *       && (0 <= x && x < getWidth())
	 */
	public Cell cellNature(int x, int y);
	
	
	/*
	 * \pre: 0 < h && 0 < w
	 * \post: getHeight() == h
	 * \post: getWidth() == w
	 * \post: forall (x,y) in [O, getWidth()[ x [0, getHeight()[, 
	 * 			cellNature(x,y) == Cell.EMP
	 */
	public void init(int w, int h);
	
	/*
	 * \pre: (0 <= y && y < getHeight()) 
	 *       && (0 <= x && x < getWidth())
	 * \pre: cellNature(x,y) == Cell.PLT
	 * \post: cellNature(x,y) == Cell.HOL
	 * \post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[, 
	 * 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre
 	 * 		
	 */
	public void dig(int x, int y);
	
	/*
	 * \pre: (0 <= y && y < getHeight()) 
	 *       && (0 <= x && x < getWidth())
	 * \pre: cellNature(x,y) == Cell.HOL
	 * \post: cellNature(x,y) == Cell.PLT
	 * \post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[, 
	 * 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre
	 */
	public void fill(int x, int y);
		
}
