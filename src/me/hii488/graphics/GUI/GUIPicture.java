package me.hii488.graphics.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import me.hii488.handlers.TextureHandler;

public class GUIPicture extends GUIElement{

	public String textureName = "";
	public BufferedImage[] textureImages;
	public BufferedImage currentTexture;

	public String identifier;
	public int states = 0;
	public int currentState = 0;
	
	@Override
	public void onClick(MouseEvent e) {}

	@Override
	public void render(Graphics g) {
		if(hidden) return;
		g.drawImage(currentTexture, position.getX(), position.getY(), null);
	}
	
	public void setupTextures() {
		if(states > 1){
			this.textureImages = new BufferedImage[states+1];
			
			for(int i = 0; i < textureImages.length; i++)
				textureImages[i] = TextureHandler.loadTexture("textures/gui/", textureName.split("\\.")[0] + "_" + i + "." + textureName.split("\\.")[1], this);
			
			currentTexture = textureImages[0];
		}
		else{
			currentTexture = TextureHandler.loadTexture("textures/gui/", textureName, this);
		}
	}
	
}
