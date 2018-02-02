package me.hii488.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.hii488.misc.Settings;
import me.hii488.objects.containers.BaseContainer;
import me.hii488.objects.entities.BaseEntity;


public class EntityHandler {
	
	public static ArrayList<BaseEntity> getCollidingEntities(BaseEntity toCheck){
		ArrayList<BaseEntity> all = (ArrayList<BaseEntity>) ContainerHandler.getLoadedContainer().getEntities();
		
		ArrayList<BaseEntity> collidingWith = new ArrayList<BaseEntity>();
		
		for(BaseEntity e : all){
			if(e.collisionBox.intersects(toCheck.collisionBox)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}
	
	public static ArrayList<BaseEntity> getEntitiesIntersectingWithRect(Rectangle r){
		ArrayList<BaseEntity> all = (ArrayList<BaseEntity>) ContainerHandler.getLoadedContainer().getEntities();
		
		ArrayList<BaseEntity> collidingWith = new ArrayList<BaseEntity>();
		
		for(BaseEntity e : all){
			if(e.collisionBox.intersects(r)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}

	public static boolean isOutOfContainer(BaseEntity e) {
		if(e.position.getAbsX() < 0 || e.position.getAbsY() < 0) return true;
		
		BaseContainer c = ContainerHandler.getContainer(e.containerIdentifier);
		if(e.position.getX() > c.grid.dimensions.getX() * Settings.Texture.tileSize || e.position.getY() > c.grid.dimensions.getY() * Settings.Texture.tileSize) return true;
		
		return false;
	}
	
	public static ArrayList<BaseEntity> deepClone(ArrayList<BaseEntity> in){
		ArrayList<BaseEntity> out = new ArrayList<BaseEntity>();
		
		for(BaseEntity e : in) {
			out.add((BaseEntity) e.clone());
		}
		
		return out;
	}
}
