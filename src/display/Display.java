package display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;

import impl.CharacterImpl;
import impl.EngineImpl;
import impl.EnvironmentImpl;
import impl.PlayerImpl;
import itf.Cell;
import itf.CharacterService;
import itf.EngineService;
import itf.EnvironmentService;
import itf.PlayerService;

class MyComponent extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8327769893614488527L;
	public static final int SQUARE_SIZE = 15;
	private EnvironmentService env;
	
	public MyComponent(int w, int h, EnvironmentService env) {
		this.env=env;
		setSize(w, h);
		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(int i =0; i < env.getWidth(); i++) {
			for(int j =0; j < env.getHeight(); j++) {
				
				switch(env.cellNature(i, j)) {
				case MTL:
					g.setColor(Color.DARK_GRAY);
				break;
				case HDR:
					g.setColor(Color.cyan);
					break;
				case HOL:
					g.setColor(Color.gray);
					break;
				case LAD:
					g.setColor(Color.yellow);
					break;
				case PLT:
					g.setColor(Color.black);
					break;
				default:
					// CELL.EMP
					g.setColor(Color.WHITE);
				}
				g.fillRect(MyComponent.SQUARE_SIZE * i, MyComponent.SQUARE_SIZE * j, MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);
			
			}
		}
		
		g.dispose();
	}
}


public class Display extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1853996690309457043L;
	private MyComponent mainPane;
	
	public Display(EngineService e) {
		int width = e.getEnvi().getWidth();
		int height = e.getEnvi().getHeight();
		setTitle("Load Runner");
		setSize(MyComponent.SQUARE_SIZE*width, MyComponent.SQUARE_SIZE * height);
		mainPane = new MyComponent(MyComponent.SQUARE_SIZE * width, MyComponent.SQUARE_SIZE * height, e.getEnvi());
		setContentPane(mainPane);
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public static void main(String[] args) {
		EnvironmentService es = new EnvironmentImpl(50, 50);
		for(int i =0; i<50; i++)
			es.setNature(i, 49, Cell.MTL);
		//es.init(50, 50);
		PlayerService player = new PlayerImpl(es, 10, 10);
		Set<CharacterService> guards = new HashSet<CharacterService>();
		guards.add(new CharacterImpl(es, 0, 20));
		EngineImpl engine = new EngineImpl(es, player, guards);
		engine.init();
		for(int i =0; i < 100; i++)
			engine.step();
	}


	public void step() {
		mainPane.repaint();
	}
	
}

