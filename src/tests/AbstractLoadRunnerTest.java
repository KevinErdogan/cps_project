package tests;

import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

import org.junit.After;
import org.junit.Before;

public abstract class AbstractLoadRunnerTest {
	private EngineService engine;
	private EnvironmentService envi;
	private PlayerService player;
	//private Set<GuardService> guards;
	
	protected AbstractLoadRunnerTest() {
		engine = null;
		envi = null;
		player = null;
		//guards = null;
	}

	public final EngineService getEngine() {
		return engine;
	}

	public final void setEngine(EngineService engine) {
		this.engine = engine;
	}

	public final EnvironmentService getEnvi() {
		return envi;
	}

	public final void setEnvi(EnvironmentService envi) {
		this.envi = envi;
	}

	public final PlayerService getPlayer() {
		return player;
	}

	public final void setPlayer(PlayerService player) {
		this.player = player;
	}
	
	@Before
	public abstract void beforeTests(); 

	@After
	public final void afterTests() {
		engine = null;
		envi = null;
		player = null;
		//guards = null;
	}
	
	
}
