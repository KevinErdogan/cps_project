package contrat;

import Decorator.PlayerDecorator;
import itf.Cell;
import itf.Command;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

public class PlayerContrat extends PlayerDecorator{

	protected PlayerContrat(PlayerService service) {
		super(service);
	}

	@Override
	public void init(int w, int h, EnvironmentService envS, EngineService engS) {
		
		super.init(w, h, envS, engS);
		
		//post: getEngine() == engS
		
		if(! (getEngine() == engS)) {
			throw new PostconditionError("Init Player, engine non initialise correctement");
		}
	}

	@Override
	public void step() {
		
		Command nextCommand_AtPre = getEngine().getCommands().get(getEngine().getCommands().size()-1);
		int hgt_atPre = getHgt();
		int wdt_atPre = getWdt();
		Cell cellNatWH_atPre = getEnvi().cellNature(getWdt(), getHgt());
		Cell cellNatWH_1_atPre = getEnvi().cellNature(getWdt(), getHgt()-1);
		Cell cellNatW_1H_atPre = getEnvi().cellNature(getWdt()-1, getHgt());
		Cell cellNatWp1H_atPre = getEnvi().cellNature(getWdt()+1, getHgt());
		Cell cellNatWp1H_1_atPre = getEnvi().cellNature(getWdt()+1, getHgt()-1);
		Cell cellNatW_1H_1_atPre = getEnvi().cellNature(getWdt()-1, getHgt()-1);
		boolean hasCharacWH_1_atPre = getEnvi().cellContent(getWdt(), getHgt()-1).isCharacter();
		
		super.step();
		
		boolean enchute = ( (cellNatWH_atPre != Cell.LAD
				              && cellNatWH_atPre != Cell.HDR)
				           && (cellNatWH_1_atPre == Cell.HDR
				               || cellNatWH_1_atPre == Cell.HOL
				               || cellNatWH_1_atPre == Cell.EMP)
				           && hasCharacWH_1_atPre == false);
		
		// \post: getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
		 // 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre)
		 // 		  => goDown()@Pre
		
		if(! (enchute == true
			  && (getHgt() == hgt_atPre || getHgt() == hgt_atPre-1)
			  && (getWdt() == wdt_atPre))){
			throw new PostconditionError("erreur deplacement joueur lors d'une chute");
		}
		  
		 // \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
		 // 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
		 // 		  && getEngine().getNextCommand()@Pre == Move.Right
		 // 		  => goRight()@Pre
		  
		if(! (enchute == false
			  && nextCommand_AtPre == Command.Right
			  && (getHgt() == hgt_atPre)
			  && (getWdt() == wdt_atPre || getWdt() == wdt_atPre+1))) {
			throw new PostconditionError("erreur deplacement joueur vers la droite");
		}
		
		 // \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
		 // 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
		 // 		  && getEngine().getNextCommand()@Pre == Move.Left
		 // 		  => goLeft()@Pre
		 
		if(! (enchute == false
				  && nextCommand_AtPre == Command.Left
				  && (getHgt() == hgt_atPre)
				  && (getWdt() == wdt_atPre || getWdt() == wdt_atPre-1))) {
			throw new PostconditionError("erreur deplacement joueur vers la gauche");
		}
		
		 // \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
		 // 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
		 // 		  && getEngine().getNextCommand()@Pre == Move.Up
		 // 		  => goUp()@Pre
		
		if(! (enchute == false
				  && nextCommand_AtPre == Command.Up
				  && (getHgt() == hgt_atPre || getHgt() == hgt_atPre+1)
				  && (getWdt() == wdt_atPre))) {
			throw new PostconditionError("erreur deplacement joueur vers le haut");
		}
		 
		 // \post: not (getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre )@Pre
		 // 			not in {Cell.LAD, Cell.HDR}
		 // 		  && getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 		    in {Cell.HDR, Cell.HOL, Cell.EMP}
		 // 		  && not exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre))
		 // 		  && getEngine().getNextCommand()@Pre == Move.Down
		 // 		  => goDown()@Pre
		
		if(! (enchute == false
				  && nextCommand_AtPre == Command.Down
				  && (getHgt() == hgt_atPre || getHgt() == hgt_atPre-1)
				  && (getWdt() == wdt_atPre))) {
			throw new PostconditionError("erreur deplacement joueur vers le bas");
		}
		 
		 // \post: getEngine().getNextCommand()@Pre == Move.DigL
		 // 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			in {Cell.MTL, Cell.PLT}
		 // 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
		 // 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre )@Pre
		 // 			not in {Cell.MTL, Cell.PLT}
		 // 		  && getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 )@Pre
		 // 			== Cell.PLT
		 // 		  => getEnvi().cellNature( getWdt()@Pre-1, getHgt()@Pre-1 ) == Cell.HOL
		
		if(! (nextCommand_AtPre == Command.DigL
			  && ((cellNatWH_1_atPre == Cell.MTL
			         || cellNatWH_1_atPre == Cell.PLT)
			  	  || hasCharacWH_1_atPre == true)
			  && (cellNatW_1H_atPre != Cell.MTL
			      && cellNatW_1H_atPre != Cell.PLT)
			  && cellNatW_1H_1_atPre == Cell.PLT
			  && getEnvi().cellNature(wdt_atPre -1, hgt_atPre-1) == Cell.HOL)) {
			throw new PostconditionError("erreur commande creuser a gauche");
		}
		  
		 // \post: getEngine().getNextCommand()@Pre == Move.DigR
		 // 		  && ( getEnvi().cellNature( getWdt()@Pre, getHgt()@Pre-1 )@Pre
		 // 			in {Cell.MTL, Cell.PLT}
		 // 		     || exist (Character c in getEnvi().cellContent( getWdt()@Pre, getHgt()@Pre-1 )@Pre) )
		 // 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre )@Pre
		 // 			not in {Cell.MTL, Cell.PLT}
		 // 		  && getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 )@Pre
		 // 			== Cell.PLT
		 // 		  => getEnvi().cellNature( getWdt()@Pre+1, getHgt()@Pre-1 ) == Cell.HOL
		
		if(! (nextCommand_AtPre == Command.DigR
				  && ((cellNatWH_1_atPre == Cell.MTL
				         || cellNatWH_1_atPre == Cell.PLT)
				  	  || hasCharacWH_1_atPre == true)
				  && (cellNatWp1H_atPre != Cell.MTL
				      && cellNatWp1H_atPre != Cell.PLT)
				  && cellNatWp1H_1_atPre == Cell.PLT
				  && getEnvi().cellNature(wdt_atPre +1, hgt_atPre-1) == Cell.HOL)) {
				throw new PostconditionError("erreur commande creuser a droite");
			}
	}

	
	
}
