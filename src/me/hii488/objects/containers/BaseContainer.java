package me.hii488.objects.containers;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import me.hii488.controllers.GameController;
import me.hii488.graphics.Camera;
import me.hii488.graphics.GUI.GUI;
import me.hii488.handlers.ContainerHandler;
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
		if(grid.dimensions.getX() * Settings.Texture.tileSize < GameController.getWindow().width && grid.dimensions.getY() * Settings.Texture.tileSize < GameController.getWindow().height){
			Camera.moveTo(new Vector(-(GameController.getWindow().width / 2 - grid.dimensions.getX() * (Settings.Texture.tileSize*Camera.scale/2)),
									 -(GameController.getWindow().height/ 2 - grid.dimensions.getY() * (Settings.Texture.tileSize*Camera.scale/2))));
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
		for(BaseEntity ge : entities) ge.endOfTick();
		
		entities.addAll(entitiesAddedInTick);
		
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
	
	public void keyPressed(KeyEvent arg0){
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
	
	public GUI getGui(String s){
		for(GUI g : guis) if(g.getIdentifier().equals(s)) return g;
		return null;
	}
	
	public void showEntities(boolean b){
		if(loaded) ContainerHandler.getRenderContainer().showEntities = b;
	}
	
	public void showGUIs(boolean b){
		if(loaded) ContainerHandler.getRenderContainer().showGUI = b;
	}
	
	public RenderContainer createRenderContainer(){
		RenderContainer rc = new RenderContainer();
		for(BaseEntity e : entities) rc.entities.add(e.createRenderEntity());
		rc.grid = grid.clone(); // TODO: Change this so only the to be rendered tiles are cloned?
		rc.guis = guis;
		return rc;
	}
}
