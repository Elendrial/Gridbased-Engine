package me.hii488.registries;

import java.util.HashMap;

import me.hii488.objects.tiles.BaseTile;
import me.hii488.objects.tiles.BlankTile;

public class TileRegistry{
	
	protected static HashMap<String, BaseTile> tiles = new HashMap<String, BaseTile>();
	
	public static void registerTile(BaseTile b){
		if(!tiles.containsValue(b)) tiles.put(b.identifier, b);
	}
	
	public static BlankTile getBlankTile(){
		return (BlankTile) tiles.get("blankTile").clone();
	}
	
	public static BaseTile getTile(String identifier){
		return (BaseTile) tiles.get(identifier).clone();
	}
	
	public static boolean contains(String identifier){
		return tiles.containsKey(identifier);
	}
	
}
