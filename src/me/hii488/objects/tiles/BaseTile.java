package me.hii488.objects.tiles;

import java.awt.Color;
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
	
	
	public void onDestroy(){}
	public abstract BaseTile clone();
	
	protected Vector renderPosA = new Vector(); // Upper left corner
	protected Vector renderPosB = new Vector(); // Lower right corner
	
	public void updateRenderPosition() {
		renderPosA.setX(gridPosition.getAbsX() * Camera.scale * Settings.Texture.tileSize - Camera.getPosition().getAbsX());
		renderPosA.setY(gridPosition.getAbsY() * Camera.scale * Settings.Texture.tileSize - Camera.getPosition().getAbsY());
		renderPosB.setX(renderPosA.getAbsX() + (Settings.Texture.tileSize * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (Settings.Texture.tileSize * Camera.scale));
	}
	
	public boolean shouldRender() {
		updateRenderPosition();
		return (renderPosA.getX() < GameController.getWindow().width && renderPosB.getX() > 0) && (renderPosA.getY() < GameController.getWindow().height && renderPosB.getY() > 0);
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		g.drawImage(getTexture(), renderPosA.getX(), renderPosA.getY(), null);
				
		if(Settings.Logging.debug && isCollidable){
			g.setColor(Color.red);
			g.drawRect(renderPosA.getX(), renderPosA.getY(), (int)(Settings.Texture.tileSize * Camera.scale), (int)(Settings.Texture.tileSize * Camera.scale));
		}		
	}
}
