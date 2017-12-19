package me.hii488.graphics.GUI;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import me.hii488.handlers.TextureHandler;

public class GUIPicture extends GUIElement{

	public String textureName = "";
	public String textureKey;
	
	public String identifier, path = "textures/gui/";
	public int states = 0;
	public int currentState = 0;
	
	@Override
	public void onClick(MouseEvent e) {}

	@Override
	public void render(Graphics g) {
		if(hidden) return;
		g.drawImage(TextureHandler.getTexture(textureKey + "_" + currentState), position.getX(), position.getY(), null);
	}
	
	public void setupTextures() {
		String sanitizedName = textureName.split("\\.")[0];
		if(textureKey == null) textureKey = sanitizedName;
		
		if(states > 0){
			for(int i = 0; i < states; i++) TextureHandler.loadTexture(path, sanitizedName + "_" + i + "." + textureName.split("\\.")[1], this, textureKey + "_" + i);
		}
		else{
			TextureHandler.loadTexture(path, textureName, this, textureKey + "_0");
		}
	}

	public String getTextureName() {
		return textureName;
	}

	public GUIPicture setTextureName(String textureName) {
		this.textureName = textureName;
		return this;
	}
	
	public String getTextureKey() {
		return textureKey;
	}

	public GUIPicture setTextureKey(String textureKey) {
		this.textureKey = textureKey;
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
		this.path = path;
		return this;
	}
	
}
