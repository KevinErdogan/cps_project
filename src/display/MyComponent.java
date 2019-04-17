package display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

import javax.swing.JComponent;

import itf.CharacterService;
import itf.EngineService;
import itf.EnvironmentService;

class MyComponent extends JComponent{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8327769893614488527L;
	public static final int SQUARE_SIZE = 25;
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

		addKeyListener(new MyKeyListener(engine));
		setFocusable(true);
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
				g.fillRect(MyComponent.SQUARE_SIZE * i, MyComponent.SQUARE_SIZE * (env.getHeight() - 1 - j), MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);

			}
		}

		/* display guards */
		Set<CharacterService> guards = engine.getGuards();

		g.setColor(Color.red);
		for(CharacterService guard : guards) {
			g.drawOval(MyComponent.SQUARE_SIZE * guard.getWdt(), MyComponent.SQUARE_SIZE  * (env.getHeight() - 1 - guard.getHgt()), MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);
		}

		/* display player */
		CharacterService player = engine.getPlayer();
		g.setColor(Color.white);
		g.drawOval(MyComponent.SQUARE_SIZE  * player.getWdt(), MyComponent.SQUARE_SIZE * (env.getHeight()-1 - player.getHgt()), MyComponent.SQUARE_SIZE, MyComponent.SQUARE_SIZE);

		g.dispose();
	}

}
