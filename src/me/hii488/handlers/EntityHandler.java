package me.hii488.handlers;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.hii488.objects.entities.BaseEntity;


public class EntityHandler {
	@SuppressWarnings("unchecked")
	public static ArrayList<BaseEntity> getCollidingEntities(BaseEntity toCheck){
		ArrayList<BaseEntity> all = (ArrayList<BaseEntity>) ContainerHandler.getLoadedContainer().getEntities().clone();
		
		ArrayList<BaseEntity> collidingWith = new ArrayList<BaseEntity>();
		
		for(BaseEntity e : all){
			if(e.collisionBox.intersects(toCheck.collisionBox)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<BaseEntity> getEntitiesIntersectingWithRect(Rectangle r){
		ArrayList<BaseEntity> all = (ArrayList<BaseEntity>) ContainerHandler.getLoadedContainer().getEntities().clone();
		
		ArrayList<BaseEntity> collidingWith = new ArrayList<BaseEntity>();
		
		for(BaseEntity e : all){
			if(e.collisionBox.intersects(r)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}

	public static boolean isOutOfContainer(BaseEntity generalEntity) {
		// TODO: This, again
		try{
		}
		catch(Exception e){
			return true;
		}
		
		return false;
	}
}
