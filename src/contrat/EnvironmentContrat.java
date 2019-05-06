package contrat;

import java.util.ArrayList;

import Decorator.EnvironmentDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EditableScreenService;
import itf.EnvironmentService;
import itf.Item;
import itf.Item.ItemType;

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
				Content content = cellContent(x, y);
				if(content.isCharacter()) {
					ArrayList<CharacterService> characters = content.getCharacters();
					if(characters.size() >= 2)	
						throw new InvariantError(characters.size()+" Characters presents sur la meme cellule");
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

		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Content content = cellContent(x,y);
				ArrayList<Item> items = content.getItems();
				for(Item i : items) {
					if(i.getNature() == ItemType.Treasure) {
						if( !( cellNature(x,y)== Cell.EMP && ( cellNature(x, y-1) == Cell.PLT || cellNature(x, y-1) == Cell.MTL ) ) ) {
							throw new InvariantError("Tresor present dans une case non vide ou au-dessus d'une case differente de PLT ou MTL.");
						}
					}
				}
			}
	}

	@Override
	public void init(EditableScreenService e) {
		
		checkInvariant();
		
		super.init(e.getHeight(), e.getWidth());
		
		checkInvariant();
		
		/*
		 * \post: \forall (x,y) in [0, getWidth()[ x [0, getHeight()[
		 * 		  cellNature(x,y) = e.cellNature(x,y)
		 */
		
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				if( !( cellNature(x,y) == e.cellNature(x, y) ) ) {
					throw new PostconditionError("EnvrionmentContrat.init : la nature de la case en ("+x+","+y+") est : "+cellNature(x,y)+" alors qu'elle devrait être : "+e.cellNature(x, y));
				}
			}
		
	}

	@Override
	public Content cellContent(int x, int y) {
		
		//pre: (0 <= y && y < getHeight())
		// 		&& ( 0 <= x && x < getWidth())
		
		if(! (0 <= y && y < getHeight()
				&& 0 <= x && x < getWidth()) ) {
			throw new PreconditionError("cellContent sur une case en dehors des limites de la map");
		 }
		
		checkInvariant();
		
		Content res = super.cellContent(x, y);
		
		checkInvariant();
		
		return res;
	}
	
	
}
