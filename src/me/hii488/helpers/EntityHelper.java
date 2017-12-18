package me.hii488.helpers;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.baseTypes.GeneralWorldContainer;
import me.hii488.objects.entities.GeneralEntity;

public class EntityHelper {
	
	@SuppressWarnings("unchecked")
	@Deprecated
	public static ArrayList<GeneralEntity> getCollidingEntities(GeneralEntity toCheck){
		ArrayList<GeneralEntity> all = (ArrayList<GeneralEntity>) World.getCurrentWorldContainer().getEntities().clone();
		
		ArrayList<GeneralEntity> collidingWith = new ArrayList<GeneralEntity>();
		
		for(GeneralEntity e : all){
			if(e.collisionBox.intersects(toCheck.collisionBox)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}
	
	public static ArrayList<GeneralEntity> getCollidingEntities(GeneralEntity toCheck, GeneralWorldContainer container){
		@SuppressWarnings("unchecked")
		ArrayList<GeneralEntity> all = (ArrayList<GeneralEntity>) container.getEntities().clone();
		
		ArrayList<GeneralEntity> collidingWith = new ArrayList<GeneralEntity>();
		
		for(GeneralEntity e : all){
			if(e.collisionBox.intersects(toCheck.collisionBox)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<GeneralEntity> getEntitiesIntersectingWithRect(Rectangle r, GeneralWorldContainer container){
		ArrayList<GeneralEntity> all = (ArrayList<GeneralEntity>) container.getEntities().clone();
		
		ArrayList<GeneralEntity> collidingWith = new ArrayList<GeneralEntity>();
		
		for(GeneralEntity e : all){
			if(e.collisionBox.intersects(r)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}
	
	@Deprecated
	@SuppressWarnings("unchecked")
	public static ArrayList<GeneralEntity> getEntitiesIntersectingWithRect(Rectangle r){
		ArrayList<GeneralEntity> all = (ArrayList<GeneralEntity>) World.getCurrentWorldContainer().getEntities().clone();
		
		ArrayList<GeneralEntity> collidingWith = new ArrayList<GeneralEntity>();
		
		for(GeneralEntity e : all){
			if(e.collisionBox.intersects(r)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}

	@Deprecated
	public static boolean isOutOfContainer(GeneralEntity generalEntity) {
		// Easiest way to check if it's still on the grid :
		try{
			World.getCurrentWorldContainer().grid.getTile(World.getCurrentWorldContainer().grid.getGridPositionOn(generalEntity.position)).getTileType();
		}
		catch(Exception e){
			return true;
		}
		
		return false;
	}
	
	public static boolean isOutOfContainer(GeneralEntity generalEntity, GeneralWorldContainer container) {
		// Easiest way to check if it's still on the grid :
		try{
			container.grid.getTile(container.grid.getGridPositionOn(generalEntity.position)).getTileType();
		}
		catch(Exception e){
			return true;
		}
		
		return false;
	}
	
}
