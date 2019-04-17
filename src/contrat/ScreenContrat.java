package contrat;

import Decorator.ScreenDecorator;
import itf.Cell;
import itf.ScreenService;

public class ScreenContrat extends ScreenDecorator{

	protected ScreenContrat(ScreenService service) {
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
		 //pre: cellNature(x,y) == Cell.PLT
		
		super.dig(x, y);

		//post: cellNature(x,y) == Cell.HOL
		//post: forall (i,j) in [O, getWidth()[ x [0, getHeight()[,
		// 			i != x || j != y => cellNature(i,j) == cellNature(i,j)@Pre

	}

	@Override
	public void fill(int x, int y) {

		super.fill(x, y);
	}



}
