package me.hii488.objects.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

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

	public Vector position = new Vector(0,0);
	public Vector queuedMovement = new Vector(0, 0);
	public Rectangle collisionBox = new Rectangle(0,0,0,0);
	
	public BaseEntity(){
		super();
		EntityRegistry.registerEntity(this);
	}
	
	protected BaseEntity(BaseEntity t){
		super(t);
		this.position = t.position;
		this.collisionBox = t.collisionBox;
		this.showCollisionBox = t.showCollisionBox;
	}
	
	public void updateOnTick() {
		collisionBox = new Rectangle(position.getX(),position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
		if(EntityHandler.isOutOfContainer(this)){
			this.destroy();
		}
	}
	
	public void render(Graphics g) {
		if(this.states > 1) currentTexture = textureImages[currentState];
		g.drawImage(currentTexture, position.getX(), position.getY(), null);
		
		if(Settings.Logging.debug || this.showCollisionBox){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(this.collisionBox.x, this.collisionBox.y, this.collisionBox.width, this.collisionBox.height);
			g.setColor(c);
		}
	}

	public void destroy(){
		ContainerHandler.containers.get(containerIdentifier).removeEntity(this);
		notDestroyed = false;
	}
}
