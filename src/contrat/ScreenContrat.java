package contrat;

import Decorator.ScreenDecorator;
import itf.Cell;
import itf.ScreenService;

public class ScreenContrat extends ScreenDecorator{

	public ScreenContrat(ScreenService service) {
		super(service);
	}

	public void checkInvariant() {
		//nothing to do
	}

	@Override
	public Cell cellNature(int x, int y) {
		
		 //pre: (0 <= y && y < getHeight()) 
		 //       && (0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth()) ) {
			throw new PreconditionError("cellNature sur une case en dehors des limites de la map");
		}
		
		return super.cellNature(x, y);
		
		
	}

	@Override
	public void init(int w, int h) {
		
		//pre: 0 < h && 0 < w
		 
		if(! ( 0 < h && 0 < w )) {
			throw new PreconditionError("Init screen avec taille(s) negative(s)");
		}
		
		super.init(w, h);
		
		//post: getHeight() == h
		
		if(! (getHeight() == h)) {
			throw new PostconditionError("Init screen, initialisation incorrect de hgt");
		}
		
		//post: getWidth() == w
		
		if(! (getWidth() == w)) {
			throw new PostconditionError("Init screen, initialisation incorrect de wdt");
		}
		
		//post: forall (x,y) in [O, getWidth()[ x [0, getHeight()[, 
		// 			cellNature(x,y) == Cell.EMP
		
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				if(! (cellNature(x, y) == Cell.EMP)) {
					throw new PostconditionError("Init screen, cellule non vide (EMP)");
				}
			}		
	}

	@Override
	public void dig(int x, int y) {
		 //pre: (0 <= y && y < getHeight()) 
		 //       && (0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth())) {
			throw new PreconditionError("Dig en dehors de la map");
		}
		
		 //pre: cellNature(x,y) == Cell.PLT
		
		if(! (cellNature(x, y) == Cell.PLT)) {
			throw new PreconditionError("Dig appelable seulement sur une plateforme (PLT)");
		}
		
		
		Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++) {
				cells_atPre[i][j] = cellNature(i, j);
			}
		
		super.dig(x, y);
		
		//post: cellNature(x,y) == Cell.HOL
		
		if(! (cellNature(x, y) == Cell.HOL)) {
			throw new PostconditionError("Dig n'a pas modifie la cellule correctement");
		}
		
		//post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[, 
		// 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre
		
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++) {
				if(i != x || j != y) {
					if(! (cellNature(i, j) == cells_atPre[i][j])) {
						throw new PostconditionError("Dig a modifie une cellule autre que la cible");
					}
				}
				
			}
	}

	@Override
	public void fill(int x, int y) {
		
		// \pre: (0 <= y && y < getHeight()) 
		//       && (0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth())) {
			throw new PreconditionError("Fill en dehors de la map");
		}
		
		// \pre: cellNature(x,y) == Cell.HOL
		
		if(! (cellNature(x, y) == Cell.HOL)) {
			throw new PreconditionError("Fill appelable seulement sur un trou (HOL)");
		}
		
		Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++) {
				cells_atPre[i][j] = cellNature(i, j);
			}
		
		super.fill(x, y);
		
		// \post: cellNature(x,y) == Cell.PLT
		
		if(! (cellNature(x, y) == Cell.HOL)) {
			throw new PostconditionError("Fill n'a pas modifie la cellule correctement");
		}
		
		// \post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[, 
		// 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre
		
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++) {
				if(i != x || j != y) {
					if(! (cellNature(i, j) == cells_atPre[i][j])) {
						throw new PostconditionError("Fill a modifie une cellule autre que la cible");
					}
				}
				
			}
	}
}
