package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.interfaces.IInitiliser;

public class InitilisationController{
	
public static ArrayList<IInitiliser> initList = new ArrayList<IInitiliser>();

	public static void preInitAll(){
		for(IInitiliser i : initList) i.preInit();
	}
	
	public static void initAll(){
		for(IInitiliser i : initList) i.init();
	}
	
	public static void postInitAll(){
		for(IInitiliser i : initList) i.postInit();
	}
	
}
