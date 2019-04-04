package itf;


/**
 * \inv: isPlayable() <=> forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 						cellNature(x,y) != Cell.HOL
 * 		 	 			&& forall x in [O, getWidth()[, 
 * 							cellNature(x,0) == Cell.MTL
 */
public interface EditableScreen extends ScreenService{

	public boolean isPlayable();

	/*
	 * \pre: (0 <= y && y < getHeight())
	 * 		&& ( 0 <= x && x < getWidth())
	 * \post: cellNature(x,y) = c
	 * \post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[,
	 * 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre
	 */
	public void setNature(int x, int y, Cell c);
	
	
	
	
}
