package contrat;


import java.util.Set;

import Decorator.PlayerDecorator;
import itf.Cell;
import itf.Content;
import itf.EngineService;
import itf.EnvironmentService;
import itf.HoleService;
import itf.Item;
import itf.Move;
import itf.PlayerService;

public class PlayerContrat extends PlayerDecorator{

	public PlayerContrat(PlayerService service) {
		super(service);
	}
	
	public void checkInvariant() {
		// \inv: getNbTreasure() >= 0
		if( !(getNbTreasure()>=0))
			throw new InvariantError("inv: nbTreasure < 0");

		// \inv: getHP() >= 0 && getHP <= 3
		if( !( getHP() >=0 && getHP() <= 3 ))
			throw new InvariantError("inv: player's hp not in range :"+getHP());
	}
	

	@Override
	public void init(int w, int h, EnvironmentService envS, EngineService engS) {
		
		super.init(w, h, envS, engS);
		
		checkInvariant();
		
		//post: getEngine() == engS
		
		if(! (getEngine() == engS)) {
			throw new PostconditionError("Init Player, engine non initialise correctement");
		}
	}

	@Override
	public void step() {
		
		Move nextCommand_AtPre = getEngine().getCommands().get(getEngine().getCommands().size()-1);
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		Cell cellNatWH_atPre = getEnvi().cellNature(getWdt(), getHgt());
		Cell cellNatWH_1_atPre = getEnvi().cellNature(getWdt(), getHgt()-1);
		Cell cellNatW_1H_atPre = getEnvi().cellNature(getWdt()-1, getHgt());
		Cell cellNatWp1H_atPre = getEnvi().cellNature(getWdt()+1, getHgt());
		Cell cellNatWp1H_1_atPre = getEnvi().cellNature(getWdt()+1, getHgt()-1);
		Cell cellNatW_1H_1_atPre = getEnvi().cellNature(getWdt()-1, getHgt()-1);
		boolean hasCharacWH_1_atPre = getEnvi().hasCharacter(getWdt(), getHgt()-1);
		
		checkInvariant();
		
		super.step();
		
		checkInvariant();
		
		boolean enchute = ( (cellNatWH_atPre != Cell.LAD
				              && cellNatWH_atPre != Cell.HDR)
				           && (cellNatWH_1_atPre == Cell.HDR
				               || cellNatWH_1_atPre == Cell.HOL
				               || cellNatWH_1_atPre == Cell.EMP)
				           && hasCharacWH_1_atPre == false);
		
		if(! (!(enchute == true)
				|| (
			  (getHgt() == hgt_atPre || getHgt() == hgt_atPre-1)
			  && (getWdt() == wdt_atPre)))){
			throw new PostconditionError("erreur deplacement joueur lors d'une chute");
		}
		  
		if(! (! (enchute == false
			  && nextCommand_AtPre == Move.Right)
				||
			  ((getHgt() == hgt_atPre)
			  && (getWdt() == wdt_atPre || getWdt() == wdt_atPre+1)))) {
			throw new PostconditionError("erreur deplacement joueur vers la droite");
		}
			 
		if(! (!(enchute == false
				  && nextCommand_AtPre == Move.Left)
				||
				  ((getHgt() == hgt_atPre)
				  && (getWdt() == wdt_atPre || getWdt() == wdt_atPre-1)))) {
			throw new PostconditionError("erreur deplacement joueur vers la gauche");
		}
		
		if(! (!(enchute == false
				  && nextCommand_AtPre == Move.Up)
				  || ((getHgt() == hgt_atPre || getHgt() == hgt_atPre+1)
				  && (getWdt() == wdt_atPre)))) {
			throw new PostconditionError("erreur deplacement joueur vers le haut");
		}
		 
		
		if(! (! (enchute == false
				  && nextCommand_AtPre == Move.Down)
				  || ((getHgt() == hgt_atPre || getHgt() == hgt_atPre-1)
				  && (getWdt() == wdt_atPre)))) {
			throw new PostconditionError("erreur deplacement joueur vers le bas");
		}		 
		
		boolean found = false;
		for(HoleService h : getEngine().getHoles()) {
			if(h.getX() == wdt_atPre -1 && h.getY() == hgt_atPre-1) {
				found = true;
				break;
			}
		}
		
		if(! (! (nextCommand_AtPre == Move.DigL
			  && wdt_atPre != 0
			  && ((cellNatWH_1_atPre == Cell.MTL
			         || cellNatWH_1_atPre == Cell.PLT)
			  	  || hasCharacWH_1_atPre == true)
			  && (cellNatW_1H_atPre != Cell.MTL
			      && cellNatW_1H_atPre != Cell.PLT)
			  && cellNatW_1H_1_atPre == Cell.PLT)
			  || (getEnvi().cellNature(wdt_atPre -1, hgt_atPre-1) == Cell.HOL && found))) {
				throw new PostconditionError("erreur commande creuser a gauche");
		}
		  		
		found = false;
		for(HoleService h : getEngine().getHoles()) {
			if(h.getX() == wdt_atPre +1 && h.getY() == hgt_atPre-1) {
				found = true;
				break;
			}
		}
		
		if(! (! (nextCommand_AtPre == Move.DigR
				  && wdt_atPre != getEnvi().getWidth()-1
				  && ((cellNatWH_1_atPre == Cell.MTL
				         || cellNatWH_1_atPre == Cell.PLT)
				  	  || hasCharacWH_1_atPre == true)
				  && (cellNatWp1H_atPre != Cell.MTL
				      && cellNatWp1H_atPre != Cell.PLT)
				  && cellNatWp1H_1_atPre == Cell.PLT)
				  || (getEnvi().cellNature(wdt_atPre +1, hgt_atPre-1) == Cell.HOL && found))) {
				throw new PostconditionError("erreur commande creuser a droite");
		}
			
	}

