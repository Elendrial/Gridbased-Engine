package me.hii488.objects.tiles;

import java.awt.Graphics;

import me.hii488.Interfaces.ITickable;
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
	}
	
	public void render(Graphics g) {
		g.drawImage(currentTexture, gridPosition.getX() * Settings.Texture.tileSize, gridPosition.getY() * Settings.Texture.tileSize, null);
	}
}
