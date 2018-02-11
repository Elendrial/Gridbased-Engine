package me.hii488.registries;

import java.util.HashMap;

import me.hii488.objects.entities.BaseEntity;
import me.hii488.objects.entities.Player;

public class EntityRegistry {
	public static Player player;
	
	protected static HashMap<String, BaseEntity> entities = new HashMap<String, BaseEntity>();
	
	public static void registerEntity(BaseEntity b){
		if(!entities.containsValue(b)) entities.put(b.identifier, b);
	}
	
	public static BaseEntity getEntity(String identifier){
		return (BaseEntity) entities.get(identifier).clone();
	}
	
	public static boolean contains(String identifier){
		return entities.containsKey(identifier);
	}
}
