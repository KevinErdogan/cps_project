package impl;

import java.util.Set;

import display.Display;
import itf.CharacterService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.Move;

public class EngineImpl implements EngineService{
	private EnvironmentService es;
	private CharacterService player;
	private Set<CharacterService> guards;
	private Display display;

	public EngineImpl(EnvironmentService es, CharacterService player, Set<CharacterService> guards) {
		this.es=es;
		this.player=player;
		this.guards=guards;
	}

	@Override
	public EnvironmentService getEnvi() {
		return es;
	}

	@Override
	public CharacterService getPlayer() {
		return player;
	}

	@Override
	public Set<CharacterService> getGuards() {
		return guards;
	}

	@Override
	public Display getStatus() {
		return display;
	}

	@Override
	public Move getNextCommand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void step() {
		player.step();
		for(CharacterService guard : guards) {
			guard.step();
		}

		display.step();
	}

	@Override
	public void init() {
		display = new Display(this);

	}

}
