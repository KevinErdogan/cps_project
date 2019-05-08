package tests;

import org.junit.Assert;
import org.junit.Test;

import contrat.EditableScreenContrat;
import contrat.EngineContrat;
import contrat.GuardContrat;
import contrat.PlayerContrat;
import contrat.ScreenContrat;
import impl.EditableScreenImpl;
import impl.EngineImpl;
import impl.GuardImpl;
import impl.HoleImpl;
import impl.ItemImpl;
import impl.PlayerImpl;
import impl.ScreenImpl;
import itf.Cell;
import itf.EditableScreenService;
import itf.EngineService;
import itf.GuardService;
import itf.HoleService;
import itf.Item;
import itf.ItemType;
import itf.Move;
import itf.PlayerService;
import itf.ScreenService;

public class TestImpl extends AbstractLoadRunnerTest{

	@Override
	public void beforeTests() {
		EngineService engine = new EngineImpl();
		engine.setDisplayOn(false);
		engine.initFirstMap();
		setPlayer(engine.getPlayer());
		setEnvi(engine.getEnvi());
		setEngine(engine);
		
	}
	
	//////////Test GuardImpl avec GuardContrat////////////////
	
	
	@Test
	public void testInitGuardPre() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		
		Assert.assertTrue("testInitGuardPre1 failed", engine.getEnvi().cellNature(2, 2) == Cell.EMP);
	}
	
	@Test
	public void testInitGuardPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		
		Assert.assertTrue("testInitGuardPre1 failed", guard.getWdt() == 2);
		Assert.assertTrue("testInitGuardPre1 failed", guard.getHgt() == 2);
		Assert.assertTrue("testInitGuardPre1 failed", guard.getTarget() == player);
		Assert.assertTrue("testInitGuardPre1 failed", guard.getId() == 5);
		Assert.assertTrue("testInitGuardPre1 failed", guard.getTimeInHole() == 0);
	}
	
	
	@Test
	public void testClimLeftGuardPre() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		
		engine.getEnvi().dig(3, 1);
		guardContrat.goRight();
		guardContrat.step();
		Assert.assertTrue("testClimLeftGuardPre failed", engine.getEnvi().cellNature(guard.getWdt(), guard.getHgt()) == Cell.HOL);
		
		guardContrat.climbLeft();
	}
	
	
	@Test
	public void testClimLeftGuardPost1() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		engine.getEnvi().dig(3, 1);
		guardContrat.goRight();
		guardContrat.step();
		guardContrat.step();//pour mettre le timeInHole a 1
		Assert.assertTrue("testClimLeftGuardPost1 failed", engine.getEnvi().cellNature(guard.getWdt(), guard.getHgt()) == Cell.HOL);
		guardContrat.climbLeft();
		
		Assert.assertTrue("testClimLeftGuardPost1 failed", guard.getWdt() == 2);
		Assert.assertTrue("testClimLeftGuardPost1 failed", guard.getHgt() == 2);
		Assert.assertTrue("testClimLeftGuardPost1 failed", guard.getTimeInHole() == 0);
	}
	
	
	
	
	@Test
	public void testClimRightGuardPre() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		
		engine.getEnvi().dig(1, 1);
		guardContrat.goLeft();
		guardContrat.step();
		Assert.assertTrue("testClimRightGuardPre failed", engine.getEnvi().cellNature(guard.getWdt(), guard.getHgt()) == Cell.HOL);
		
		guardContrat.climbRight();
	}
	
	
	@Test
	public void testClimRightGuardPost1() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		engine.getEnvi().dig(1, 1);
		guardContrat.goLeft();
		guardContrat.step();
		guardContrat.step();//pour mettre le timeInHole a 1
		Assert.assertTrue("testClimRightGuardPost1 failed", engine.getEnvi().cellNature(guard.getWdt(), guard.getHgt()) == Cell.HOL);
		guardContrat.climbRight();
		
		Assert.assertTrue("testClimRightGuardPost1 failed", guard.getWdt() == 2);
		Assert.assertTrue("testClimRightGuardPost1 failed", guard.getHgt() == 2);
		Assert.assertTrue("testClimRightGuardPost1 failed", guard.getTimeInHole() == 0);
	}
	
	
	@Test
	public void testResetGuardPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
	
		guardContrat.goLeft();

		Assert.assertTrue("testResetGuardPost failed", guard.getWdt() != guard.getInitialWdt());
		
		guardContrat.reset();
		
		Assert.assertTrue("testResetGuardPost failed", guard.getWdt() == guard.getInitialWdt());
		Assert.assertTrue("testResetGuardPost failed", guard.getHgt() == guard.getInitialHgt());
		Assert.assertTrue("testResetGuardPost failed", guard.getTimeInHole() == 0);
		Assert.assertTrue("testResetGuardPost failed", guard.hasItem() == false);
	}
	
	@Test
	public void testDieGuardPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		guardContrat.goLeft();
		
		Assert.assertTrue("testDieGuardPost failed", guard.getWdt() != guard.getInitialWdt());
		
		guardContrat.die();
		
		Assert.assertTrue("testDieGuardPost failed", guard.getWdt() == guard.getInitialWdt());
		Assert.assertTrue("testDieGuardPost failed", guard.getHgt() == guard.getInitialHgt());
		Assert.assertTrue("testDieGuardPost failed", guard.getTimeInHole() == 0);
		Assert.assertTrue("testDieGuardPost failed", guard.hasItem() == false);
	}
	
	
	@Test
	public void testPickUpGuardPre() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
	
		Item t = new ItemImpl(2, 2, ItemType.Treasure, true);
		
		Assert.assertTrue("testPickUpGuardPre failed", guard.hasItem() == false);
		
		guardContrat.pickUpItem(t);
		
	}
	
	@Test
	public void testPickUpGuardPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
	
		Item t = new ItemImpl(2, 2, ItemType.Treasure, true);
			
		guardContrat.pickUpItem(t);
		
		Assert.assertTrue("testPickUpGuardPost failed", guard.hasItem() == true);
		Assert.assertTrue("testPickUpGuardPost failed", guard.getItem() == t);
		
	}
	
	
	@Test
	public void testDropGuardPre() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
	
		Item t = new ItemImpl(2, 2, ItemType.Treasure, true);
		guardContrat.pickUpItem(t);
		
		Assert.assertTrue("testDropGuardPre failed", guard.hasItem() == true);
		
		guardContrat.dropItem();
		
	}
	
	@Test
	public void testDropGuardPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
	
		Item t = new ItemImpl(2, 2, ItemType.Treasure, true);
		guardContrat.pickUpItem(t);
		
		guardContrat.dropItem();
		
		Assert.assertTrue("testDropGuardPost failed", guard.hasItem() == false);
	}	
	
	@Test
	public void testStepFallGuard() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);
		
		engine.getEnvi().dig(1, 1);
		guardContrat.goLeft();
		
		int x = guard.getWdt();
		int y = guard.getHgt();
		
		guardContrat.step();//le garde tombe dans le trou en (1,1)
		
		Assert.assertTrue("testStepFallGuard failed", guard.getWdt() == x);
		Assert.assertTrue("testStepFallGuard failed", guard.getHgt() == y-1);
		Assert.assertTrue("testStepFallGuard failed", guard.getWdt() == 1);
		Assert.assertTrue("testStepFallGuard failed", guard.getHgt() == 1);
	}	
	
	@Test
	public void testStepRightGuard() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		guardContrat.init(engine.getEnvi(), 2, 2, player, 5);//garde a gauche du player
		
		int x = guard.getWdt();
		int y = guard.getHgt();
		
		guardContrat.step();//le garde se deplace a droite
		
		Assert.assertTrue("testStepRightGuard failed", guard.getWdt() == x+1);
		Assert.assertTrue("testStepRightGuard failed", guard.getHgt() == y);
	}	
	
	
	@Test
	public void testStepLeftGuard() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		guardContrat.init(engine.getEnvi(), 22, 2, player, 5);//garde a droite du player
		
		int x = guard.getWdt();
		int y = guard.getHgt();
		
		guardContrat.step();//le garde se deplace a gauche
		
		Assert.assertTrue("testStepLeftGuard failed", guard.getWdt() == x-1);
		Assert.assertTrue("testStepLeftGuard failed", guard.getHgt() == y);
	}
	
	@Test
	public void testStepDownGuard() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		guardContrat.init(engine.getEnvi(), 15, 13, player, 5);//garde au dessus du player
		
		int x = guard.getWdt();
		int y = guard.getHgt();
		
		guardContrat.step();//le garde se deplace en bas
		
		Assert.assertTrue("testStepDownGuard failed", guard.getWdt() == x);
		Assert.assertTrue("testStepDownGuard failed", guard.getHgt() == y-1);
	}
	
	@Test
	public void testStepUpGuard() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		GuardService guard = new GuardImpl();
		GuardContrat guardContrat = new GuardContrat(guard);
		
		while(player.getWdt() != 4)
			player.goLeft();
		
		player.goUp();
		player.goUp();// player en (4,4)
		
		guardContrat.init(engine.getEnvi(), 4, 2, player, 5);//garde en dessous du player
		
		int x = guard.getWdt();
		int y = guard.getHgt();
		
		guardContrat.step();//le garde se deplace vers le haut
		
		Assert.assertTrue("testStepUpGuard failed", guard.getWdt() == x);
		Assert.assertTrue("testStepUpGuard failed", guard.getHgt() == y+1);
	}
	
