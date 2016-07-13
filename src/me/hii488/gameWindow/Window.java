package me.hii488.gameWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import me.hii488.gameWorld.World;

public class Window implements Runnable {

	// Actual window
	private JFrame frame;
	private Display display;

	public int width, height;
	public String title;

	// How often we want the game to tick per second
	public int targetTPS;

	public boolean isRunning;

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
		display.addKeyListener(World.player);
		display.addMouseListener(World.player);
		this.frame.add(this.display);
	}

	public void start() {
		isRunning = true;
		frame.requestFocus();
		new Thread(this).start();
	}

	public void stop() {
		isRunning = false;
	}

	private void render() {
		// Buffer Strategy
		BufferStrategy bs = this.display.getBufferStrategy();
		if (bs == null) {
			this.display.createBufferStrategy(2);
			this.display.requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// Clear the graphics
		g.clearRect(0, 0, width, height);

		// Draw the display
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		this.display.render(g);

		g.dispose();
		bs.show();
	}

	// Tick is deliberately throttled, it should only happen every 'x' ms, as it
	// applies game logic
	// FPS should happen as fast as it can, since it renders (only important if
	// the field of vision can change)

	public int FPS = 0;
	
	public void run() {
		int fps = 0;

		double fpsTimer = System.currentTimeMillis();

		while (isRunning && World.isRunning) {

			// This is NOT to sleep, but to limit the game loop
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			render();
			fps++;

			// If the current time is 1 second greater than the last time we
			// printed
			if (System.currentTimeMillis() - fpsTimer >= 1000) {
			//	System.out.printf("FPS: %d\tTPS: %d%n", fps, tick);
				FPS = fps;
				fps = 0;
				fpsTimer += 1000;
			}
		}
		
		World.closeGame(); // TODO: Remove this and have a better way, so that multiple windows can be opened etc...

		// When the gameloop is finished running, close the program
		this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));

	}
}
