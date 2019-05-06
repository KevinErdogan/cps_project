package itf;

/**
 * \inv: getTime() >= 0 && getTime() <= 15
 */
public interface HoleService {
	
	public int getX();
	public int getY();
	public int getTime();
	
	/*
	 * \pre: x > 0
	 * \pre: y > 0
	 * 
	 * \post: getX() == x
	 * \post: getY() == y
	 * \post: getTime() == 0
	 */
	public void init(int x, int y);
	
	/*
	 * \post: getTime() == getTime()@Pre+1
	 * \post: getX() == getX()@Pre
	 * \post: getY() == getY()@Pre
	 */
	public void step();
	
}
