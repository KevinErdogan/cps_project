package display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import itf.CharacterService;

public class MyKeyListener implements KeyListener {
	
	private CharacterService player;
	
	public MyKeyListener(CharacterService player) {
		this.player=player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_Z:
				player.goUp();
			break;
			case KeyEvent.VK_S:
				player.goDown();
			break;
			case KeyEvent.VK_Q:
				player.goLeft();
			break;
			case KeyEvent.VK_D:
				player.goDown();
			break;
		default:
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
