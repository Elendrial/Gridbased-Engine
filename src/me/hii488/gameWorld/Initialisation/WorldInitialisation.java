package me.hii488.gameWorld.Initialisation;

import java.util.ArrayList;

public class WorldInitialisation {
	
	public static ArrayList<IInitilisation> initList = new ArrayList<IInitilisation>();
	
	public static void preInitMainWorld(){
		for(IInitilisation i : initList){
			i.preInit();
		}
	}
	
	public static void initMainWorld(){
		for(IInitilisation i : initList){
			i.init();
		}
	}
	
}
