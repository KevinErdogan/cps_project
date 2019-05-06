package contrat;

import Decorator.CharacterDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;

public class CharacterContrat extends CharacterDecorator{

	protected CharacterContrat(CharacterService service) {
		super(service);
	}

	public void checkInvariant() {
		
		 // \inv: getEnvi().cellNature(getWdt(), getHgt())
		 // 			in {Cell.EMP, Cell.HOL, Cell.LAD, Cell.HDR}
		 
		Cell nature = getEnvi().cellNature(getWdt(), getHgt());
		if(! (nature == Cell.EMP || nature == Cell.HOL || nature == Cell.LAD || nature == Cell.HDR)) {
			throw new InvariantError("Le Character n'est pas dans une case libre (vide, trou, echelle ou rail)");
		}
		
		// \inv: if exist Character x in getEnvi().cellContent(getWdt(), getHgt()) => x == this
		Content c = getEnvi().cellContent(getWdt(), getHgt());
		CharacterService ch = c.getCharacters().get(0);
		if(! (ch == this)) {
			throw new InvariantError("Le Character n'est pas present dans sa case (cellContent)");
		}
	}

	@Override
	public void init(EnvironmentService screen, int x, int y) {
		//pre: screen.cellNature(x,y) == EMP
		if(! (screen.cellNature(x, y) == Cell.EMP)) {
			throw new PreconditionError("Init Character sur cellule non vide");
		}
		
		super.init(screen, x, y);
		
		checkInvariant();	
	}
	
	@Override
	public void goLeft() {
		//pas de pre
		
		//captures
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		Cell cellNatW_1H_atPre = getEnvi().cellNature(getWdt()-1, getHgt());
		Cell cellNatWH_atPre = getEnvi().cellNature(getWdt(), getHgt());
		Cell cellNatWH_1_atPre = getEnvi().cellNature(getWdt(), getHgt()-1);
		boolean hasCharacWH_1_atPre = getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter();
		boolean hasCharacW_1H_atPre = getEnvi().cellContent(getWdt()-1, getHgt()).isCharacter();
		
		checkInvariant();
		
		super.goLeft();
		
		checkInvariant();
		
		 //post: getHgt() == getHgt()@Pre
		
		if(! (getHgt() == hgt_atPre)) {
			throw new PostconditionError("GoLeft a modifie hgt");		
		}
		
		 //post: getWdt()@Pre == 0 => getWdt() == getWdt()@Pre 
		//		 <=> getWdt()@Pre != 0 || getWdt() == getWdt()@Pre 
		
		if(! (wdt_atPre != 0 || getWdt() == wdt_atPre)) {
			throw new PostconditionError("Deplacement a gauche d'un Character sur le bord gauche de la map");
		}
		
		 //post: getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
		 //			in {Cell.MTL, Cell.PLT} => getWdt() == getWdt()@Pre
		
		if(! ( (cellNatW_1H_atPre != Cell.MTL && cellNatW_1H_atPre != Cell.PLT) || getWdt() == wdt_atPre ) ) {
			throw new PostconditionError("Deplacement a gauche sur une case non libre");
		}
		
		 //post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 //			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			not in {Cell.PLT, Cell.MTL, Cell.LAD}
		 // 		  && not exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			=> getWdt() == getWdt()@Pre
		
		
		if(! ( !( (cellNatWH_atPre != Cell.LAD && cellNatWH_atPre != Cell.HDR)
			  && (cellNatWH_1_atPre != Cell.PLT && cellNatWH_1_atPre != Cell.MTL && cellNatWH_1_atPre != Cell.LAD)
			  && hasCharacWH_1_atPre == false)
				|| getWdt() == wdt_atPre) ) {
				  throw new PostconditionError("Deplacement a gauche non autorisé");
			  }
		
		
		// \post: exist Character c in getEnvi().cellContent(getWdt()@Pre-1, getHgt()@Pre)@Pre
		// 				=> getWdt() == getWdt()@Pre
			  
		if( ! ( !hasCharacW_1H_atPre || getWdt() == wdt_atPre )  ) {
			throw new PostconditionError("Deplacement a gauche alors que la case est occupé par un autre personnage");
		}
		
		 // \post: getWdt()@Pre != 0 
		 // 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
		 // 			not in {Cell.MTL, Cell.PLT}
		 //		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 //				in {Cell.LAD, Cell.HDR}
		 //			  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 //				 in {Cell.PLT, Cell.MTL, Cell.LAD}
		 //			  || exist Character c in
		 //				 getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre )
		 //       && not (exist Character c in 
		 //       			getEnvi().cellContent( getWdt()@Pre-1, getHgt()@Pre )@Pre )
		 //       => getWdt() == getWdt()@Pre -1
		
		if(! ( !(wdt_atPre != 0
					&& (cellNatW_1H_atPre != Cell.MTL
					    && cellNatW_1H_atPre != Cell.PLT)
					&& ( (cellNatWH_atPre == Cell.LAD
					       || cellNatWH_atPre == Cell.HDR)
						|| (cellNatWH_1_atPre == Cell.PLT
						   || cellNatWH_1_atPre == Cell.MTL
						   || cellNatWH_1_atPre == Cell.LAD)
						|| hasCharacWH_1_atPre == true)
					&& hasCharacW_1H_atPre == false)
				|| getWdt() == wdt_atPre-1)) {
			throw new PostconditionError("Deplacement a gauche non effectue alors que les conditions sont corrects");
		}	
	}
	
