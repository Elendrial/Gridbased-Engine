package me.hii488.objects.entities;

import java.awt.event.KeyEvent;

import me.hii488.handlers.ContainerHandler;
import me.hii488.handlers.InputHandler;
import me.hii488.interfaces.IInputUser;
import me.hii488.misc.Grid;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.TexturedObject;

public class Player extends BaseEntity implements IInputUser{

	public Player(){
		super();
		InputHandler.inputUsers.add(this);
		this.collisionBox.setBounds(0, 0, getTexture().getWidth(), getTexture().getHeight());
	}
	
	public boolean usesEngineMovement;
	public int speed;
	
	@Override
	public void initVars() {
		this.identifier = "player";
		usesEngineMovement = true;
		speed = 2;
	}
	
	@Override
	public void onLoad() {
		position = new Vector(ContainerHandler.getLoadedContainer().grid.dimensions.getX()/2 * Settings.Texture.tileSize, ContainerHandler.getLoadedContainer().grid.dimensions.getY()/2 * Settings.Texture.tileSize);
	}

	@Override
	public void updateOnTick() {
		super.updateOnTick();
		if (usesEngineMovement && !queuedMovement.isZeroVector()) {
			position.addToLocation(allowedMovement(queuedMovement));
		}
	}
	
	protected Vector allowedMovement(Vector queuedMovement) { // TODO: Fix corner case (heh), where going ur or ul into a corner tps you away.
		Grid g = ContainerHandler.getLoadedContainer().grid;
		Vector p = position.clone(), a;
		
		Vector out = queuedMovement.clone();
		
		if(out.getAbsY() < 0){
			a = p.clone().addToLocation(0, out.getAbsY()); // Test -ve y movement
			if(g.getTileAtVector(a).isCollidable || g.getTileAtVector(a.clone().addToLocation(collisionBox.width-1, 0)).isCollidable){
				out.setY((position.getY())%Settings.Texture.tileSize); // Get dist between top of player and above tile.
			}
		}
		else if(out.getAbsY() > 0){
			a = p.clone().addToLocation(0, out.getAbsY()); // Test +ve y movement
			if(g.getTileAtVector(a.clone().addToLocation(0, collisionBox.height-1)).isCollidable || g.getTileAtVector(a.clone().addToLocation(collisionBox.width-1, collisionBox.height-1)).isCollidable){
				out.setY(Settings.Texture.tileSize - collisionBox.height - (position.getY()) % Settings.Texture.tileSize); // Get dist between bottom of player and tile below.
			}
		}
		
		if(out.getAbsX() < 0){
			a = p.clone().addToLocation(out.getAbsX(), 0); // Test -ve x movement
			if(g.getTileAtVector(a).isCollidable || g.getTileAtVector(a.clone().addToLocation(0, collisionBox.height-1)).isCollidable){
				out.setX((position.getX()) % Settings.Texture.tileSize); // Get dist between left of player and left tile.
			}
		}
		else if(out.getAbsX() > 0){
			a = p.clone().addToLocation(out.getAbsX(), 0); // Test +ve x movement
			if(g.getTileAtVector(a.clone().addToLocation(collisionBox.width-1, 0)).isCollidable || g.getTileAtVector(a.clone().addToLocation(collisionBox.width-1, collisionBox.height-1)).isCollidable){
				out.setX(Settings.Texture.tileSize - collisionBox.width - (position.getX()) % Settings.Texture.tileSize); // Get dist between right of player and right tile.
			}
		}
		
		return out;
	}
	
	public void keyDown(KeyEvent arg0){
		switch (arg0.getKeyCode()) {
		case 37:
			queuedMovement.setX(-speed);
			break;
		case 38:
			queuedMovement.setY(-speed);
			break;
		case 39:
			queuedMovement.setX(speed);
			break;
		case 40:
			queuedMovement.setY(speed);
			break;
		case KeyEvent.VK_A:
			queuedMovement.setX(-speed);
			break;
		case KeyEvent.VK_W:
			queuedMovement.setY(-speed);
			break;
		case KeyEvent.VK_D:
			queuedMovement.setX(speed);
			break;
		case KeyEvent.VK_S:
			queuedMovement.setY(speed);
			break;
		}
	}
	
	public void keyUp(KeyEvent arg0){
		switch (arg0.getKeyCode()) {
		case 37:
			queuedMovement.setX(0);
			break;
		case 38:
			queuedMovement.setY(0);
			break;
		case 39:
			queuedMovement.setX(0);
			break;
		case 40:
			queuedMovement.setY(0);
			break;
		case KeyEvent.VK_A:
			queuedMovement.setX(0);
			break;
		case KeyEvent.VK_W:
			queuedMovement.setY(0);
			break;
		case KeyEvent.VK_D:
			queuedMovement.setX(0);
			break;
		case KeyEvent.VK_S:
			queuedMovement.setY(0);
			break;
		}
	}

	public void keyPressed(KeyEvent arg0) {
		keyDown(arg0);
	}

	public void keyReleased(KeyEvent arg0) {
		keyUp(arg0);
	}

	public void keyTyped(KeyEvent arg0) {
		keyDown(arg0);
	}
	
	@Override
	public float randTickChance() {return 0;}

	@Override
	public void updateOnSec() {}

	@Override
	public void updateOnRandTick() {}

	@Override
	public void onDestroy() {}

	@Override
	public TexturedObject clone() { // Player should not be copied.
		return null;
	}

}
