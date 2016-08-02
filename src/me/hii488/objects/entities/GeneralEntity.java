package me.hii488.objects.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.tickControl.ITickable;
import me.hii488.general.Position;
import me.hii488.general.Settings;
import me.hii488.general.Vector;
import me.hii488.helpers.EntityHelper;
import me.hii488.helpers.TextureHelper;

public class GeneralEntity implements ITickable{
	public Position position = new Position(0,0);
	public Rectangle collisionBox = new Rectangle(0,0,0,0);
	public boolean showCollisionBox = false;
	public boolean notDestroyed = true;
	
	public BufferedImage[] textureImages;
	public BufferedImage currentTexture;
	public String textureName = "";
	
	public Vector queuedMovement = new Vector(0, 0);
	
	public int containerID;
	
	public float randTickChance = 0f;

	public int states = 0;
	public int currentState = 0;
	
	public GeneralEntity(){}
	
	protected GeneralEntity(GeneralEntity toCopy){
		this.setup();
		position = toCopy.position;
		notDestroyed = toCopy.notDestroyed;
		currentState = toCopy.currentState;
	}
	
	public void setup() {
		this.textureImages = new BufferedImage[states+1];
		
		if(states == 0){
			textureImages[0] = TextureHelper.loadTexture("textures/entities/", textureName, this);
		}
		else{
			for(int i = 0; i < textureImages.length; i++){
				textureImages[i] = TextureHelper.loadTexture("textures/entities/", textureName.split("\\.")[0] + "_" + i + "." + textureName.split("\\.")[1], this);
			}
		}
		currentTexture = textureImages[currentState];
		collisionBox = new Rectangle(position.getX(),position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
	}

	public void updateOnTick() {
		collisionBox = new Rectangle(position.getX(),position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
		if(EntityHelper.isOutOfContainer(this)){
			this.destroy();
		}
		return;
	}

	public void updateOnSec() {
		
	}

	public void onLoad() {

	}

	public void render(Graphics g) {
		currentTexture = textureImages[currentState];
		g.drawImage(currentTexture, position.getX(), position.getY(), null);
		
		if(Settings.Logging.debug || this.showCollisionBox){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(this.collisionBox.x, this.collisionBox.y, this.collisionBox.width, this.collisionBox.height);
			g.setColor(c);
		}
	}

	public Vector getMotion() {
		return new Vector(0, 0);
	}
	
	public void destroy(){
		World.worldContainers.get(containerID).destroyedInTick.add(this);
		notDestroyed = false;
	}
	
	public GeneralEntity clone(){
		return new GeneralEntity(this);
	}

	@Override
	public boolean alwaysTicks() {return false;} // Entities cannot natively always tick, as that could massively increase time taken to tick for large systems. Use an external ITickable to call the specific entities tick if you need to.

	@Override
	public float randTickChance() {return randTickChance;}

	@Override
	public void updateOnRandTick() {}
	
}
