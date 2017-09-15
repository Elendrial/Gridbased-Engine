package me.hii488.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import me.hii488.handlers.ContainerHandler;
import me.hii488.misc.Settings;

@SuppressWarnings("serial")
public class Display extends Canvas{

	public Display(Window window) {
		setBounds(0, 0, window.width, window.height);
	}
	
	public void render(Graphics g){
		Color c = g.getColor();
		g.setColor(Settings.Texture.background);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(c);
		try{
			ContainerHandler.getLoadedContainer().render(g);
		}
		catch(Exception e){
			System.err.println("Error rendering current world container:");
			e.printStackTrace();
		}
	}
	
}
