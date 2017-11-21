package me.hii488.objects.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.hii488.controllers.GameController;
import me.hii488.graphics.Camera;
import me.hii488.handlers.ContainerHandler;
import me.hii488.handlers.EntityHandler;
import me.hii488.interfaces.ITickable;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.TexturedObject;
import me.hii488.registries.EntityRegistry;

public abstract class BaseEntity extends TexturedObject implements ITickable{
	public String containerIdentifier;
	public boolean showCollisionBox = false;
	public boolean notDestroyed = true;
	public boolean destroyIfOutside = false;

	public Vector position = new Vector(0,0);
	public Vector queuedMovement = new Vector(0, 0);
	public Rectangle collisionBox = new Rectangle(0,0,0,0);
	
	public BaseEntity(){
		super();
		EntityRegistry.registerEntity(this);
	}
	
	protected BaseEntity(BaseEntity t){
		super(t);
		this.position = t.position.clone();
		this.collisionBox = new Rectangle(t.collisionBox);
		this.showCollisionBox = t.showCollisionBox;
	}
	
	public void updateOnTick() {
		collisionBox = new Rectangle(position.getX(), position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
		if(destroyIfOutside && EntityHandler.isOutOfContainer(this)) this.destroy();
	}
	
	private Vector renderPosA = new Vector(); // Upper left corner
	private Vector renderPosB = new Vector(); // Lower right corner
	public void render(Graphics g) {
		if(this.states > 1) currentTexture = textureImages[currentState];
		
		renderPosA.setX(position.getX() - Camera.cameraPosition.getX());
		renderPosA.setY(position.getY() - Camera.cameraPosition.getY());
		renderPosB.setX(renderPosA.getAbsX() + (Settings.Texture.tileSize * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (Settings.Texture.tileSize * Camera.scale));
		
		if(renderPosA.getX() < GameController.windows[0].width && renderPosB.getX() > 0){
			if(renderPosA.getY() < GameController.windows[0].height && renderPosB.getY() > 0){
				g.drawImage(currentTexture, renderPosA.getX(), renderPosA.getY(), null);
			}
		}
		
		if(Settings.Logging.debug || this.showCollisionBox){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(this.collisionBox.x  - Camera.cameraPosition.getX(), this.collisionBox.y - Camera.cameraPosition.getY(), this.collisionBox.width, this.collisionBox.height);
			g.setColor(c);
		}
	}

	public void destroy(){
		this.onDestroy();
		ContainerHandler.containers.get(containerIdentifier).removeEntity(this);
		notDestroyed = false;
	}
}