//////////Test PlayerImpl avec PlayerContrat////////////////
	
	@Test
	public void testInitPlayerPost() {
		EngineService engine = getEngine();
		PlayerService playerTest = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(playerTest);
		
		playerContrat.init(2, 2, engine.getEnvi(), engine);
		
		Assert.assertTrue("testInitPlayerPost failed", playerTest.getEngine() == engine);
		Assert.assertTrue("testInitPlayerPost failed", playerTest.getNbTreasure() == 0);
		Assert.assertTrue("testInitPlayerPost failed", playerTest.getHP() == 3);
	}
	
	@Test
	public void testPickUpTreasurePlayerPost() {
		EngineService engine = getEngine();
		PlayerService playerTest = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(playerTest);
		
		playerContrat.init(2, 2, engine.getEnvi(), engine);
			
		playerContrat.pickUpTreasure();
		
		Assert.assertTrue("testPickUpTreasurePlayerPost failed", playerTest.getNbTreasure() == 1);
	}	
	
	
	@Test
	public void testPickUpKeyPlayerPre() {
		EngineService engine = getEngine();
		PlayerService playerTest = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(playerTest);
		
		playerContrat.init(2, 2, engine.getEnvi(), engine);
			
		Item key = new ItemImpl(2, 2, ItemType.Key, true);
		
		Assert.assertTrue("testPickUpKeyPlayerPre failed", playerTest.hasKey() == false);
		
		playerContrat.pickUpKey(key);
	}
	
	
	@Test
	public void testPickUpKeyPlayerPost() {
		EngineService engine = getEngine();
		PlayerService playerTest = new PlayerImpl();
		PlayerContrat playerContrat = new PlayerContrat(playerTest);
		
		playerContrat.init(2, 2, engine.getEnvi(), engine);
			
		Item key = new ItemImpl(2, 2, ItemType.Key, true);
		
		playerContrat.pickUpKey(key);
		
		Assert.assertTrue("testPickUpKeyPlayerPost failed", playerTest.hasKey() == true);
		Assert.assertTrue("testPickUpKeyPlayerPost failed", playerTest.getKey() == key);
	}
	
	
	@Test
	public void testDiePlayerPost() {
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
			
		playerContrat.die();
		
		Assert.assertTrue("testDiePlayerPost failed", player.getHP() == 2);
		Assert.assertTrue("testDiePlayerPost failed", player.getNbTreasure() == 0);
		Assert.assertTrue("testDiePlayerPost failed", player.hasKey() == false);

	}
	
	@Test
	public void testDiePlayerWithKey() {//test avec la cle en possession du joueur
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
			
		Item key = new ItemImpl(2, 2, ItemType.Key, true);
		player.pickUpKey(key);
		
		Assert.assertTrue("testDiePlayerWithKey failed", player.hasKey() == true);
		
		playerContrat.die();
		
		Assert.assertTrue("testDiePlayerWithKey failed", player.getHP() == 2);
		Assert.assertTrue("testDiePlayerWithKey failed", player.getNbTreasure() == 0);
		Assert.assertTrue("testDiePlayerWithKey failed", player.hasKey() == false);
	}

	
	@Test
	public void testDiePlayerWithTreasures() {//test avec tresors possession du joueur
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
			
		player.pickUpTreasure();
		player.pickUpTreasure();
		
		Assert.assertTrue("testDiePlayerWithTreasures failed", player.getNbTreasure() == 2);
		
		playerContrat.die();
		
		Assert.assertTrue("testDiePlayerWithTreasures failed", player.getHP() == 2);
		Assert.assertTrue("testDiePlayerWithTreasures failed", player.getNbTreasure() == 0);
		Assert.assertTrue("testDiePlayerWithTreasures failed", player.hasKey() == false);
	}	
	
	
	@Test
	public void testResetNbTreasurePost() {
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
			
		player.pickUpTreasure();
		player.pickUpTreasure();
		
		Assert.assertTrue("testResetNbTreasurePost failed", player.getNbTreasure() == 2);
		
		playerContrat.resetNbTreasure();
		
		Assert.assertTrue("testResetNbTreasurePost failed", player.getNbTreasure() == 0);
	}	
	
	@Test
	public void testResetKeyPost() {
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
			
		Item key = new ItemImpl(2, 2, ItemType.Key, true);
		player.pickUpKey(key);
		
		Assert.assertTrue("testResetKeyPost failed", player.hasKey() == true);
		
		playerContrat.resetKey();
		
		Assert.assertTrue("testResetKeyPost failed", player.hasKey() == false);
	}	
	
	@Test
	public void testResetHpPost() {
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		player.die();
		
		Assert.assertTrue("testResetHpPost failed", player.getHP() == 2);
		
		playerContrat.resetHP();
		
		Assert.assertTrue("testResetHpPost failed", player.getHP() == 3);
	}	
	
	@Test
	public void testStepRightPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		engine.addCommand(Move.Right);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepRightPlayerPost failed", player.getWdt() == x+1);
		Assert.assertTrue("testStepRightPlayerPost failed", player.getHgt() == y);
	}
	
	@Test
	public void testStepLeftPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		engine.addCommand(Move.Left);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepLeftPlayerPost failed", player.getWdt() == x-1);
		Assert.assertTrue("testStepLeftPlayerPost failed", player.getHgt() == y);
	}
	
	@Test
	public void testStepChutePlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		engine.addCommand(Move.Left);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		engine.getEnvi().dig(x, y-1);
		
		playerContrat.step();
		
		Assert.assertTrue("testStepChutePlayerPost failed", player.getWdt() == x);
		Assert.assertTrue("testStepChutePlayerPost failed", player.getHgt() == y-1);
	}
	
	@Test
	public void testStepUpPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
		
		while(player.getWdt() != 4)
			player.goLeft();
		
		engine.addCommand(Move.Up);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepUpPlayerPost failed", player.getWdt() == x);
		Assert.assertTrue("testStepUpPlayerPost failed", player.getHgt() == y+1);
	}
	
	@Test
	public void testStepDownPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
		
		while(player.getWdt() != 4)
			player.goLeft();
		
		player.goUp();
		
		engine.addCommand(Move.Down);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepDownPlayerPost failed", player.getWdt() == x);
		Assert.assertTrue("testStepDownPlayerPost failed", player.getHgt() == y-1);
	}
	
	
	@Test
	public void testStepDigLPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		engine.addCommand(Move.DigL);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepDigLPlayerPost failed", engine.getEnvi().cellNature(x-1, y-1) == Cell.HOL);
	}
	
	@Test
	public void testStepDigRPlayerPost() {
		EngineService engine = getEngine();
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		engine.addCommand(Move.DigR);
		
		int x = player.getWdt();
		int y = player.getHgt();
		
		playerContrat.step();
		
		Assert.assertTrue("testStepDigRPlayerPost failed", engine.getEnvi().cellNature(x+1, y-1) == Cell.HOL);
	}
	
	
	@Test
	public void testInvariantNegPlayer() {
		PlayerService player = getPlayer();
		PlayerContrat playerContrat = new PlayerContrat(player);
				
		player.die();
		player.die();
		player.die();
		
		try {
			playerContrat.die();
			Assert.assertTrue(false);
		}catch(Error e) {
			Assert.assertTrue(true);
		}
	}
	
	
