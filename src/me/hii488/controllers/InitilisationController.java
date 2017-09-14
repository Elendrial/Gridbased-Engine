package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.Interfaces.IInitiliser;

public class InitilisationController {
	
public static ArrayList<IInitiliser> initList = new ArrayList<IInitiliser>();
	
	public static void preInit(){
		for(IInitiliser i : initList) i.preInit();
	}
	
	public static void init(){
		for(IInitiliser i : initList) i.init();
	}
	
	public static void postInit(){
		for(IInitiliser i : initList) i.postInit();
	}
	
}
