package me.hii488.gameWindow;

import java.awt.Canvas;
import java.awt.Graphics;

import me.hii488.gameWorld.World;

@SuppressWarnings("serial")
public class Display extends Canvas{

	public Display(Window window) {
		setBounds(0, 0, window.width, window.height);
	}
	
	public void render(Graphics g){
		try{
			World.worldContainers.get(World.currentWorldContainerID).render(g);
		}
		catch(Exception e){
			System.err.println("Error rendering current world container:");
			e.printStackTrace();
			System.exit(ERROR);
		}
	}
	
}
