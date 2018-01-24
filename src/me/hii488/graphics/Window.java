package me.hii488.graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import me.hii488.handlers.InputHandler;

public class Window {

	// Actual window
	public JFrame frame;
	public Display display;

	public int width, height;
	public String title;

	// How often we want the game to tick per second
	public int targetTPS;

	public Window(String title, int width, int height) {
		// Set the variables
		this.title = title;
		this.width = width;
		this.height = height;

		// Setup Window
		this.frame = new JFrame(title);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
		this.frame.setResizable(false);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}
	
	public void createDisplay(){
		this.display = new Display(this);
		display.addKeyListener(InputHandler.instance);
		display.addMouseListener(InputHandler.instance);
		this.frame.add(this.display);
	}
	
}
