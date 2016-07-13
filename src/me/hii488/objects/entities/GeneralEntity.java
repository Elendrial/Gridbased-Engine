package me.hii488.objects.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import me.hii488.gameWorld.World;
import me.hii488.general.Position;
import me.hii488.general.Settings;
import me.hii488.general.Vector;
import me.hii488.helpers.EntityHelper;
import me.hii488.helpers.TextureHelper;

// TODO : add this to base
public class GeneralEntity {
	public Position position;
	public Rectangle collisionBox;
	public BufferedImage textureImage;
	public String textureName = "";
	public Vector queuedMovement = new Vector(0, 0);
	public boolean notDestroyed = true;

	public GeneralEntity(){}
	
	protected GeneralEntity(GeneralEntity toCopy){
		this.setup();
		position = toCopy.position;
		notDestroyed = toCopy.notDestroyed;
	}
	
	public void setup() {
		try {
			textureImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\textures\\entities\\" + textureName));
		} catch (Exception e) {
			try {
				TextureHelper.TextureNotFoundPrint(textureName, this.getClass());
				textureImage = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\" + Settings.defaultEntityTextureLocation));
			} catch (Exception e1) {
				TextureHelper.TextureNotFoundPrint(Settings.defaultEntityTextureLocation, this.getClass());
			}
		}
		collisionBox = new Rectangle(textureImage.getWidth(), textureImage.getHeight());
	}

	public void updateOnTick() {
		collisionBox = new Rectangle(position.getX(),position.getY(),textureImage.getWidth(), textureImage.getHeight());
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
		
		g.drawImage(textureImage, position.getX(), position.getY(), null);
		
		Color c = g.getColor();
		g.setColor(Color.red);
		g.drawRect(this.collisionBox.x, this.collisionBox.y, this.collisionBox.width, this.collisionBox.height);
		g.setColor(c);
	}

	public Vector getMotion() {
		return new Vector(0, 0);
	}
	
	public void destroy(){
		World.getCurrentWorldContainer().entities.remove(this);
		notDestroyed = false;
	}
	
	public GeneralEntity clone(){
		return new GeneralEntity(this);
	}
	
}
