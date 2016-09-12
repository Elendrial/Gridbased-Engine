package me.hii488.gameWorld.baseTypes;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.tickControl.ITickable;
import me.hii488.general.Position;
import me.hii488.objects.entities.GeneralEntity;

public class GeneralWorldContainer implements ITickable {

	public int ID;
	public Grid grid = new Grid();
	public boolean loaded = false;
	protected ArrayList<GeneralEntity> entities = new ArrayList<GeneralEntity>();
	
	public Position positionOffset;
	
	protected boolean setup = false;
	
	public void setup() {
		this.ID = World.Containers.getUnusedID();
		setup = true;
	}

	public void destroy() {

	}

	public void updateOnTick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).updateOnTick();
		}
		grid.updateOnTick();
	}

	public void updateOnSec() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).updateOnSec();
		}
		grid.updateOnSec();
	}

	public void onLoad() {
		positionOffset = new Position((World.mainWindow.width / 2) - (grid.gridSize[0] * (grid.tileSize/2)),	World.mainWindow.height / 2 - (grid.gridSize[1] * (grid.tileSize/2)));
		grid.positionGrid(positionOffset);
		grid.onLoad();
		tickEndCleanup();
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).onLoad();
		}
	}
	
	public void onUnLoad(){}

	
	public void render(Graphics g) {
		grid.render(g);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	
	public boolean isSetup() {
		return setup;
	}
	

	public ArrayList<GeneralEntity> destroyedInTick = new ArrayList<GeneralEntity>();
	public ArrayList<GeneralEntity> addedInTick = new ArrayList<GeneralEntity>();
	
	public void tickEndCleanup(){
		this.entities.removeAll(destroyedInTick);
		destroyedInTick.clear();
		
		this.entities.addAll(addedInTick);
		for(GeneralEntity ge: addedInTick){
			ge.containerID = this.ID;
			ge.onLoad();
		}
		
		addedInTick.clear();
	}
	
	
	protected boolean alwaysTicks = false;
	protected float randTickChance = 0f;
	
	@Override
	public void updateOnRandTick() {} // Entities and tiles cannot be called from this method, as otherwise all tiles/entities will call have a "random" tick at the same time, rather than independently.

	@Override
	public boolean alwaysTicks() {return this.alwaysTicks;}

	@Override
	public float randTickChance() {return randTickChance;}
	
	
	
	public ArrayList<GeneralEntity> getEntities() {
		return entities;
	}
	
	public void addEntity(GeneralEntity e){
		addedInTick.add(e);
	}

	public ArrayList<GeneralEntity> getEntitiesInsideRect(Rectangle r){
		return getEntitiesInsideRect(r, false);
	}
	
	public Rectangle rect = new Rectangle(0,0,0,0);
	
	public ArrayList<GeneralEntity> getEntitiesInsideRect(Rectangle r, boolean strictlyWithin){
		ArrayList<GeneralEntity> ge = new ArrayList<GeneralEntity>();
		
		rect = r;
		
		if(!strictlyWithin)	for(GeneralEntity e : this.getEntities()){ if(e.collisionBox.intersects(r)) ge.add(e);}
		else for(GeneralEntity e : this.getEntities()){ if(e.collisionBox.x >= r.x  && e.collisionBox.y >= r.y && e.collisionBox.width < r.width && e.collisionBox.height < r.height) ge.add(e);}
		
		return ge;
	}
	
	public void clear(){
		this.addedInTick.clear();
		this.entities.clear();
		this.destroyedInTick.clear();
		this.setup = false;
	}

	public void keyPressed(KeyEvent arg0) {}

	public void keyReleased(KeyEvent arg0) {}

	public void keyTyped(KeyEvent arg0) {}

	public void mouseClicked(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}
}
