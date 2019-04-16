package impl;

import itf.Cell;
import itf.CharacterService;
import itf.EnvironmentService;
import itf.ScreenService;

public class CharacterImpl implements CharacterService{

	protected EnvironmentService screen;
	protected int wdt;
	protected int hgt;
	
	public CharacterImpl(EnvironmentService screen, int x, int y) {
		this.init(screen, x, y);
	}
	
	@Override
	public int getHgt() {
		return this.hgt;
	}

	@Override
	public int getWdt() {
		return this.wdt;
	}

	@Override
	public void init(EnvironmentService screen, int x, int y) {
		this.screen = screen;
		this.wdt = x;
		this.hgt = y;
	}
	/*
	 * \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
	 * 			not in {Cell.LAD, Cell.HDR}
	 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
	 * 			not in {Cell.PLT, Cell.MTL}
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
	@Override
	public void goLeft() {
		if(wdt == 0) return;
		
		if(screen.cellNature(wdt -1, hgt) == Cell.MTL
				|| screen.cellNature(wdt -1, hgt) == Cell.PLT) return;
		
		if( (screen.cellNature(wdt, hgt) != Cell.LAD
				&& screen.cellNature(wdt, hgt) != Cell.HDR) 
			&& (screen.cellNature(wdt, hgt-1) != Cell.PLT
			    && screen.cellNature(wdt, hgt-1) != Cell.MTL)
			&& (screen.cellContent)
						
						)
		
	}

	@Override
	public void goRight() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void goDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void step() {
		//nothing todo 
	}

	@Override
	public EnvironmentService getEnvi() {
		return this.screen;
	}
}