	@Override
	public void goRight() {
		
		
		//captures
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		Cell cellNatWH_atPre = getEnvi().cellNature(getWdt(), getHgt());
		Cell cellNatWH_1_atPre = getEnvi().cellNature(getWdt(), getHgt()-1);
		int envWidth_atPre = getEnvi().getWidth();
		Cell cellNatWp1H_atPre = getEnvi().cellNature(getWdt()+1, getHgt());
		boolean hasCharacWH_1_atPre = getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter(); 
		boolean hasCharacWp1H_atPre = getEnvi().cellContent(getWdt()+1, getHgt()).isCharacter(); 
		
		checkInvariant();
		
		super.goRight();
		
		checkInvariant();
		
		 //post: getHgt() == getHgt()@Pre
		
		if(! (getHgt() == hgt_atPre)) {
			throw new PostconditionError("GoRight a modifie hgt");		
		}
		
		 //post: getWdt()@Pre == getEnvi().getWidth()@Pre-1 => getWdt() == getWdt()@Pre
		
		if(! ( !(wdt_atPre ==  envWidth_atPre-1) || (getWdt() == wdt_atPre) )) {
			throw new PostconditionError("Deplacement a droite d'un Character sur le bord droit de la map");
		}

		 // \post: getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
		 // 			in {Cell.MTL, Cell.PLT} 
		 // 		  => getWdt() == getWdt()@Pre
		
		if(! ( !(cellNatWp1H_atPre == Cell.MTL || cellNatWp1H_atPre == Cell.PLT) || getWdt() == wdt_atPre) ) {
			throw new PostconditionError("Deplacement a droite sur une case non libre");
		}
		
		 // \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			not in {Cell.PLT, Cell.MTL, Cell.LAD}
		 // 		  && not exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			=> getWdt() == getWdt()@Pre
		
		if(! ( !( (cellNatWH_atPre != Cell.LAD && cellNatWH_atPre != Cell.HDR)
			  && (cellNatWH_1_atPre != Cell.PLT && cellNatWH_1_atPre != Cell.MTL && cellNatWH_1_atPre != Cell.LAD)
			  && hasCharacWH_1_atPre == false)
				|| getWdt() == wdt_atPre) ) {
				  throw new PostconditionError("Deplacement a droite non autorisé");
			  }
		
		// \post: exist Character c in getEnvi().cellContent(getWdt()@Pre+1, getHgt()@Pre)@Pre
		// 				=> getWdt() == getWdt()@Pre
					  
		if( ! ( !hasCharacWp1H_atPre || getWdt() == wdt_atPre )  ) {
			throw new PostconditionError("Deplacement a droite alors que la case est occupé par un autre personnage");
		}
		
		
		 // \post: getWdt()@Pre != getEnvi().getWidth()@Pre-1 
		 // 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
		 // 			not in {Cell.MTL, Cell.PLT}
		 // 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 				in {Cell.LAD, Cell.HDR}
		 // 			  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 				 in {Cell.PLT, Cell.MTL, Cell.LAD}
		 // 			  || exist Character c in
		 // 				 getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre )
		 //        && not ( exist Character c in 
		 //        			getEnvi().cellContent( getWdt()@Pre+1, getHgt()@Pre )@Pre )
		 //        => getWdt() == getWdt()@Pre +1
		
		if(! ( !(wdt_atPre != envWidth_atPre-1 
				&& (cellNatWp1H_atPre != Cell.MTL
				    && cellNatWp1H_atPre != Cell.PLT)
				&& ( (cellNatWH_atPre == Cell.LAD
				       || cellNatWH_atPre == Cell.HDR)
					|| (cellNatWH_1_atPre == Cell.PLT
					   || cellNatWH_1_atPre == Cell.MTL
					   || cellNatWH_1_atPre == Cell.LAD)
					|| hasCharacWH_1_atPre == true)
				&& hasCharacWp1H_atPre == false)
			|| getWdt() == wdt_atPre+1)) {
			throw new PostconditionError("Deplacement a droite non effectue alors que les conditions sont corrects");
		}
	}

