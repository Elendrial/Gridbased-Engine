package me.hii488.objects.tileTypes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.hii488.general.Position;
import me.hii488.helpers.TextureHelper;

public class BaseTile {
    // GRID STUFF//
	public Position gridPosition = new Position();
	
	
	public boolean isCollidable;

	public int zLayer; // 0 = ground,

	public String textureName = "";
	public BufferedImage[] textureImages;
	public BufferedImage currentTexture;

	public int states = 0;
	public int currentState = 0;
	
	
	public BaseTile(){
		this.textureImages = new BufferedImage[states+1];
		
		if(states == 0){
			textureImages[0] = TextureHelper.loadTexture("textures/tiles/", textureName, this);
		}
		else{
			for(int i = 0; i < textureImages.length; i++){
				textureImages[i] = TextureHelper.loadTexture("textures/tiles/", textureName.split("\\.")[0] + "_" + i + "." + textureName.split("\\.")[1], this);
			}
		}
	}
	
	public void onLoad() {currentTexture = textureImages[currentState];}
	
	public void setup() {}

	public void updateOnTick() {}

	public void updateOnSec() {}

	public void render(Graphics g, int tileSize) {
		g.drawImage(currentTexture, gridPosition.getX() * tileSize, gridPosition.getY() * tileSize, null);
	}
}