//////////Test ScreenImpl avec ScreenContrat////////////////
	
	
	@Test
	public void testInitScreenPreNegatif() {
		ScreenService screen = new ScreenImpl();
		ScreenContrat screenContrat = new ScreenContrat(screen);	
		
		try {
			screenContrat.init(-1, 20);
			Assert.assertTrue(false);
		}catch(Error e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInitScreenPrePositif() {
		ScreenService screen = new ScreenImpl();
		ScreenContrat screenContrat = new ScreenContrat(screen);	
		
		screenContrat.init(10, 20);
		
		Assert.assertTrue("testInitScreenPrePositif failed", screen.getWidth() == 10);
		Assert.assertTrue("testInitScreenPrePositif failed", screen.getHeight() == 20);
	}
	
	
	@Test
	public void testDigScreenPreNegatif() {
		ScreenService screen = new ScreenImpl();
		ScreenContrat screenContrat = new ScreenContrat(screen);	
		
		screen.init(10, 20);
		
		try {
			screenContrat.dig(5, 5);
			Assert.assertTrue(false);
		}catch(Error e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testFillScreenPreNegatif() {
		ScreenService screen = new ScreenImpl();
		ScreenContrat screenContrat = new ScreenContrat(screen);	
		
		screen.init(10, 20);
		
		try {
			screenContrat.fill(5, 5);
			Assert.assertTrue(false);
		}catch(Error e) {
			Assert.assertTrue(true);
		}
	}
	
//////////Test EditableScreenImpl avec EditableScreenContrat////////////////	
	
	@Test
	public void testSetNaturePost() {
		EditableScreenService screen = new EditableScreenImpl();
		EditableScreenContrat screenContrat = new EditableScreenContrat(screen);	
		
		screen.init(10, 20);
		
		screenContrat.setNature(5, 15, Cell.HDR);
		
		Assert.assertTrue("testSetNaturePost failed", screen.cellNature(5, 15) == Cell.HDR);
	}
	
	@Test
	public void testSetNaturePost2() {
		EditableScreenService screen = getEngine().getEnvi();
		EditableScreenContrat screenContrat = new EditableScreenContrat(screen);	
		
		screen.setNature(5, 15, Cell.PLT);
		
		screenContrat.setNature(5, 15, Cell.HDR);
		
		Assert.assertTrue("testSetNaturePost2 failed", screen.cellNature(5, 15) == Cell.HDR);
	}
	
	@Test
	public void testSetNaturePreNegatif() {
		EditableScreenService screen = new EditableScreenImpl();
		EditableScreenContrat screenContrat = new EditableScreenContrat(screen);	
		
		screen.init(10, 20);
		try {
			screenContrat.setNature(50, 150, Cell.HDR);
			Assert.assertTrue(false);
		}catch(Error e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testInvariantEditable() {
		EditableScreenService screen = new EditableScreenImpl();
		EditableScreenContrat screenContrat = new EditableScreenContrat(screen);	
		
		screen.init(10, 20);
		
		for(int i = 0; i < 10; i++) {
			screenContrat.setNature(i, 0, Cell.MTL);
		}
		Assert.assertTrue("testInvariantEditable failed", screen.isPlayable() == true);
	}
	
	
	

//////////Test EngineImpl avec EngineContrat////////////////	
	
	@Test
	public void testInitEnginePre() {
		EngineService eng = getEngine();
		EngineService engine = new EngineImpl();
		EngineContrat engineContrat = new EngineContrat(engine);
	
		engine.setDisplayOn(false);
		engineContrat.init(eng.getEnvi(), eng.getPlayer(), eng.getGuards(),eng.getTreasures(), eng.getKey());
		
		Assert.assertTrue("testInitEnginePre failed", engine.getEnvi() == eng.getEnvi());
		Assert.assertTrue("testInitEnginePre failed", engine.getPlayer() == eng.getPlayer());
		Assert.assertTrue("testInitEnginePre failed", engine.getGuards() == eng.getGuards());
		Assert.assertTrue("testInitEnginePre failed", engine.getTreasures() == eng.getTreasures());
		Assert.assertTrue("testInitEnginePre failed", engine.getKey() == eng.getKey());
		
	}
	
	@Test
	public void testaddCommandEnginePost() {
		EngineService engine = getEngine();
		EngineContrat engineContrat = new EngineContrat(engine);
	
		engine.addCommand(Move.DigL);
		engine.addCommand(Move.Up);
		engine.addCommand(Move.Down);
		engineContrat.addCommand(Move.Left);
		
		Assert.assertTrue("testaddCommandEnginePost failed", engine.getNextCommand() == Move.Left);
	}
	
	@Test
	public void testaddNewHoleEnginePost() {
		EngineService engine = getEngine();
		EngineContrat engineContrat = new EngineContrat(engine);
		
		HoleService h = new HoleImpl();
		h.init(1,1);
		engine.addNewHole(h);
		
		HoleService h1 = new HoleImpl();
		h1.init(2,1);
		engine.addNewHole(h1);
		
		HoleService hTest = new HoleImpl();
		hTest.init(5,1);
		engineContrat.addNewHole(hTest);
		
		
		Assert.assertTrue("testaddNewHoleEnginePost failed", engine.getHoles().size() == 3);
	}
	
	@Test
	public void testResetEnginePost() {
		EngineService engine = getEngine();
		EngineContrat engineContrat = new EngineContrat(engine);
		
		HoleService h = new HoleImpl();
		h.init(1,1);
		engine.addNewHole(h);
		
		HoleService h1 = new HoleImpl();
		h1.init(2,1);
		engine.addNewHole(h1);
		
		HoleService h2 = new HoleImpl();
		h2.init(5,1);
		engine.addNewHole(h2);
		
		for(Item t : engine.getTreasures()) {
			engine.getEnvi().getOut(t, t.getX(), t.getY());
			t.setOnFloor(false);
		}
		
		engine.getKey().setOnFloor(true);
		
		engineContrat.reset();
		
		Assert.assertTrue("testResetEnginePost failed", engine.getHoles().size() == 0);
		Assert.assertTrue("testResetEnginePost failed", engine.getKey().isOnFloor() == false);
	}
	
	@Test
	public void testStepGoNextMapEnginePost() {
		EngineService engine = getEngine();
		EngineContrat engineContrat = new EngineContrat(engine);
		PlayerService player = getPlayer();
		
		player.pickUpKey(engine.getKey());
		engine.getEnvi().setNature(player.getWdt(), player.getHgt(), Cell.DOR);
		for(int i =0; i<engine.getTreasures().size(); i++)
			player.pickUpTreasure();
		
		engine.addCommand(Move.Left);
		
		
		
		engineContrat.step();
		
		Assert.assertTrue("testStepGoNextMapEnginePost failed", engine.getHoles().size() == 0);
		Assert.assertTrue("testStepGoNextMapEnginePost failed", engine.getKey().isOnFloor() == false);
	}
	
	@Test
	public void testStepKeyAndDoorEnginePost() {
		EngineService engine = getEngine();
		EngineContrat engineContrat = new EngineContrat(engine);
		PlayerService player = getPlayer();
		
		int n = 0;
		Item lastT = null;
		for(Item t : engine.getTreasures()) {
			n++;
			t.setOnFloor(false);
			engine.getEnvi().getOut(t, t.getX(), t.getY());
			lastT = t;
		}
		for(int i = 1; i < n; i++)
			player.pickUpTreasure();
		lastT.setX(player.getWdt());
		lastT.setY(player.getHgt());
		engine.getEnvi().getIn(lastT, lastT.getX(), lastT.getY());
				
		engineContrat.step();
		
		Assert.assertTrue("testStepKeyAndDoorEnginePost failed", engine.getEnvi().cellNature(engine.doorX(), engine.doorY()) == Cell.DOR);
	}
	
	
	
	
	
	
	
}


