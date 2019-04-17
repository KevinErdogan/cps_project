package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import itf.EngineService;
import itf.Move;

public class MyKeyListener implements KeyListener {
	
	private EngineService engine;
	
	public MyKeyListener(EngineService engine) {
		this.engine=engine;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_Z:
				engine.addCommand(Move.Up);
			break;
			case KeyEvent.VK_S:
				engine.addCommand(Move.Down);
			break;
			case KeyEvent.VK_Q:
				engine.addCommand(Move.Left);
			break;
			case KeyEvent.VK_D:
				engine.addCommand(Move.Right);
			break;
			case KeyEvent.VK_A:
				engine.addCommand(Move.DigL);
			break;
			case KeyEvent.VK_E:
				engine.addCommand(Move.DigR);
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}