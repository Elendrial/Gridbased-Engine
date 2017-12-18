package me.hii488.graphics.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.hii488.handlers.TextureHandler;

public class GUIPicture extends GUIElement{

	public String textureName = "";

	public String identifier, path = "textures/gui/";
	public int states = 0;
	public int currentState = 0;
	
	@Override
	public void onClick(MouseEvent e) {}

	@Override
	public void render(Graphics g) {
		if(hidden) return;
		g.drawImage(TextureHandler.getTexture(textureName + "_" + currentState), position.getX(), position.getY(), null);
	}
	
	public void setupTextures() {
		String sanitizedName = textureName.split("\\.")[0];
		if(states > 0){
			for(int i = 0; i < states; i++) TextureHandler.loadTexture(path, sanitizedName + "_" + i + "." + textureName.split("\\.")[1], this, sanitizedName + "_" + i);
		}
		else{
			TextureHandler.loadTexture(path, textureName, this, sanitizedName + "_0");
		}
		
		textureName = textureName.split("\\.")[0];
	}

	public String getTextureName() {
		return textureName;
	}

	public GUIPicture setTextureName(String textureName) {
		this.textureName = textureName;
		return this;
	}

	public int getStates() {
		return states;
	}

	public GUIPicture setStates(int states) {
		this.states = states;
		return this;
	}
	
	public GUIPicture setPath(String path) {
		this.path = "textures/" + path;
		return this;
	}
	
}
