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
	private EngineService engine;
	
	public MyComponent(int w, int h, EngineService engine) {
		this.engine=engine;

		javax.swing.GroupLayout mainPaneLayout = new javax.swing.GroupLayout(this);
		setLayout(mainPaneLayout);
		mainPaneLayout.setHorizontalGroup(
				mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, MyComponent.SQUARE_SIZE * w, Short.MAX_VALUE)
        );
		mainPaneLayout.setVerticalGroup(
				mainPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, MyComponent.SQUARE_SIZE * h, Short.MAX_VALUE)
        );

		setVisible(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		EnvironmentService env = engine.getEnvi();

		/* display env */
		for(int i =0; i < env.getWidth(); i++) {
			for(int j =0; j < env.getHeight(); j++) {
				switch(env.cellNature(i, j)) {
				case MTL:
					g.setColor(Color.gray);
				break;
				case HDR:
					g.setColor(Color.cyan);
					break;
				case HOL:
					g.setColor(Color.orange);
					break;
				case LAD:
					g.setColor(Color.yellow);
					break;
				case PLT:
					g.setColor(Color.WHITE);
					break;
				default:
					// CELL.EMP
					g.setColor(Color.BLACK);
				}
				g.fillRect(MyComponent.SQUARE_SIZE * i, MyComponent.SQUARE_SIZE * j, MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);
			
			}
		}
		
		/* display guards */
		Set<CharacterService> guards = engine.getGuards();
		
		g.setColor(Color.red);
		for(CharacterService guard : guards) {
			g.drawOval(MyComponent.SQUARE_SIZE * guard.getWdt(), MyComponent.SQUARE_SIZE  * guard.getHgt(), MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);
		}
		
		/* display player */
		CharacterService player = engine.getPlayer();
		g.setColor(Color.white);
		g.drawOval(MyComponent.SQUARE_SIZE  * player.getWdt(), MyComponent.SQUARE_SIZE * player.getHgt(), MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);
		
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
		
		setSize(width, height);
		mainPane = new MyComponent(width, height, e);

		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
	public static void main(String[] args) {
		EnvironmentService es = new EnvironmentImpl(50, 50);
		es.init(50, 50);
		for(int i =0; i<50; i++)
			es.setNature(i, 49, Cell.MTL);

		Set<CharacterService> guards = new HashSet<CharacterService>();
		guards.add(new CharacterImpl(es, 20, 48));
		
		PlayerService player = new PlayerImpl(es, 10, 10);
		EngineImpl engine = new EngineImpl(es, player, guards);
		player.init(0, 48, es, engine);
		engine.init();
		for(int i =0; i < 100; i++)
			engine.step();
	}


	public void step() {
		mainPane.repaint();
	}
	
}

