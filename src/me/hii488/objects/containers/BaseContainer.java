package me.hii488.objects.containers;

import java.awt.Graphics;
import java.util.ArrayList;

import me.hii488.Interfaces.ITickable;
import me.hii488.misc.Grid;
import me.hii488.objects.entities.BaseEntity;

public abstract class BaseContainer implements ITickable{
	
	protected ArrayList<BaseEntity> entitiesDestroyedInTick = new ArrayList<BaseEntity>();
	protected ArrayList<BaseEntity> entitiesAddedInTick = new ArrayList<BaseEntity>();
	protected ArrayList<BaseEntity> entities = new ArrayList<BaseEntity>();
	public Grid grid = new Grid();
	public boolean loaded = false;
	public String identifier;
	
	
	public BaseContainer(){}
	
	public abstract void onLoad();
	public abstract void onUnload();
	
	public void endOfTick(){
		this.entities.removeAll(entitiesDestroyedInTick);
		entitiesDestroyedInTick.clear();
		
		this.entities.addAll(entitiesAddedInTick);
		for(BaseEntity ge: entitiesAddedInTick){
			ge.containerIdentifier = this.identifier;
			ge.onLoad();
		}
		
		entitiesAddedInTick.clear();
	}
	
	public ArrayList<BaseEntity> getEntities() {
		return entities;
	}
	
	public void addEntity(BaseEntity e){
		entitiesAddedInTick.add(e);
	}
	
	public void removeEntity(BaseEntity e){
		entitiesDestroyedInTick.add(e);
	}

	public void render(Graphics g) {
		grid.render(g);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}
}
