package me.hii488.objects.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.baseTypes.Grid;
import me.hii488.general.Position;

public class Player extends GeneralEntity implements MouseListener, KeyListener {

	public boolean moveable = true;
	public int speed = 2;

	@Override
	public void setup() {
		super.setup();
	}

	@Override
	public void onLoad() {
		super.onLoad();
		position = new Position(World.mainWindow.width / 2 - currentTexture.getWidth() / 2,	World.mainWindow.height / 2 - currentTexture.getHeight() / 2);
	}

	@Override
	public void updateOnTick() {
		super.updateOnTick();
		if (moveable) {
			position.addPosition(allowedVector(queuedMovement));
		}
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawRect(position.getX(), position.getY(), currentTexture.getWidth(), currentTexture.getHeight());
		g.fillRect(position.getX()-1, position.getY(), 3, 3);
		g.fillRect(position.getX() - speed-1, position.getY()-1, 3, 3);
		g.fillRect((int) (position.getX() + speed-1 + collisionBox.getWidth()), position.getY()-1, 3, 3);
		g.fillRect(position.getX()-1, (int) (position.getY() - speed)-1, 3, 3);
		g.fillRect(position.getX()-1, (int) (position.getY() + speed + collisionBox.getHeight())-1, 3, 3);
	}

	protected Position allowedVector(Position v) {
		Grid g = World.worldContainers.get(World.currentWorldContainerID).grid;
		Position p = position.clone();
		
		Position out = v.clone();
				
		float divisor = 1;
		boolean updated = false;
		do{
			updated = false;
			
			if(out.getAbsX() != 0){
				try{if(out.getAbsX() < 0)if(g.getTileAtAbsPosition((int) (p.getX() - speed/divisor), p.getY()).isCollidable){updated = true; out.setX(-speed/divisor);}}
				catch(Exception e){out.setX(0);}
			
				try{if(out.getAbsX() > 0)if(g.getTileAtAbsPosition((int) (p.getX() + speed/divisor + collisionBox.getWidth()), p.getY()).isCollidable){updated = true; out.setX(speed/divisor);}}
				catch(Exception e){out.setX(0);}
			}
			
			if(out.getY() != 0){
				try{if(out.getAbsY() > 0)if(g.getTileAtAbsPosition(p.getX(), (int) (p.getY() - speed/divisor)).isCollidable){updated = true; out.setY(-speed/divisor);}}
				catch(Exception e){out.setY(0);}
				
				try{if(out.getAbsY() < 0)if(g.getTileAtAbsPosition(p.getX(), (int) (p.getY() + speed/divisor + collisionBox.getHeight())).isCollidable){updated = true; out.setY(speed/divisor);}}
				catch(Exception e){out.setY(0);}
			}
			
			divisor += 0.5f;
			
		}while(updated && divisor <= speed);
		
		try{if(g.getTileAtAbsPosition(p.getX() - speed, p.getY()).isCollidable && out.getAbsX() < 0) out.setX(0);}
		catch(Exception e){out.setX(0);}
		
		try{if(g.getTileAtAbsPosition(p.getX() + speed, p.getY()).isCollidable && out.getAbsX() > 0) out.setX(0);}
		catch(Exception e){out.setX(0);}
		
		try{if(g.getTileAtAbsPosition(p.getX(), p.getY() - speed).isCollidable && out.getAbsX() > 0) out.setY(0);}
		catch(Exception e){out.setY(0);}
		
		try{if(g.getTileAtAbsPosition(p.getX(), p.getY() + speed).isCollidable && out.getAbsX() < 0) out.setY(0);}
		catch(Exception e){out.setY(0);}
		
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
