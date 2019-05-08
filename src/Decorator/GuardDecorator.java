package Decorator;

import itf.CharacterService;
import itf.EnvironmentService;
import itf.GuardService;
import itf.Item;
import itf.Move;

public class GuardDecorator extends CharacterDecorator implements GuardService{
	private GuardService delegate;
	
	protected GuardDecorator(GuardService delegate) {
		super(delegate);
		this.delegate = delegate;
	}

	public GuardService getDelegate() {
		return delegate;
	}

	public int getId() {
		return delegate.getId();
	}

	public CharacterService getTarget() {
		return delegate.getTarget();
	}

	public int getTimeInHole() {
		return delegate.getTimeInHole();
	}

	public void init(EnvironmentService screen, int x, int y, CharacterService target, int id) {
		delegate.init(screen, x, y, target, id);
	}

	public Move getBehavior() {
		return delegate.getBehavior();
	}

	public void climbLeft() {
		delegate.climbLeft();
	}

	public void climbRight() {
		delegate.climbRight();
	}

	public void step() {
		delegate.step();
	}

	@Override
	public boolean hasItem() {
		return delegate.hasItem();
	}

	public void pickUpItem(Item t) {
		delegate.pickUpItem(t);
	}

	public void dropItem() {
		delegate.dropItem();
	}

	public Item getItem() {
		return delegate.getItem();
	}
}
