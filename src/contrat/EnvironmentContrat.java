package contrat;

import java.util.Set;

import Decorator.EnvironmentDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;

public class EnvironmentContrat extends EnvironmentDecorator{
	
	protected EnvironmentContrat(EnvironmentService service) {
		super(service);
	}

	public void checkInvariant() {
	   //inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
       // 			forall Character c1, c2 in cellContent(x,y),
       // 				 c1 = c2
		
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Set<Content> contents = cellContent(x, y);
				CharacterService character = null;
				for(Content c : contents) {
					if(c.isCharacter() && character == null) {
						character = c.getCharacter();
					}
					else if(c.isCharacter() && character != null) {
						if(! (c == character)) {
							throw new InvariantError("2 Character presents sur la meme cellule");
						}
					}
				}
			}
		
       // \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
       // 			cellNature(x,y) in {Cell.MTL, Cell.PLT} => cellContent(x,y) = empty
		
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Cell cellnat = cellNature(x, y);
				if(cellnat == Cell.MTL || cellnat == Cell.PLT) {
					if(! (cellContent(x, y).isEmpty())){
						throw new InvariantError("Content present(s) dans une case non libre (MTL ou PLT)");
					}
				}
			}
		
       // \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
       // 				exist Treasure t in cellContent(x,y)
       // 					=> (cellNature(x,y) = Cell.EMP &&
       // 						cellNature(x,y-1) in {Cell.PLT, Cell.MTL})

		//Treasure TODO
		
	}

	@Override
	public void init(int h, int w) {
		
		//pre: 0 < h && 0 < w
		
		if(! ( 0 < h && 0 < w )) {
			throw new PreconditionError("Init EnvironmentContrat avec taille(s) negative(s)");
		}
		
		checkInvariant();
		
		super.init(h, w);
		
		checkInvariant();
		
	}

	@Override
	public Set<Content> cellContent(int x, int y) {
		
		//pre: (0 <= y && y < getHeight())
		// 		&& ( 0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth()) ) {
			throw new PreconditionError("cellContent sur une case en dehors des limites de la map");
		 }
		
		checkInvariant();
		
		Set<Content> res = super.cellContent(x, y);
		
		checkInvariant();
		
		return res;
	}

	@Override
	public boolean hasCharacter(int x, int y) {
		
		 // \pre: (0 <= y && y < getHeight())
		 // 		&& ( 0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth()) ) {
			throw new PreconditionError("hasCharacter sur une case en dehors des limites de la map");
		 }
		  
		checkInvariant(); 
		
		boolean res =  super.hasCharacter(x, y);
		
		checkInvariant();
		
		Set<Content> contents = cellContent(x, y);
		boolean testExist = false;
		
		for(Content c : contents) {
			if(c.isCharacter()) {
				testExist = true;
				break;
			}
		}
	
		// \post: exist Character C in getEnvi().cellContent(x,y) => true
		// \post: not exist Character C in getEnvi().cellContent(x,y) => false
		
		if(! (res == testExist)) {
			throw new PostconditionError("hasCharacter ne retourne pas le bon resultat");
		}

		return res;
	}
	
	
	
	
}
