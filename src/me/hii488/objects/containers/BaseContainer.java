package me.hii488.objects.containers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import me.hii488.controllers.GameController;
import me.hii488.graphics.Camera;
import me.hii488.graphics.GUI.GUI;
import me.hii488.interfaces.IInputUser;
import me.hii488.interfaces.ITickable;
import me.hii488.misc.Grid;
import me.hii488.misc.Settings;
import me.hii488.misc.Vector;
import me.hii488.objects.entities.BaseEntity;

public class BaseContainer implements ITickable, IInputUser{
	
	protected ArrayList<BaseEntity> entitiesDestroyedInTick = new ArrayList<BaseEntity>();
	protected ArrayList<BaseEntity> entitiesAddedInTick = new ArrayList<BaseEntity>();
	protected ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>();
	public ArrayList<GUI> guis = new ArrayList<GUI>();
	public Grid grid = new Grid();
	public boolean loaded = false;
	public String identifier;
	
	
	public BaseContainer(){}
	
	public void onLoad(){
		if(grid.dimensions.getX() < GameController.windows[0].width && grid.dimensions.getY() < GameController.windows[0].height){
			Camera.moveTo(new Vector(-(GameController.windows[0].width / 2 - grid.dimensions.getX() * (Settings.Texture.tileSize*Camera.scale/2)),
									 -(GameController.windows[0].height/ 2 - grid.dimensions.getY() * (Settings.Texture.tileSize*Camera.scale/2))));
		}
		endOfTick();
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).onLoad();
		}
		
		if(Settings.Logging.debug) grid.printInfo();
	}
	
	public void onUnload(){}
	
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
	
	public void updateOnRandTick() {}
	
	@Override
	public float randTickChance() {return 0;}
	
	public void endOfTick(){
		
		this.entities.addAll(entitiesAddedInTick);
		for(BaseEntity ge: entitiesAddedInTick){
			ge.containerIdentifier = this.identifier;
			ge.onLoad();
		}
		
		this.entities.removeAll(entitiesDestroyedInTick);
		
		entitiesAddedInTick.clear();
		entitiesDestroyedInTick.clear();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		for(GUI gui : guis) gui.mouseClicked(arg0);
	}
	
	public void keyTyped(KeyEvent arg0){
		for(GUI gui : guis) gui.keyTyped(arg0);
	}
	
	public ArrayList<BaseEntity> getEntities() {
		return entities;
	}
	
	public void addEntity(BaseEntity e){
		e.containerIdentifier = identifier;
		entitiesAddedInTick.add(e);
	}
	
	public void removeEntity(BaseEntity e){
		entitiesDestroyedInTick.add(e);
	}
	
	public void render(Graphics g) {
		grid.render(g);
		for (BaseEntity e : entities) e.render(g);
		for(GUI gui : guis) gui.render(g);
	}
}
