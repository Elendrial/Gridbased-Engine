package me.hii488.objects.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.hii488.gameWorld.World;
import me.hii488.general.Grid;
import me.hii488.general.Position;
import me.hii488.general.Vector;

public class Player extends GeneralEntity implements MouseListener, KeyListener {

	public boolean moveable = true;
	public int speed = 2;

	@Override
	public void setup() {
		textureName = "player.jpg";
		super.setup();
	}

	@Override
	public void onLoad() {
		super.onLoad();
		position = new Position(World.mainWindow.width / 2 - textureImage.getWidth() / 2,	World.mainWindow.height / 2 - textureImage.getHeight() / 2);
	}

	@Override
	public void updateOnTick() {
		super.updateOnTick();
		if (moveable) {
			position.addVector(allowedVector(queuedMovement));
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	// TODO: Collision detection
	protected Vector allowedVector(Vector v) {
	/*	Grid g = World.worldContainers.get(World.currentWorldContainerID).grid;
		Position p = g.getGridPositionOn(position.clone().addVector(v));
		
		try{ if(g.getTile(p.getX() - 1, p.getY()).getTileType().isCollidable) if (out.getDx() < 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(p.getX() + 1, p.getY()).getTileType().isCollidable)if (out.getDx() > 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(p.getX(), p.getY() - 1).getTileType().isCollidable)if (out.getDy() > 0)	out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		try{if(g.getTile(p.getX(), p.getY() + 1).getTileType().isCollidable)if (out.getDy() < 0) out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		return v;
		
	 */
		/*
		Grid g = World.worldContainers.get(World.currentWorldContainerID).grid;
		Position p = position.clone().addVector(v);
		
		try{if(g.getTile(g.getGridPositionOn(p.getX() - speed, p.getY())).getTileType().isCollidable && out.getDx() < 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX() + speed, p.getY())).getTileType().isCollidable && out.getDx() > 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX(), p.getY() - speed)).getTileType().isCollidable && out.getDx() > 0) out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX(), p.getY() + speed)).getTileType().isCollidable && out.getDx() < 0) out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		return v;
		*/
		
		Grid g = World.worldContainers.get(World.currentWorldContainerID).grid;
		Position p = position.clone();
		
		Vector out = v.clone();
		
		float divisor = 1;
		boolean updated = false;
		do{
			updated = false;
			
			if(out.getDx() != 0){
				try{if(g.getTile(g.getGridPositionOn((int) (p.getX() - speed/divisor), p.getY())).getTileType().isCollidable && out.getDx() < 0){updated = true; out.setDx(-speed/divisor);}}
				catch(Exception e){out.setDx(0);}
			
				try{if(g.getTile(g.getGridPositionOn((int) (p.getX() + speed/divisor), p.getY())).getTileType().isCollidable && out.getDx() > 0){updated = true; out.setDx(speed/divisor);}}
				catch(Exception e){out.setDx(0);}
			}
			
			if(out.getDy() != 0){
				try{if(g.getTile(g.getGridPositionOn(p.getX(), (int) (p.getY() - speed/divisor))).getTileType().isCollidable && out.getDx() > 0){updated = true; out.setDy(-speed/divisor);}}
				catch(Exception e){out.setDy(0);}
				
				try{if(g.getTile(g.getGridPositionOn(p.getX(), (int) (p.getY() + speed/divisor))).getTileType().isCollidable && out.getDx() < 0){updated = true; out.setDy(speed/divisor);}}
				catch(Exception e){out.setDy(0);}
			}
			
			divisor += 0.5f;
			
		}while(updated && divisor <= speed);
		
		try{if(g.getTile(g.getGridPositionOn(p.getX() - speed, p.getY())).getTileType().isCollidable && out.getDx() < 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX() + speed, p.getY())).getTileType().isCollidable && out.getDx() > 0) out.setDx(0);}
		catch(Exception e){out.setDx(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX(), p.getY() - speed)).getTileType().isCollidable && out.getDx() > 0) out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		try{if(g.getTile(g.getGridPositionOn(p.getX(), p.getY() + speed)).getTileType().isCollidable && out.getDx() < 0) out.setDy(0);}
		catch(Exception e){out.setDy(0);}
		
		return out;
		
	}
	
	public void keyDown(KeyEvent arg0){
		switch (arg0.getKeyCode()) {
		case 37:
			queuedMovement.setDx(-speed);
			break;
		case 38:
			queuedMovement.setDy(-speed);
			break;
		case 39:
			queuedMovement.setDx(speed);
			break;
		case 40:
			queuedMovement.setDy(speed);
			break;
		}
	}
	
	public void keyUp(KeyEvent arg0){
		switch (arg0.getKeyCode()) {
		case 37:
			queuedMovement.setDx(0);
			break;
		case 38:
			queuedMovement.setDy(0);
			break;
		case 39:
			queuedMovement.setDx(0);
			break;
		case 40:
			queuedMovement.setDy(0);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		keyDown(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyUp(arg0);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		keyDown(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}
