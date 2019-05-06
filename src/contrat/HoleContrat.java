package contrat;

import Decorator.HoleDecorator;
import itf.HoleService;

public class HoleContrat extends HoleDecorator{

	protected HoleContrat(HoleService service) {
		super(service);
	}
	
	public void checkInvariant() {
		int time = super.getTime();
		if(! (time >= 0)) {
			throw new InvariantError("Hole, time negatif");
		}
		
		if(! (time <= 15)) {
			throw new InvariantError("Hole, time superieur a 15");
		}
	}
	
	public int getX() {
		
		checkInvariant();
		
		int res =  super.getX();
		
		checkInvariant();
		
		return res;
	}

	public int getY() {
		
		checkInvariant();
		
		int res =  super.getY();
		
		checkInvariant();
		
		return res;
	}

	public int getTime() {
		
		checkInvariant();
		
		int res =  super.getTime();
		
		checkInvariant();
		
		return res;
	}

	public void init(int x, int y) {
		//pre: x > 0
		if(! (x > 0)) {
			throw new PreconditionError("init Hole, x doit etre strictement positif");
		}
		
		//pre: y > 0
		if(! (y > 0)) {
			throw new PreconditionError("init Hole, y doit etre strictement positif");
		}
		
		super.init(x, y);
		
		checkInvariant();
		
		//post: getX() == x
		if(! (super.getX() == x)) {
			throw new PostconditionError("Init Hole, x n'est pas initialise correctement");
		}
		
		//post: getY() == y
		if(! (super.getY() == y)) {
			throw new PostconditionError("Init Hole, y n'est pas initialise correctement");
		}
		
		//post: getTime() == 0
		if(! (super.getTime() == 0)) {
			throw new PostconditionError("Init Hole, time n'est pas initialise a 0");
		}
	}

	public void step() {
		
		int time_at_Pre = super.getTime();
		int x_at_Pre = super.getX();
		int y_at_Pre = super.getY();
		
		checkInvariant();
		
		super.step();
		
		checkInvariant();
		
		//post: getTime() == getTime()@Pre+1
		if(! (super.getTime() == time_at_Pre+1)) {
			throw new PostconditionError("Step Hole, time n'est pas incremente");
		}
		
		//post: getX() == getX()@Pre
		if(! (super.getX() == x_at_Pre)) {
			throw new PostconditionError("Step Hole, x est modifie");
		}
		
		//post: getY() == getY()@Pre
		if(! (super.getY() == y_at_Pre)) {
			throw new PostconditionError("Step Hole, y est modifie");
		}
	}
	
}
