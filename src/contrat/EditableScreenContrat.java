package contrat;

import Decorator.EditableScreenDecorator;
import itf.Cell;
import itf.EditableScreenService;

public class EditableScreenContrat extends EditableScreenDecorator{
	
	protected EditableScreenContrat(EditableScreenService service) {
		super(service);
	}

	public void checkInvariant() {
		
		 //inv: isPlayable() <=> forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
		 // 		cellNature(x,y) != Cell.HOL
		 // 		&& forall x in [O, getWidth()[,
		 // 		cellNature(x,0) == Cell.MTL
		
		boolean isPlayable = true;
		
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				if(! (cellNature(x, y) != Cell.HOL)) {
					isPlayable = false;
				}
			}
		
		for(int x = 0; x < getWidth(); x++) {
			if(! (cellNature(x, 0) == Cell.MTL)) {
				isPlayable = false;
			}
		}
		
		if(! (super.isPlayable() == isPlayable)) {
			throw new InvariantError("EditableScreen isPlayable");
		}
		
		
	}

	@Override
	public void init(int h, int w) {
		//pre: 0 < h && 0 < w
		
		if(! ( 0 < h && 0 < w )) {
			throw new PreconditionError("Init Editablescreen avec taille(s) negative(s)");
		}
		
		checkInvariant();
		
		super.init(h, w);
		
		checkInvariant();
		
		
	}

	@Override
	public void setNature(int x, int y, Cell c) {
	
		 //pre: (0 <= y && y < getHeight())
		 //		&& ( 0 <= x && x < getWidth())
		
		 if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth()) ) {
			throw new PreconditionError("setNature sur une case en dehors des limites de la map");
		 }
		
		 Cell[][] cells_atPre = new Cell[getWidth()][getHeight()];
			for(int i = 0; i < getWidth(); i++)
				for(int j = 0; j < getHeight(); j++) {
					cells_atPre[i][j] = cellNature(i, j);
				}
		 
		checkInvariant();
		
		super.setNature(x, y, c);
		
		checkInvariant();
		
		//post: cellNature(x,y) = c
		
		if(! (cellNature(x, y) == c)) {
			throw new PostconditionError("cellNature ne modifie pas correctement la cible");
		}
		
		//post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[,
		// 			i != x || j != y => cellNature(i,j) = cellNature(i,j)@Pre
		
	
		for(int i = 0; i < getWidth(); i++)
			for(int j = 0; j < getHeight(); j++) {
				if(i != x || j != y) {
					if(! (cellNature(i, j) == cells_atPre[i][j])) {
						throw new PostconditionError("setNature a modifie une cellule autre que la cible");
					}
				}
				
			}
		
	}
	
	
	
}
