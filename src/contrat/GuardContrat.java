package contrat;

import java.util.Set;

import Decorator.GuardDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;
import itf.GuardService;
import itf.Move;

public class GuardContrat extends GuardDecorator{

	public GuardContrat(GuardService service) {
		super(service);
	}
	
	public void checkInvariant() {
		 // LAD_On_NonFreeCell() defined by 
		 // 			getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
		 // 			&& ((getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		          not in {Cell.HOL, Cell.HDR, Cell.EMP}
		 // 				  || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
		 // 				|| getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre in {Cell.PLT, Cell.MTL})
		 
		
		Cell nature = getEnvi().cellNature( getWdt(), getHgt()-1) ;
		Cell nature2 = getEnvi().cellNature( getWdt(), getHgt()-1 );
		Set<Content> contents = getEnvi().cellContent( getWdt(), getHgt()-1 );
		boolean existCharacter = false;
		for(Content cont : contents) {
			if(cont.isCharacter()) {
				existCharacter = true;
				break;
			}
		}
		
		boolean LAD_On_NonFreeCell = getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
				  				&& (nature != Cell.HOL && nature != Cell.HDR && nature !=Cell.EMP)
				  				|| existCharacter
				  				|| (nature2 == Cell.PLT || nature2 == Cell.MTL);
		 // Down_Non_Free_Cell() defined by 
		 // 			getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre in {Cell.PLT, Cell.MTL}
		nature = getEnvi().cellNature( getWdt(), getHgt()-1 );
		boolean Down_Non_Free_Cell =  nature == Cell.PLT || nature == Cell.MTL;
		
		 // Right_Non_Free_Cell() defined by
		 // 			getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre in {Cell.PLT, Cell.MTL}
		nature = getEnvi().cellNature( getWdt()+1, getHgt());
		 boolean Right_Non_Free_Cell = nature == Cell.PLT || nature == Cell.MTL;
		
		 // Left_Non_Free_Cell() defined by 
		 // 			getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre in {Cell.PLT, Cell.MTL}
		nature = getEnvi().cellNature( getWdt()-1, getHgt());
		boolean Left_Non_Free_Cell = nature == Cell.PLT || nature == Cell.MTL;
		
		// \inv: LAD_On_NonFreeCell() && getTarget().getWdt() == getWdt()@Pre && getTarget().getHgt() > getHgt()@Pre
		// => getBehavior() ==  Move.Up
		
		if( !( !(LAD_On_NonFreeCell && getTarget().getWdt() == getWdt() && getTarget().getHgt() > getHgt()) || getBehavior() == Move.Up ) )
			throw new InvariantError("Inv: 1");
		
		/*
		\inv: LAD_On_NonFreeCell()
		 * 			&& getTarget().getWdt() == getWdt()@Pre 
		 * 		    && getTarget().getHgt() < getHgt()@Pre
		 * 			&& !Down_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Down
		*/
		
		if( !( !(LAD_On_NonFreeCell
		  			&& getTarget().getWdt() == getWdt() 
		  		    && getTarget().getHgt() < getHgt()
		  			&& !Down_Non_Free_Cell)
		  		|| getBehavior() ==  Move.Down) )
			throw new InvariantError("Inv: 2");
		
		 /* \inv: LAD_On_NonFreeCell()
		 * 			&& getTarget().getWdt() < getWdt()@Pre 
		 * 		    && getTarget().getHgt() == getHgt()@Pre
		 * 			&& !Left_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Left
		 */
		
		if ( !( !(LAD_On_NonFreeCell
		  			&& getTarget().getWdt() < getWdt() 
		 		    && getTarget().getHgt() == getHgt()
		  			&& !Left_Non_Free_Cell)
		  			|| getBehavior() ==  Move.Left ) )
			throw new InvariantError("Inv: 3");
		
		 /* \inv: LAD_On_NonFreeCell()
		 * 			&& getTarget().getWdt() > getWdt()@Pre 
		 * 		    && getTarget().getHgt() == getHgt()@Pre
		 * 			&& !Right_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Right
		 */
		if (!( !(LAD_On_NonFreeCell
				 			&& getTarget().getWdt() > getWdt()
				 		    && getTarget().getHgt() == getHgt()
				 			&& !Right_Non_Free_Cell)
				 			|| getBehavior() ==  Move.Right))
			throw new InvariantError("Inv: 4");
		

		
		 /* \inv: LAD_On_NonFreeCell()
		 * 			&&  |getWdt()@Pre -getTarget().getWdt()| > |getHgt()@Pre -getTarget().getHgt()| 
		 * 			&& getTarget().getHgt() < getHgt()@Pre
		 *  		&& !Down_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Down
		 */ 
		if (!( !(LAD_On_NonFreeCell
				  			&&  Math.abs(getWdt() -getTarget().getWdt()) > Math.abs(getHgt() -getTarget().getHgt()) 
				  			&& getTarget().getHgt() < getHgt()
				  			&& !Down_Non_Free_Cell)
				  			|| getBehavior() ==  Move.Down ))
			throw new InvariantError("Inv: 6");
		
		 /* \inv: LAD_On_NonFreeCell()
		 * 			&&  |getWdt()@Pre -getTarget().getWdt()| < |getHgt()@Pre -getTarget().getHgt()| 
		 * 			&& getTarget().getWdt() > getWdt()@Pre
		 * 			&& !Right_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Right
		 */ 
		if (!( !(LAD_On_NonFreeCell
				  			&&  Math.abs(getWdt() -getTarget().getWdt()) < Math.abs(getHgt() -getTarget().getHgt())
				  			&& getTarget().getWdt() > getWdt()
				  			&& !Right_Non_Free_Cell)
				  			|| getBehavior() ==  Move.Right ))
			throw new InvariantError("Inv: 7");
		
		 /* \inv: LAD_On_NonFreeCell()
		 * 			&&  |getWdt()@Pre -getTarget().getWdt()| < |getHgt()@Pre -getTarget().getHgt()| 
		 * 			&& getTarget().getWdt() < getWdt()@Pre
		 * 			&& !Left_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Left
		 */ 
		if (!( !(LAD_On_NonFreeCell
				  			&&  Math.abs(getWdt() -getTarget().getWdt()) < Math.abs(getHgt() -getTarget().getHgt()) 
				  			&& getTarget().getWdt() < getWdt()
				  			&& !Left_Non_Free_Cell)
				  			|| getBehavior() ==  Move.Left))
			throw new InvariantError("Inv: 8");
		
		 /* \inv:   ! LAD_On_NonFreeCell()
		 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
		 * 			&& getTarget().getHgt() > getHgt()@Pre
		 * 			=> getBehavior() ==  Move.Up
		 */   
		if (!(  ! (! LAD_On_NonFreeCell
				  			&& getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
				  			&& getTarget().getHgt() > getHgt())
				  			|| getBehavior() ==  Move.Up ))
			throw new InvariantError("Inv: 9");
		
		 /* \inv:   ! LAD_On_NonFreeCell()
		 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.LAD
		 * 			&& getTarget().getHgt() < getHgt()@Pre
		 * 			&& !Down_Non_Free_Cell()
		 * 			=> getBehavior() ==  Move.Down
		 */ 
		if (!( !(! LAD_On_NonFreeCell
				  			&& getEnvi().cellNature( getWdt(), getHgt() ) == Cell.LAD
				  			&& getTarget().getHgt() < getHgt()
				  			&& !Down_Non_Free_Cell)
				  			|| getBehavior() ==  Move.Down ))
			throw new InvariantError("Inv: 10");
		
		
		 /* \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 * 			   in {Cell.HOL, Cell.HDR}
		 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
		 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
		 * 		  && getTarget().getWdt() > getWdt()@Pre
		 * 			=> getBehavior() ==  Move.Right
		 */ 
		nature = getEnvi().cellNature(getWdt(), getHgt());
		nature2 = getEnvi().cellNature( getWdt(), getHgt()-1 );
		
		contents = getEnvi().cellContent( getWdt(), getHgt()-1);
		existCharacter = false;
		for(Content cont : contents) {
			if(cont.isCharacter()) {
				existCharacter = true;
				break;
			}
		}
		
		if (!( !( (  (nature == Cell.HOL || nature ==  Cell.HDR)
				 	  || (nature2 !=  Cell.HOL && nature2 !=  Cell.HDR && nature2 !=  Cell.EMP)
				 	  || existCharacter)
				  	   && getTarget().getWdt() > getWdt())
				  	|| getBehavior() ==  Move.Right ) )
			throw new InvariantError("Inv: 12");
		
		 /* \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 * 			   in {Cell.HOL, Cell.HDR}
		 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
		 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
		 * 		  && getTarget().getWdt() < getWdt()@Pre
		 * 			=> getBehavior() ==  Move.Left
		 */ 
		nature = getEnvi().cellNature( getWdt(), getHgt() );
		nature2 = getEnvi().cellNature( getWdt(), getHgt()-1);
		
		contents = getEnvi().cellContent( getWdt(), getHgt()-1 );
		existCharacter = false;
		for(Content cont : contents) {
			if(cont.isCharacter()) {
				existCharacter = true;
				break;
			}
		}
		
		if (!( !( (  (nature == Cell.HOL || nature == Cell.HDR)
				 	||  (nature2 != Cell.HOL && nature2!= Cell.HDR && nature2!= Cell.EMP)
				 	|| existCharacter )
				 	&& getTarget().getWdt() < getWdt()
				 )
				 || getBehavior() ==  Move.Left ))
			throw new InvariantError("Inv: 13");
		
		 /* \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 * 			   in {Cell.HOL, Cell.HDR}
		 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
		 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
		 * 		  && getTarget().getHgt() < getHgt()@Pre && getTarget().getWdt() == getWdt()@Pre
		 * 			=> getBehavior() ==  Move.Down
		 */ 
		
		nature = getEnvi().cellNature( getWdt(), getHgt() );
		nature2 = getEnvi().cellNature( getWdt(), getHgt()-1);
		
		contents = getEnvi().cellContent( getWdt(), getHgt()-1 );
		existCharacter = false;
		for(Content cont : contents) {
			if(cont.isCharacter()) {
				existCharacter = true;
				break;
			}
		}
		
		if (!( !(( (nature == Cell.HOL || nature == Cell.HDR)
				  || (nature2 != Cell.HOL && nature2!= Cell.HDR && nature2!= Cell.EMP)
				  || existCharacter)
				  && getTarget().getHgt() < getHgt() && getTarget().getWdt() == getWdt()
				)
				|| getBehavior() ==  Move.Down ))
			throw new InvariantError("Inv: 14");
		
		 /* \inv: ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 * 			   in {Cell.HOL, Cell.HDR}
		 * 	         || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 * 		       not in {Cell.HOL, Cell.HDR, Cell.EMP}
		 * 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) ))
		 * 		  && getTarget().getWdt() == getWdt()@Pre
		 * 		  && getTarget().getHgt() == getHgt()@Pre
		 * 			=> getBehavior() ==  Move.Neutral 
		 */
		
		nature = getEnvi().cellNature( getWdt(), getHgt() );
		nature2 = getEnvi().cellNature( getWdt(), getHgt()-1);
		
		contents = getEnvi().cellContent( getWdt(), getHgt()-1 );
		existCharacter = false;
		for(Content cont : contents) {
			if(cont.isCharacter()) {
				existCharacter = true;
				break;
			}
		}
		
		if (!(  !( (( (nature == Cell.HOL || nature == Cell.HDR)
				  	|| (nature2 != Cell.HOL && nature2!= Cell.HDR && nature2!= Cell.EMP)
				  	|| existCharacter ))
				  		  && getTarget().getWdt() == getWdt()
				  		  && getTarget().getHgt() == getHgt()
				 )
				 || getBehavior() ==  Move.Neutral   ))
			throw new InvariantError("Inv: 15");
		
		 // \inv : timeinhole entre 0 et 15 (inclus)
		if (!( getTimeInHole()>=0 && getTimeInHole() <=15 ))
			throw new InvariantError("Inv: timeInHole not in range (0,15)");
		 
	}