	@Override
	public void pickUpTreasure() {
		
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		int nbTreasure_atPre = getNbTreasure();

		checkInvariant();
		
		super.pickUpTreasure();
		
		checkInvariant();
		
		// \post: getNbTreasure() == getNbTreasure()@Pre+1
		if(!(getNbTreasure() == nbTreasure_atPre+1))
			throw new PostconditionError("PickUpTreasure : nbTreasure not inc");
		
		// \post: getWdt() = getWdt()@Pre
		if(!(getWdt() == wdt_atPre))
			throw new PostconditionError("PickUpTreasure : wdt changed");
		
		// \post: getHgt() = getHgt()@Pre
		if(!(getHgt() == hgt_atPre))
			throw new PostconditionError("PickUpTreasure : hgt changed");
		
	}
	
	@Override
	public void pickUpKey(Item k) {
		 // \pre: hasKey() == false
		if(!(!hasKey()))
			throw new PreconditionError("PickUpKey : hasKey = true");
		
		// captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		int nbTreasure_atPre = getNbTreasure();
		
		checkInvariant();
		
		super.pickUpKey(k);
		
		checkInvariant();
		
		// \post: getNbTreasure() == getNbTreasure()@Pre
		if( !(getNbTreasure() == nbTreasure_atPre))
			throw new PostconditionError("PickUpKey : nbTreasure changed");
		
		// \post: getWdt() = getWdt()@Pre
		if(!(getWdt() == wdt_atPre))
			throw new PostconditionError("PickUpTreasure : wdt changed");
		
		// \post: getHgt() = getHgt()@Pre
		if(!(getHgt() == hgt_atPre))
			throw new PostconditionError("PickUpTreasure : hgt changed");
		// \post: hasKey() == true
		if( !(hasKey()))
			throw new PostconditionError("PickUpTreasure : hasKey==false");
		
		// \post: getKey() == k
		if( !(getKey() == k))
			throw new PostconditionError("PickUpTreasure : key not properly set");
	}
	
	@Override
	public void reset() {
		
		//captures
		int wdt_atPre = getWdt();
		int hgt_atPre = getHgt();
		
		checkInvariant();
		
		super.reset();
		
		checkInvariant();
		
		// \post: getWdt() = getInitialWdt()
		if(!(getWdt() == getInitialWdt()))
			throw new PostconditionError("Reset : wdt != initialWdt");
		// \post: getHgt() = getInitialHgt()
		if(!(getHgt() == getInitialHgt()))
			throw new PostconditionError("Reset : hgt != initialHgt");
		/* \post: not exist this in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre )
	     *    		&& exist this in getEnvi().cellContent( getWdt(), getHgt() )
	     */
		Set<Content> before = getEnvi().cellContent(wdt_atPre, hgt_atPre);
		boolean notexist = false, exist=false;
		for(Content c : before) {
			if(c.isCharacter() && c.getCharacter()==this) {
				notexist=true;
				break;
			}
		}
		Set<Content> after = getEnvi().cellContent(getWdt(), getHgt());
		for(Content c : after) {
			if(c.isCharacter() && c.getCharacter()==this) {
				exist=true;
				break;
			}
		}
		
		if( !( !notexist && exist ))
			throw new PostconditionError("Reset : problem when resetting position of player");
		
	    //\post: hasKey() == false
		if( !( !hasKey()))
			throw new PostconditionError("Reset : hasKey==true");
	}
	
	@Override
	public Item getKey() {
		// \pre: hasKey() == true
		if( !(hasKey()) )
			throw new PreconditionError("GetKey : player does not have key");
		
		checkInvariant();
		
		Item k = super.getKey();
		
		checkInvariant();
		
		return k;
	}
	
}