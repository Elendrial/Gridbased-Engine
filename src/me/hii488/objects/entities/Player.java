package me.hii488.objects.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import me.hii488.controllers.GameController;
import me.hii488.handlers.ContainerHandler;
import me.hii488.misc.Grid;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.TexturedObject;
import me.hii488.objects.tiles.BaseTile;

public class Player extends BaseEntity{

	public boolean moveable = true;
	public int speed = 2;

	@Override
	public void onLoad() {
		position = new Vector(GameController.windows[0].width / 2 - currentTexture.getWidth() / 2,	GameController.windows[0].height / 2 - currentTexture.getHeight() / 2);
	}

	@Override
	public void updateOnTick() {
		super.updateOnTick();
		if (moveable) {
			position.addToLocation(allowedMovement(queuedMovement));
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if(Settings.WorldSettings.debug){
			g.drawRect(position.getX(), position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
			g.fillRect(position.getX()-1, position.getY(), 3, 3);
			g.fillRect(position.getX() - speed-1, position.getY()-1, 3, 3);
			g.fillRect((int) (position.getX() + speed-1 + collisionBox.getWidth()), position.getY()-1, 3, 3);
			g.fillRect(position.getX()-1, (int) (position.getY() - speed)-1, 3, 3);
			g.fillRect(position.getX()-1, (int) (position.getY() + speed + collisionBox.getHeight())-1, 3, 3);
		}
	}

	// TODO: Check this works properly, it was virtually straight copied from V2
	protected Vector allowedMovement(Vector queuedMovement) {
		Grid g = ContainerHandler.getLoadedContainer().grid;
		Vector p = position.clone();
		
		Vector out = queuedMovement.clone();
		
		boolean updated = false;
			
		if(out.getAbsX() != 0){
			do{
				updated = false;
				BaseTile t = g.getTileAtAbsPosition((int)(p.getAbsX() + out.getAbsX()), (int)(p.getAbsY() + out.getAbsY()));
				BaseTile t2 = g.getTileAtAbsPosition((int)(p.getAbsX() + out.getAbsX() + currentTexture.getWidth()), (int)(p.getAbsY() + out.getAbsY()));
				
				if(t == null || t.isCollidable|| (t2 == null || t2.isCollidable)){
					updated = true;
					out.addToX((out.getAbsX() > 0) ? -1f : 1f);
				}
			}while(updated && (out.getAbsX() >= 1 || out.getAbsX() <= -1));
			
				
		}
		
		if(out.getAbsY() != 0){
			do{
				updated = false;
				BaseTile t = g.getTileAtAbsPosition((int)(p.getAbsX() + out.getAbsX()), (int)(p.getAbsY() + out.getAbsY()));
				BaseTile t2 = g.getTileAtAbsPosition((int)(p.getAbsX() + out.getAbsX()), (int)(p.getAbsY() + out.getAbsY()+ currentTexture.getHeight()));

				if((t == null || t.isCollidable) || (t2 == null || t2.isCollidable)){
					updated = true;
					out.addToY((out.getAbsY() > 0) ? -1f : 1f);
				}
			}while(updated && (out.getAbsY() >= 1 || out.getAbsY() <= -1));
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

	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public float randTickChance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateOnSec() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOnRandTick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initVars() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TexturedObject clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
