package me.hii488.helpers;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.hii488.gameWorld.World;
import me.hii488.objects.entities.GeneralEntity;

public class EntityHelper {
	
	@SuppressWarnings("unchecked")
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

	public static boolean isOutOfContainer(GeneralEntity generalEntity) {
		// TODO: This, again
		try{
		}
		catch(Exception e){
			return true;
		}
		
		return false;
	}
	
}
