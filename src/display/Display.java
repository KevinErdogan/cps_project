package display;

import java.util.HashSet;
import java.util.Set;

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

	public void step() {
		mainPane.repaint();
	}
	
	
	public static void main(String[] args) {
		EnvironmentService es = new EnvironmentImpl(50, 50);
		es.init(50, 50);
		for(int i =0; i<50; i++)
			es.setNature(i, 0, Cell.MTL);

		Set<CharacterService> guards = new HashSet<CharacterService>();
		guards.add(new CharacterImpl(es, 20, 1));
		
		PlayerService player = new PlayerImpl(es, 10, 10);
		EngineImpl engine = new EngineImpl(es, player, guards);
		player.init(0, 1, es, engine);
		engine.init();
		for(int i =0; i < 100; i++)
			engine.step();
	}
}

