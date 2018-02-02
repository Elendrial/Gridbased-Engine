package me.hii488.controllers;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import me.hii488.graphics.Camera;
import me.hii488.graphics.Window;

public class RenderController implements Runnable{
	
	public Window window;
	public boolean isRunning;
	
	public void start() {
		isRunning = true;
		window.frame.requestFocus();
		new Thread(this).start();
	}
	
	public void restart(){
		isRunning = true;
	}
	
	public void stop() {
		isRunning = false;
	}

	private void render() {
		BufferStrategy bs = window.display.getBufferStrategy();
		if (bs == null) {
			window.display.createBufferStrategy(2);
			window.display.requestFocus();
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.clearRect(0, 0, window.width, window.height);
		
		window.display.render(g);

		g.dispose();
		bs.show();
	}

	public synchronized void update(){
		update = true;
		notifyAll();
	}
	
	private boolean update = false;
	private synchronized void waitForUpdate(){
		while(!update){
			try {
				this.wait();
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		update = false;
	}
	
	public void run() {

		while (GameController.isRunning) {
			waitForUpdate();
			if(isRunning) render();
			Camera.update();
			UpdateController.instance.renderDone();
		}
		
		// When the gameloop is finished running, close the program
		window.frame.dispatchEvent(new WindowEvent(window.frame, WindowEvent.WINDOW_CLOSING));

	}
	
}
