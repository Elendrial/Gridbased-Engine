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
	}
	
	private Vector renderPosA = new Vector(); // Upper left corner
	private Vector renderPosB = new Vector(); // Lower right corner
	public void render(Graphics g) {
		renderPosA.setX((gridPosition.getX() - Camera.cameraPosition.getAbsX()) * Settings.Texture.tileSize * Camera.scale);
		renderPosA.setY((gridPosition.getY() - Camera.cameraPosition.getAbsY()) * Settings.Texture.tileSize * Camera.scale);
		renderPosB.setX(renderPosA.getAbsX() + (Settings.Texture.tileSize * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (Settings.Texture.tileSize * Camera.scale));
		
		if(renderPosA.getAbsX() >= 0 && renderPosB.getAbsX() <= GameController.windows[0].width){
			if(renderPosA.getAbsY() >= 0 && renderPosB.getAbsY() <= GameController.windows[0].height){ // If x1,x2,y1,y2 all in area.
				g.drawImage(currentTexture, renderPosA.getX(), renderPosA.getY(), (int) (Settings.Texture.tileSize * Camera.scale), (int) (Settings.Texture.tileSize * Camera.scale), null);
			}
			else if(renderPosB.getAbsY() > 0 || renderPosA.getAbsY() < GameController.windows[0].height){ // If x1, x2, (y1 or y2) in area
				g.drawImage(currentTexture, renderPosA.getX(), renderPosA.getY() < GameController.windows[0].height ? renderPosA.getY() : 0,
							renderPosB.getX(), renderPosB.getY() > 0 ? renderPosB.getY() : 0,
							0, renderPosA.getY() < GameController.windows[0].height ? 0 : -renderPosA.getY(),
							Settings.Texture.tileSize,  renderPosB.getY() > 0 ? Settings.Texture.tileSize : renderPosB.getY() - renderPosA.getY(), null);
			}
		}
		else if(renderPosB.getAbsX() > 0 || renderPosA.getAbsX() < GameController.windows[0].width){
			if(renderPosA.getAbsY() >= 0 && renderPosB.getAbsY() <= GameController.windows[0].height){ // If y1, y2, (x1 or x2) in area
				g.drawImage(currentTexture, renderPosA.getX() < GameController.windows[0].width ? renderPosA.getX() : 0, renderPosA.getY(),
						renderPosB.getX() > 0 ? renderPosB.getX() : 0, renderPosB.getY(),
						renderPosA.getX() < GameController.windows[0].width? 0 : -renderPosA.getX(), 0,
						renderPosB.getX() > 0 ? Settings.Texture.tileSize : renderPosB.getX() - renderPosA.getX(), Settings.Texture.tileSize, null);
			}
			else if(renderPosB.getAbsY() > 0 || renderPosA.getAbsY() < GameController.windows[0].height){ // If (x1 or x2), (y1 or y2) in area
				g.drawImage(currentTexture, renderPosA.getX() < GameController.windows[0].width ? renderPosA.getX() : 0, renderPosA.getY() < GameController.windows[0].height ? renderPosA.getY() : 0,
						renderPosB.getX() > 0 ? renderPosB.getX() : 0, renderPosB.getY() > 0 ? renderPosB.getY() : 0,
						renderPosA.getX() < GameController.windows[0].width? 0 : -renderPosA.getX(), renderPosA.getY() < GameController.windows[0].height ? 0 : -renderPosA.getY(),
						renderPosB.getX() > 0 ? Settings.Texture.tileSize : renderPosB.getX() - renderPosA.getX(), renderPosB.getY() > 0 ? Settings.Texture.tileSize : renderPosB.getY() - renderPosA.getY(), null);
			}
		}
	}
}
