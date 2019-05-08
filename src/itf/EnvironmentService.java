package itf;

import java.util.Set;

/**
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 			cellNature(x,y) in {Cell.MTL, Cell.PLT, Cell.LAD} => cellContent(x,y) = empty
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 				exist Item i in cellContent(x,y)
 * 					=> (cellNature(x,y) in {Cell.EMP}
 * \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
 * 				exist Item i and Item g in cellContent(x,y)
 * 				    => i == g
 */
public interface EnvironmentService extends EditableScreenService{
	
	/*
	 * \pre: (0 <= y && y < getHeight())
	 * 		&& ( 0 <= x && x < getWidth())
	 */
	public Set<Content> cellContent(int x, int y);
	
	public void init(int w, int h);
	
	
	/*
	 * \pre: cellContent(x,y) contains c
	 * 
	 * \post: cellContent(x,y) contains not c
	 */
	public void getOut(CharacterService c, int x, int y);
	
	/*
	 * \pre: cellContent(x,y) contains not c
	 * 
	 * \post: cellContent(x,y) contains c
	 */
	public void getIn(CharacterService c, int x, int y);
	
	/*
	 * \pre: cellContent(x,y) contains i
	 * 
	 * \post: cellContent(x,y) contains not i
	 */
	public void getOut(Item i, int x, int y);
	
	/*
	 * \pre: cellContent(x,y) contains not i
	 * 
	 * \post: cellContent(x,y) contains i
	 */
	public void getIn(Item i, int x, int y);
	
	
	/* 
	 * \post: exist Character C in getEnvi().cellContent(x,y) => true
	 * 
	 * \post: not exist Character C in getEnvi().cellContent(x,y) => false
	 */
	public boolean hasCharacter(int x, int y);
		
	/* 
	 * \post: exist Item I in getEnvi().cellContent(x,y) => true
	 * 
	 * \post: not exist Item I in getEnvi().cellContent(x,y) => false
	 */
	public boolean hasItem(int x, int y);
	
	/*
	 * \pre: exist Item I in getEnvi().cellContent(x,y)
	 */
	public Item getItem(int x, int y);
	
	
	
}
