package tests;

import org.junit.Assert;
import org.junit.Test;

import impl.EngineImpl;
import itf.Cell;
import itf.Command;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

public class TestImpl extends AbstractLoadRunnerTest{

	@Override
	public void beforeTests() {
		EngineService engine = new EngineImpl();
		engine.initWithTxt("map1.txt");
		setPlayer(engine.getPlayer());
		setEnvi(engine.getEnvi());
		setEngine(engine);
		engine.setDisplayOn(false);
	}
	
	@Test
	public void testMoveWhenFalling(){//positif
		//operation
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		for(int i = 0; i < 12; i++) {
			engine.addCommand(Command.Right);
			engine.step();
		}
		engine.addCommand(Command.Up);
		engine.step();
		engine.addCommand(Command.Up);
		engine.step();
		for(int i = 0; i < 12; i++) {
			engine.addCommand(Command.Left);
			engine.step();
		}
		engine.addCommand(Command.Down);
		engine.step();
		//ici le joueur est dans une case vide (HOL) et se trouve au dessus d'une case vide
		int x = player.getWdt();
		int y = player.getHgt();
		
		engine.addCommand(Command.Left);
		engine.step();//le jouer doit tomber au lieu d'aller a gauche
		
		//oracle
		//Pas d'exception a lever
		Assert.assertTrue("testMoveWhenFalling failed, le joueur n'est pas tombe", player.getWdt() == x && player.getHgt() == y-1);
	}
	
	@Test
	public void testDigWhenOnHandRail(){//positif
		//operation
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		EnvironmentService env = getEnvi();
		for(int i = 0; i < 12; i++) {
			engine.addCommand(Command.Right);
			engine.step();
		}
		engine.addCommand(Command.Up);
		engine.step();
		engine.addCommand(Command.Up);
		engine.step();
		for(int i = 0; i < 16; i++) {
			engine.addCommand(Command.Left);
			engine.step();
		}
		//ici le joueur est dans sur une rail (HDR) et en bas a gauche il y a une plateforme (PLT)
		
		engine.addCommand(Command.DigL);
		engine.step();//le jouer ne doit pas pouvoir creuser
		
		//oracle
		//Pas d'exception a lever
		Assert.assertTrue("testDigWhenOnHandRail failed, le joueur a reussi a creuser", 
				env.cellNature(player.getWdt()-1, player.getHgt()-1) == Cell.PLT);
	}
	
	@Test
	public void testFallWhenInHOL(){//positif
		//operation
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		EnvironmentService env = getEnvi();
		for(int i = 0; i < 12; i++) {
			engine.addCommand(Command.Right);
			engine.step();
		}
		engine.addCommand(Command.Up);
		engine.step();
		engine.addCommand(Command.Up);
		engine.step();

		engine.addCommand(Command.Left);
		engine.step();
		
		engine.addCommand(Command.DigL);//creuse a gauche
		engine.step();
		
		
		engine.addCommand(Command.Left);//se deplace a gauche et se trouve au dessus du trou
		engine.step();
		
		
		
		engine.addCommand(Command.Left);//ignore Left et tombe dans le trou
		engine.step();
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		engine.addCommand(Command.Left);//tombe en dessous du trou
		engine.step();
		
		//oracle
		//Pas d'exception a lever
		Assert.assertTrue("testFallWhenInHOL failed, le joueur n'etait pas dans un trou", 
				env.cellNature(x, y) == Cell.HOL);
		
		Assert.assertTrue("testFallWhenInHOL failed, le joueur n'est pas tombe", 
				player.getWdt() == x && player.getHgt() == y-1);
	}
	
}
