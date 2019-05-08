package contrat;

import java.util.Set;

import Decorator.EnvironmentDecorator;
import itf.Cell;
import itf.CharacterService;
import itf.Content;
import itf.EnvironmentService;
import itf.Item;
import itf.ItemType;

public class EnvironmentContrat extends EnvironmentDecorator{
	
	public EnvironmentContrat(EnvironmentService service) {
		super(service);
	}

	public void checkInvariant() {
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
		// 		 exist Item i in cellContent(x,y) => (cellNature(x,y) in {Cell.EMP}
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Set<Content> contents = cellContent(x, y);
				for(Content c : contents) {
					if(c.isItem()) {
						// condition
						if( !( cellNature(x, y) == Cell.EMP ) )
							throw new InvariantError("Inv : Item not in Cell.EMP");
					}
				}
			}
		
	//	 \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
	// exist Item i and Item g in cellContent(x,y) => i == g
		int cpt=0;
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Set<Content> contents = cellContent(x, y);
				cpt=0;
				for(Content c : contents) {
					if(c.isItem()) {
						cpt++;
					}
				}
				
				//condition
				if( !( cpt <= 1 )) {
					throw new InvariantError("Inv : 2 or more items in one cell");
				}
			}
		
       // \inv: forall (x,y) in [O, getWidth()[ x [0, getHeight()[,
       // 				exist Treasure t in cellContent(x,y)
       // 					=> (cellNature(x,y) in {Cell.EMP, Cell.HDR, Cell.LAD} &&
       // 						cellNature(x,y-1) in {Cell.PLT, Cell.MTL, Cell.LAD})
		for(int x = 0; x < getWidth(); x++)
			for(int y = 0; y < getHeight(); y++) {
				Set<Content> contents = cellContent(x, y);
				cpt=0;
				for(Content c : contents) {
					if(c.isItem() && c.getItem().getItemType() == ItemType.Treasure) {
						//condition
						if( !( (cellNature(x, y) == Cell.EMP || cellNature(x, y) == Cell.HDR || cellNature(x, y) == Cell.LAD)  )
								&& (cellNature(x, y-1) == Cell.PLT || cellNature(x, y-1) == Cell.MTL || cellNature(x, y-1) == Cell.LAD)  )
							throw new InvariantError("Inv : Treasure not in {Cell.EMP, Cell.HDR, Cell.LAD} or not above of {Cell.PLT, Cell.MTL, Cell.LAD}");
						break;
					}
				}
				
				
			}
		
	}

	@Override
	public void init(int h, int w) {
		
		//pre: 0 < h && 0 < w
		
		if(! ( 0 < h && 0 < w )) {
			throw new PreconditionError("Init EnvironmentContrat avec taille(s) negative(s)");
		}
		
		//checkInvariant();
		
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
	
	@Override
	public void getOut(CharacterService c, int x, int y) {
		// \pre: cellContent(x,y) contains c
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isCharacter() && cont.getCharacter() == c ) {
				contain = true;
				break;
			}
		}

		//condition
		if( !( contain ) )
			throw new PreconditionError("GetOut: cell ("+x+","+y+")  does not contain character : "+c);
		
		checkInvariant();
		
		super.getOut(c, x, y);

		checkInvariant();
		
		// \post: cellContent(x,y) contains not c
		contents = cellContent(x, y);
		contain = false;
		for(Content cont : contents) {
			if( cont.isCharacter() && cont.getCharacter() == c ) {
				contain = true;
				break;
			}
		}
		
		//condition
		if( !( !contain ) )
			throw new PostconditionError("GetOut: cell ("+x+","+y+") contain character : "+c);
	
	}

	@Override
	public void getIn(CharacterService c, int x, int y) {
		
		// \pre: cellContent(x,y) not contains c
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isCharacter() && cont.getCharacter() == c ) {
				contain = true;
				break;
			}
		}

		//condition
		if( !( !contain ) )
			throw new PreconditionError("GetIn: cell ("+x+","+y+") contain character : "+c);
				
				
		checkInvariant();
		
		super.getIn(c, x, y);
		
		checkInvariant();
		
		// \post: cellContent(x,y) contains not c
		contents = cellContent(x, y);
		contain = false;
		for(Content cont : contents) {
			if( cont.isCharacter() && cont.getCharacter() == c ) {
				contain = true;
				break;
			}
		}
		
		//condition
		if( !( contain ) )
			throw new PostconditionError("GetIn: cell ("+x+","+y+") does not contain character : "+c);
		
		
	}
	
	@Override
	public void getOut(Item i, int x, int y) {
		// \pre: cellContent(x,y) contains i
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isItem() && cont.getItem() == i ) {
				contain = true;
				break;
			}
		}

		//condition
		if( !( contain ) )
			throw new PreconditionError("GetOut: cell ("+x+","+y+")  does not contain item: "+i);
		
		checkInvariant();
		
		super.getOut(i, x, y);

		checkInvariant();
		
		// \post: cellContent(x,y) contains not i
		contents = cellContent(x, y);
		contain = false;
		for(Content cont : contents) {
			if( cont.isItem() && cont.getItem() == i ) {
				contain = true;
				break;
			}
		}
		
		//condition
		if( !( !contain ) )
			throw new PostconditionError("GetOut: cell ("+x+","+y+") contain item : "+i);
	
	}

	@Override
	public void getIn(Item i, int x, int y) {
		
		// \pre: cellContent(x,y) not contains i
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isItem() && cont.getItem() == i ) {
				contain = true;
				break;
			}
		}

		//condition
		if( !( !contain ) )
			throw new PreconditionError("GetIn: cell ("+x+","+y+") contain item : "+i);
				
				
		checkInvariant();
		
		super.getIn(i, x, y);
		
		checkInvariant();
		
		// \post: cellContent(x,y) contains not i
		contents = cellContent(x, y);
		contain = false;
		for(Content cont : contents) {
			if( cont.isItem() && cont.getItem() == i ) {
				contain = true;
				break;
			}
		}
		
		//condition
		if( !( contain ) )
			throw new PostconditionError("GetIn: cell ("+x+","+y+") does not contain item : "+i);
		
		
	}
	
	@Override
	public boolean hasItem(int x, int y) {
		checkInvariant();
		
		boolean res = super.hasItem(x, y);
		
		checkInvariant();
		
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isItem()) {
				contain = true;
				break;
			}
		}
		
		// \post: exist Item I in getEnvi().cellContent(x,y) => true
		if( !( res!=true || contain == true ) )
			throw new PostconditionError("HasItem: not expected value");
		
			
		// \post: not exist Item I in getEnvi().cellContent(x,y) => false
		if( !( res != false || contain == false ) )
			throw new PostconditionError("HasItem: not expected value");

		return contain;
	}
	
	@Override
	public Item getItem(int x, int y) {
		// \pre: exist Item I in getEnvi().cellContent(x,y)
		Set<Content> contents = cellContent(x, y);
		boolean contain = false;
		for(Content cont : contents) {
			if( cont.isItem() ) {
				contain = true;
				break;
			}
		}
		
		//condition
		if( !( contain ))
			throw new PreconditionError("GetItem: called on cell not containing item");
		
		
		checkInvariant();
		
		Item i = super.getItem(x, y);
		 
		checkInvariant();
		 
		return i;
	}
	
	
}
