package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import itf.Command;
import itf.EngineService;

public class MyKeyListener implements KeyListener {

	private EngineService engine;

	public MyKeyListener(EngineService engine) {
		this.engine=engine;
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_Z:
			engine.addCommand(Command.Up);
		break;
		case KeyEvent.VK_S:
			engine.addCommand(Command.Down);
		break;
		case KeyEvent.VK_Q:
			engine.addCommand(Command.Left);
		break;
		case KeyEvent.VK_D:
			engine.addCommand(Command.Right);
		break;
		case KeyEvent.VK_A:
			engine.addCommand(Command.DigL);
		break;
		case KeyEvent.VK_E:
			engine.addCommand(Command.DigR);
		break;
		default:
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
