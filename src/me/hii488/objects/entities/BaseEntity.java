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
		collisionBox = new Rectangle(position.getX(), position.getY(), getTexture().getWidth(), getTexture().getHeight());
		if(destroyIfOutside && EntityHandler.isOutOfContainer(this)) this.destroy();
	}
	
	public void endOfTick(){
		textureName = sanitizedName + "_" + currentState;
	}
	
	public void updateRenderPosition() {
		renderPosA.setX(position.getX() - Camera.getPosition().getX());
		renderPosA.setY(position.getY() - Camera.getPosition().getY());
		renderPosB.setX(renderPosA.getAbsX() + (getTexture().getWidth() * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (getTexture().getHeight() * Camera.scale));
	}
	
	public boolean shouldRender() {
		updateRenderPosition();
		return (renderPosA.getX() < GameController.getWindow().width && renderPosB.getX() > 0) && (renderPosA.getY() < GameController.getWindow().height && renderPosB.getY() > 0);
	}
	
	protected Vector renderPosA = new Vector(); // Upper left corner
	protected Vector renderPosB = new Vector(); // Lower right corner
	public void render(Graphics g) {
		g.drawImage(getTexture(), renderPosA.getX(), renderPosA.getY(), null);
		
		if(Settings.Logging.debug || this.showCollisionBox){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(this.collisionBox.x  - Camera.getPosition().getX(), this.collisionBox.y - Camera.getPosition().getY(), this.collisionBox.width, this.collisionBox.height);
			g.drawString(this.currentState + ":" + this.textureName, renderPosA.getX(), renderPosA.getY());
			g.setColor(c);
		}
	}

	public void destroy(){
		this.onDestroy();
		ContainerHandler.containers.get(containerIdentifier).removeEntity(this);
		notDestroyed = false;
	}
	
	public RenderEntity createRenderEntity(){
		return new RenderEntity(this);
	}
}