	@Override
	public void goUp() {
		
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		int envHeight_atPre = getEnvi().getHeight();
		Cell cellNatWH_atPre = getEnvi().cellNature(getWdt(), getHgt());
		Cell cellNatWHp1_atPre = getEnvi().cellNature(getWdt(), getHgt()+1);
		boolean hasCharacWHp1_atPre = getEnvi().cellContent(getWdt(), getHgt()+1).isCharacter();
		
		checkInvariant();
		
		super.goUp();
		
		checkInvariant();
		
		
		 // \post: getWdt() == getWdt()@Pre
		
		if(! (getWdt() == wdt_atPre)) {
			throw new PostconditionError("GoUp a modifie wdt");	
		}
		
		 // \post: getHgt()@Pre == getEnvi().getHeight()@Pre-1 => getHgt() == getHgt()@Pre
		
		if(! ( !(hgt_atPre ==  envHeight_atPre-1)
				|| getHgt() == hgt_atPre) ) {
			throw new PostconditionError("Deplacement en haut d'un Character tout en haut de la map");
		}
		 
		 // \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD}
		 // 		  || getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre+1 )@Pre
		 // 			not in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
		 // 		  || exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre+1 )@Pre
		 // 		  => getHgt() == getHgt()@Pre
		
		if(! ( !( cellNatWH_atPre != Cell.LAD
				|| (cellNatWHp1_atPre != Cell.HOL
			       && cellNatWHp1_atPre != Cell.LAD
			       && cellNatWHp1_atPre != Cell.HDR
			       && cellNatWHp1_atPre != Cell.EMP)
			   || hasCharacWHp1_atPre == true)
			|| getHgt() == hgt_atPre) ) {
			throw new PostconditionError("Deplacement en haut impossible");
		}
		
		// \post: exist Character c in getEnvi().cellContent(getWdt()@Pre, getHgt()@Pre+1)@Pre
		// 				=> getHgt() == getHgt()@Pre
		if( ! ( !hasCharacWHp1_atPre || getHgt() == hgt_atPre )  ) {
			throw new PostconditionError("Deplacement en haut alors que la case est occupé par un autre personnage");
		}
		
		 // \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			in {Cell.LAD}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre+1 )@Pre
		 // 			in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
		 // 		  &&  not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre+1 )@Pre)
		 // 		  => getHgt() == getHgt()@Pre+1
		 
		if(! ( !(cellNatWH_atPre == Cell.LAD
				&& (cellNatWHp1_atPre == Cell.HOL
			       || cellNatWHp1_atPre == Cell.LAD
			       || cellNatWHp1_atPre == Cell.HDR
			       || cellNatWHp1_atPre == Cell.EMP)
				&& hasCharacWHp1_atPre == false)
				|| getHgt() == hgt_atPre+1)) {
			throw new PostconditionError("Deplacement en haut non effectue");
		}	
	}

	@Override
	public void goDown() {
		
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		Cell cellNatWH_1_atPre = getEnvi().cellNature(getWdt(), getHgt()-1);
		boolean hasCharacWH_1_atPre = getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter();
		
		checkInvariant();
		
		super.goDown();
		
		checkInvariant();
		
	
		 // \post: getWdt() == getWdt()@Pre
		
		if(! (getWdt() == wdt_atPre)) {
			throw new PostconditionError("GoDown a modifie wdt");	
		}
		
		 // \post: getHgt()@Pre == 0 => getHgt() == getHgt()@Pre
		
		if(! ( !(hgt_atPre ==  0)
				|| getHgt() == hgt_atPre)) {
			throw new PostconditionError("Deplacement en bas d'un Character tout en bas de la map");
		}
		 
		 // \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			not in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
		 // 		  || exist Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		  => getHgt() == getHgt()@Pre
		
		if(! ( !((cellNatWH_1_atPre != Cell.HOL
				 && cellNatWH_1_atPre != Cell.LAD
				 && cellNatWH_1_atPre != Cell.HDR
				 && cellNatWH_1_atPre != Cell.EMP)
			  || hasCharacWH_1_atPre == true)
			  || getHgt() == hgt_atPre)) {
			throw new PostconditionError("Deplacement en bas impossible");
		}
		
		// \post: exist Character c in getEnvi().cellContent(getWdt()@Pre, getHgt()@Pre-1)@Pre
		// 				=> getHgt() == getHgt()@Pre
		if( ! ( !hasCharacWH_1_atPre || getHgt() == hgt_atPre )  ) {
			throw new PostconditionError("Deplacement en haut alors que la case est occupé par un autre personnage");
		}
		
		 
		 // \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			in {Cell.HOL, Cell.LAD, Cell.HDR, Cell.EMP}
		 //        && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
		 //        => getHgt() == getHgt()@Pre-1
		
		if(! ( !( (cellNatWH_1_atPre == Cell.HOL
				 || cellNatWH_1_atPre == Cell.LAD
				 || cellNatWH_1_atPre == Cell.HDR
				 || cellNatWH_1_atPre == Cell.EMP)
				&& hasCharacWH_1_atPre == false)
				|| getHgt() == hgt_atPre-1) ){
			throw new PostconditionError("Deplacement en bas non effectue");	
		}
		
	}
}
