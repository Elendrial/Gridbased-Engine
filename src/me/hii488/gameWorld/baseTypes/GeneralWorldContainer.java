package me.hii488.gameWorld.baseTypes;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.World.Containers;
import me.hii488.general.Position;
import me.hii488.objects.entities.GeneralEntity;

public class GeneralWorldContainer {

	public boolean alwaysUpdate;
	public int ID;
	public Grid grid = new Grid();
	public boolean loaded = false;
	public ArrayList<GeneralEntity> entities = new ArrayList<GeneralEntity>();
	// TODO: Rename this
	public Position metaPosition;
	
	protected boolean setup = false;
	
	public void setup() {
		this.ID = World.Containers.getUnusedID();
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).setup();
		}
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

	// TODO : add this to base
	public void onLoad() {
		metaPosition = new Position((World.mainWindow.width / 2) - (grid.gridSize[0] * 8),	World.mainWindow.height / 2 - (grid.gridSize[1] * 8));
		grid.positionGrid(metaPosition);
		grid.onLoad();
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).onLoad();
		}
	}

	public void render(Graphics g) {
		grid.render(g);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	public boolean isSetup() {
		return setup;
	}
	
	
	public void addEntity(GeneralEntity e){
		entities.add(e);
		e.onLoad();
	}
	
}
