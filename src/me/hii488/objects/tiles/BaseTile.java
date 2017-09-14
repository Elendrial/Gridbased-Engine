package me.hii488.objects.tiles;

import java.awt.Graphics;

import me.hii488.controllers.GameController;
import me.hii488.graphics.Camera;
import me.hii488.interfaces.ITickable;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.TexturedObject;
import me.hii488.registries.TileRegistry;

public abstract class BaseTile extends TexturedObject implements ITickable{
	public Vector gridPosition = new Vector(0,0);
	public boolean isCollidable;
	
	public BaseTile(){
		super();
		TileRegistry.registerTile(this);
	}
	
	protected BaseTile(BaseTile t){
		super(t);
		this.isCollidable = t.isCollidable;
		this.gridPosition = t.gridPosition;
		this.gridPosition = t.gridPosition.clone();
	}
	
	public abstract BaseTile clone();
	
	private Vector renderPosA = new Vector(); // Upper left corner
	private Vector renderPosB = new Vector(); // Lower right corner
	public void render(Graphics g) {
		renderPosA.setX(gridPosition.getAbsX() * Camera.scale * Settings.Texture.tileSize - Camera.cameraPosition.getAbsX());
		renderPosA.setY(gridPosition.getAbsY() * Camera.scale * Settings.Texture.tileSize - Camera.cameraPosition.getAbsY());
		renderPosB.setX(renderPosA.getAbsX() + (Settings.Texture.tileSize * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (Settings.Texture.tileSize * Camera.scale));
		
		if(renderPosA.getX() < GameController.windows[0].width && renderPosB.getX() > 0){
			if(renderPosA.getY() < GameController.windows[0].height && renderPosB.getY() > 0){
				g.drawImage(currentTexture, renderPosA.getX(), renderPosA.getY(), null);
			}
		}
	}
}
