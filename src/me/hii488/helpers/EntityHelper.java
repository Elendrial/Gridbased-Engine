package me.hii488.helpers;

import java.util.ArrayList;

import me.hii488.gameWorld.World;
import me.hii488.objects.entities.GeneralEntity;

public class EntityHelper {
	
	@SuppressWarnings("unchecked")
	public static ArrayList<GeneralEntity> getCollidingEntities(GeneralEntity toCheck){
		ArrayList<GeneralEntity> all = (ArrayList<GeneralEntity>) World.getCurrentWorldContainer().entities.clone();
		
		ArrayList<GeneralEntity> collidingWith = new ArrayList<GeneralEntity>();
		
		for(GeneralEntity e : all){
			if(e.collisionBox.intersects(toCheck.collisionBox)){
				collidingWith.add(e);
			}
		}
		
		return collidingWith;
	}

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
	
}