	@Override
	public void init(EnvironmentService screen, int x, int y, CharacterService target, int id) {
		super.init(screen, x, y, target, id);

		checkInvariant();
	/*
	 * \post: getTimeInHole() == 0
	 */
	if( !(getTimeInHole() ==0))
		throw new PostconditionError("Init : timeinhole not initialized");
			
	 // \post: getTarget() == target
		
	if ( ! ( getTarget() == target ))
		throw new PostconditionError("Init : target not initialized");
		
	 // \post: getId() == id
	if ( ! ( getId()== id ))
		throw new PostconditionError("Init : id not initialized");
		
	}
	
	@Override
	public void climbLeft() {

		//	\pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
		if ( !(getEnvi().cellNature(getWdt(), getHgt()) == Cell.HOL ) )
			throw new PreconditionError("ClimbLeft : not in hole");
	
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		Cell nature_atPre = getEnvi().cellNature( wdt_atPre-1, hgt_atPre+1 );
		
		checkInvariant();
	
		super.climbLeft();
		
		checkInvariant();
		
		// \post: getWdt()@Pre == 0 => getWdt() == getWdt()@Pre
		//		  && getHgt() == getHgt()@Pre
		if( !( !(wdt_atPre ==0) || getWdt() == wdt_atPre && getHgt() == hgt_atPre ) )
			throw new PostconditionError("ClimbLeft: climbed out of the map");
		
		//\post: getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre+1 )@Pre in {Cell.PLT, Cell.MTL} 
		// 		   => getWdt() == getWdt()@Pre 
		// 			   && getHgt() == getHgt()@Pre
		
		if (! ( !(nature_atPre == Cell.PLT || nature_atPre == Cell.MTL) || getWdt() == wdt_atPre && getHgt() == hgt_atPre ))
			throw new PostconditionError("ClimbLeft : can not climb on Cell.PLT or Cell.MTL");
		
		
		/*
		 * \post: getWdt()@Pre != 0 
		 * 			&& getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre+1 )@Pre not in {Cell.PLT, Cell.MTL}
		 * 		   => getWdt() == getWdt()@Pre-1 && getHgt() == getHgt()@Pre+1
		 * 				&& getTimeInHole() == 0
		 * 				&& not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
		 *        		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
		 */
		Set<Content> contentsBefore = getEnvi().cellContent(wdt_atPre, hgt_atPre);
		Set<Content> contentsAfter = getEnvi().cellContent(getWdt(), getHgt());
		boolean before = false, after =false;
		
		for(Content c : contentsBefore) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				before = true;
				break;
			}
		}
		
		for(Content c : contentsAfter) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				after = true;
				break;
			}
		}
		
		if( ! ( !(wdt_atPre !=0 && (nature_atPre!= Cell.PLT && nature_atPre!=Cell.MTL) ) || getWdt() == wdt_atPre-1 && getHgt() == hgt_atPre+1 && getTimeInHole()==0 && !before && after ) )
			throw new PostconditionError("ClimbLeft : expected climb but not climbed");
	}
	
	@Override
	public void climbRight() {
		//	\pre: getEnvi().cellNature( getWdt(), getHgt() ) == Cell.HOL
			if ( !(getEnvi().cellNature(getWdt(), getHgt()) == Cell.HOL ) )
				throw new PreconditionError("ClimbLeft : not in hole");
		
			// captures
			int wdt_atPre = getWdt();
			int hgt_atPre = getHgt();
			Cell nature_atPre = getEnvi().cellNature( wdt_atPre+1, hgt_atPre+1 );
			
			checkInvariant();
		
			super.climbRight();
			
			checkInvariant();
			
			// \post: getWdt()@Pre == getEnvi().getWidth()@Pre-1  => getWdt() == getWdt()@Pre
			// 															&& getHgt() == getHgt()@Pre
			if( !( !(wdt_atPre ==getEnvi().getWidth()-1) || getWdt() == wdt_atPre && getHgt() == hgt_atPre ) )
				throw new PostconditionError("ClimbLeft: climbed out of the map");
			
			//\post: getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre+1 )@Pre in {Cell.PLT, Cell.MTL} 
			// 		   => getWdt() == getWdt()@Pre && getHgt() == getHgt()@Pre
			
			if (! ( !(nature_atPre == Cell.PLT || nature_atPre == Cell.MTL) || getWdt() == wdt_atPre && getHgt() == hgt_atPre ))
				throw new PostconditionError("ClimbLeft : can not climb on Cell.PLT or Cell.MTL");
			
			/*
			 * \post: getWdt()@Pre != getEnvi().getWidth()@Pre-1  
			 * 			&& getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre+1 )@Pre not in {Cell.PLT, Cell.MTL}
			 * 		   => getWdt() == getWdt()@Pre+1 && getHgt() == getHgt()@Pre+1 
			 * 				&& getTimeInHole() == 0
			 * 				&& not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
			 *        		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
			 */
			Set<Content> contentsBefore = getEnvi().cellContent(wdt_atPre, hgt_atPre);
			Set<Content> contentsAfter = getEnvi().cellContent(getWdt(), getHgt());
			boolean before = false, after =false;
			
			for(Content c : contentsBefore) {
				if(c.isCharacter() && c.getCharacter() == getDelegate()) {
					before = true;
					break;
				}
			}
			
			for(Content c : contentsAfter) {
				if(c.isCharacter() && c.getCharacter() == getDelegate()) {
					after = true;
					break;
				}
			}
			
			if( ! ( !(wdt_atPre !=getEnvi().getWidth()-1 && (nature_atPre!= Cell.PLT && nature_atPre!=Cell.MTL) ) || getWdt() == wdt_atPre+1 && getHgt() == hgt_atPre+1 && getTimeInHole()==0 && !before && after ) )
				throw new PostconditionError("ClimbLeft : expected climb but not climbed");
	}
	
	@Override
	public void die() {
				
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		
		checkInvariant();
		
		super.die();
		
		checkInvariant();
		
		// \post: getWdt() = getInitialWdt()
		if( !( getWdt() == getInitialWdt())  )
			throw new PostconditionError("Die : wdt != initialWdt");
		
		// \post: getHgt() = getInitialHgt()
		if( !( getHgt() == getInitialHgt())  )
			throw new PostconditionError("Die : hgt != initialHgt");
		
		// \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
	    //    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
		Set<Content> contentsBefore = getEnvi().cellContent(wdt_atPre, hgt_atPre);
		Set<Content> contentsAfter = getEnvi().cellContent(getWdt(), getHgt());
		boolean before = false, after =false;
		
		for(Content c : contentsBefore) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				before = true;
				break;
			}
		}
		
		for(Content c : contentsAfter) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				after = true;
				break;
			}
		}
		
		if( !( !before && after ) )
			throw new PostconditionError("Die : contents not properly set");
		
	    // \post: getTimeInHole() == 0
		if( !(getTimeInHole()==0))
			throw new PostconditionError("Die : timeInHole !=0");
	}
	
	
	@Override
	public void reset() {
		
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();

		checkInvariant();
		
		super.reset();
	
		checkInvariant();
		

		// \post: getWdt() = getInitialWdt()
		if( !( getWdt() == getInitialWdt())  )
			throw new PostconditionError("Reset : wdt != initialWdt");
		
		// \post: getHgt() = getInitialHgt()
		if( !( getHgt() == getInitialHgt())  )
			throw new PostconditionError("Reset : hgt != initialHgt");
		// \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
	    //    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
		Set<Content> contentsBefore = getEnvi().cellContent(wdt_atPre, hgt_atPre);
		Set<Content> contentsAfter = getEnvi().cellContent(getWdt(), getHgt());
		boolean before = false, after =false;
		
		for(Content c : contentsBefore) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				before = true;
				break;
			}
		}
		
		for(Content c : contentsAfter) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				after = true;
				break;
			}
		}
		
		
		if( !( !before && after ) )
			throw new PostconditionError("Reset : contents not properly set");
		
     // \post: hasTreasure() == false
		if( !( hasItem() == false ) )
			throw new PostconditionError("Reset : hasTreasure = true after reset");
		
     // \post: getTimeInHole() == 0
		if( !( getTimeInHole() == 0 ))
			throw new PostconditionError("Reset : timeInHole !=0");
	
	}
	
	@Override
	public void step() {
		
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		int timeInHole_atPre = getTimeInHole();
		Cell nature_atPre = getEnvi().cellNature(wdt_atPre, hgt_atPre);
		Cell nature_1_atPre = getEnvi().cellNature(wdt_atPre, hgt_atPre-1);
		Move behavior_atPre = getBehavior();
		
		Set<Content> contents = getEnvi().cellContent(wdt_atPre, hgt_atPre-1);
		boolean isChar = false;
		for(Content c : contents) {
			if(c.isCharacter() && c.getCharacter() == getDelegate()) {
				isChar=true;
				break;
			}
		}
		
		
		checkInvariant();
		
		super.step();
		
		checkInvariant();
		
		/*
		 * WillFall() defined by 
		 * 		 getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 * 			not in {Cell.LAD, Cell.HDR, Cell.HOL}
		 * 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 * 		    in {Cell.HDR, Cell.HOL, Cell.EMP, Cell.DOR}
		 * 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
		 */
		
		
		
		boolean WillFall = (nature_atPre != Cell.LAD && nature_atPre != Cell.HDR && nature_atPre != Cell.HOL)
							&& (nature_1_atPre == Cell.HDR && nature_1_atPre == Cell.HOL && nature_1_atPre == Cell.EMP 
										&& nature_1_atPre == Cell.DOR)
							&& !isChar;
		
		
		/*
		 * \post: WillFall()
		 * 		  => goDown()@Pre
		 */
		
		if( ! ( !WillFall || getHgt()==hgt_atPre-1 ))
			throw new PostconditionError("Step : does not fall");
		/*
		 * \post: ! WillFall() 
		 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
		 * 			&& getTimeInHole()@Pre < 5
		 * 		  => getTimeInHole() == getTimeInHole()@Pre+1
		 */
		if(! (  !(!WillFall && nature_atPre == Cell.HOL && timeInHole_atPre < 5) || getTimeInHole() ==timeInHole_atPre+1)   )
			throw new PostconditionError("Step : post2");
		
		 /* \post: ! WillFall() 
		 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
		 * 			&& getTimeInHole()@Pre == 5
		 * 			&& getBehavior()@Pre == Move.Left
		 * 		  => climbLeft()
		 */
		if(! ( ! (!WillFall && nature_atPre == Cell.HOL && timeInHole_atPre == 5 && behavior_atPre == Move.Left) || getWdt()==wdt_atPre-1 && getHgt()==hgt_atPre+1 )   )
			throw new PostconditionError("Step : post3");
		
		 /* \post: ! WillFall() 
		 * 			&& getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre == Cell.HOL
		 * 			&& getTimeInHole()@Pre == 5
		 * 			&& getBehavior()@Pre == Move.Right
		 * 		  => climbRight()
		 */ 
		if(! ( ! (!WillFall && nature_atPre == Cell.HOL && timeInHole_atPre == 5 && behavior_atPre == Move.Right) || getWdt()==wdt_atPre+1 && getHgt()==hgt_atPre+1 )   )
			throw new PostconditionError("Step : post4");
		
		 /* \post: not WillFall()
		 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
		 * 			&& getBehavior()@Pre == Move.Right
		 * 		  => goRight()@Pre 
		 */ 
		
		if(! ( !(!WillFall && nature_atPre!= Cell.HOL && behavior_atPre == Move.Right) || getWdt()==wdt_atPre+1))
			throw new PostconditionError("Step : post5");
		 /* \post: not WillFall()
		 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
		 * 			&& getBehavior()@Pre == Move.Left
		 * 		  => goLeft()@Pre 
		 */
		if(! ( !(!WillFall && nature_atPre!= Cell.HOL && behavior_atPre == Move.Left) || getWdt()==wdt_atPre-1))
			throw new PostconditionError("Step : post6");
		
		 /* \post: not WillFall()
		 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
		 * 			&& getBehavior()@Pre == Move.Up
		 * 		  => goUp()@Pre 
		 */ 
		if(! ( !(!WillFall && nature_atPre!= Cell.HOL && behavior_atPre == Move.Up) || getHgt()==hgt_atPre+1))
			throw new PostconditionError("Step : post7");
		
		 /* \post: not WillFall()
		 * 			&&  getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre != Cell.HOL 
		 * 			&& getBehavior()@Pre == Move.Down
		 * 		  => goDown()@Pre 
		 */
		if(! ( !(!WillFall && nature_atPre!= Cell.HOL && behavior_atPre == Move.Down) || getHgt()==hgt_atPre-1))
			throw new PostconditionError("Step : post8");
		
	}

}