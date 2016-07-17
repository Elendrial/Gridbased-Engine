package me.hii488.gameWindow;

import java.awt.Canvas;
import java.awt.Graphics;

import me.hii488.gameWorld.World;

@SuppressWarnings("serial")
public class Display extends Canvas{

	public Display(Window window) {
		setBounds(0, 0, window.width, window.height);
	}
	
	// TODO: GUIs, like seriously, it shouldn't be hard to just make a custom GUI handler
	// Please do it self, like it'd make life so easy if you did, then all GUI stuff would be nicely in one place
	// And by GUI I mean additional stuff, aside from rendering, like buttons / text and stuff
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
