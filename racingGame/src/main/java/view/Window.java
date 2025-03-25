package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import input.KeyboardInput;

public class Window {
	private JFrame frame;
	private Dimension dimension;
	private String title;
	
	private SmoothPanel panel;
	
	public Window(String title, Dimension dimension) {
		this.dimension = dimension;
		this.title = title;
		
		frame = new JFrame();
		panel = new SmoothPanel();
		init();
	}
	
	public void init() {
		frame.setPreferredSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.addKeyListener(new KeyboardInput());
		
		frame.setVisible(true);
		frame.pack();
		
		
	}
	
	public void render() {
		panel.repaint();
	}
	
	public void update() {
		
	}

}
