package Decorator;

import itf.EngineService;
import itf.EnvironmentService;
import itf.Item;
import itf.PlayerService;

public class PlayerDecorator extends CharacterDecorator implements PlayerService{
	private PlayerService delegate;

	protected PlayerDecorator(PlayerService delegate) {
		super(delegate);
		this.delegate = delegate;
	}	

	@Override
	public void init(int w, int h, EnvironmentService envS, EngineService engS) {
		delegate.init(w, h, envS, engS);
	}
	
	@Override
	public EngineService getEngine() {
		return delegate.getEngine();
	}

	@Override
	public void step() {
		delegate.step();
	}

	@Override
	public int getNbTreasure() {
		return delegate.getNbTreasure();
	}

	public void die() {
		delegate.die();
	}

	public void pickUpTreasure() {
		delegate.pickUpTreasure();
	}
	
	public int getHP() {
		return delegate.getHP();
	}

	public boolean hasKey() {
		return delegate.hasKey();
	}

	public void pickUpKey(Item k) {
		delegate.pickUpKey(k);
	}

	public Item getKey() {
		return delegate.getKey();
	}
	
	public void resetNbTreasure() {
		delegate.resetNbTreasure();
	}

	public void resetKey() {
		delegate.resetKey();
	}

	public void resetHP() {
		delegate.resetHP();
	}
}
