package me.hii488.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.hii488.handlers.TextureHandler;

public abstract class TexturedObject {
	
	public String textureName = "";
	public BufferedImage[] textureImages;
	public BufferedImage currentTexture;

	public String identifier;
	public int states = 0;
	public int currentState = 0;
	
	public TexturedObject(){
		initVars();
		setupTextures();
	}
	
	public TexturedObject(TexturedObject t){
		this.states = t.states;
		this.currentState = t.currentState;
		this.textureName = t.textureName;
		setupTextures();
	}
	
	public void setupTextures() {
		if(states > 1){
			this.textureImages = new BufferedImage[states+1];
			
			for(int i = 0; i < textureImages.length; i++)
				textureImages[i] = TextureHandler.loadTexture("textures/tiles/", textureName.split("\\.")[0] + "_" + i + "." + textureName.split("\\.")[1], this);
			
			currentTexture = textureImages[0];
		}
		else{
			currentTexture = TextureHandler.loadTexture("textures/tiles/", textureName, this);
		}
	}
	
	public abstract void initVars();
	public abstract void render(Graphics g);
	public abstract void onLoad();
	public abstract void onDestroy();
	public abstract TexturedObject clone();
}