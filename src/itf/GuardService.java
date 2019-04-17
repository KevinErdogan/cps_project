package itf;

public interface GuardService extends CharacterService{

	public int getId(); // const
	public CharacterService getTarget();
	public Move getBehavior();
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
