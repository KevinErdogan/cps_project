package display;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;


import impl.EngineImpl;

import itf.EngineService;



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
		EngineService engine = new EngineImpl();
		engine.initWithTxt("map1.txt");
		while(true){
			engine.step();
			Thread.sleep(5000);
		}
	}
}
