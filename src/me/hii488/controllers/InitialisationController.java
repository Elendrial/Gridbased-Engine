package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.interfaces.IInitialiser;

public class InitialisationController{
	
	protected static ArrayList<IInitialiser> initList = new ArrayList<IInitialiser>();

	public static void preInitAll(){
		for(IInitialiser i : initList) i.preInit();
	}
	
	public static void initAll(){
		for(IInitialiser i : initList) i.init();
	}
	
	public static void postInitAll(){
		for(IInitialiser i : initList) i.postInit();
	}

	public static void addInitialiser(IInitialiser i) {
		initList.add(i);
	}
	
}
