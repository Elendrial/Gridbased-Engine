package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.interfaces.IInitiliser;
import me.hii488.objects.tiles.BlankTile;

public class InitilisationController implements IInitiliser{
	
public static ArrayList<IInitiliser> initList = new ArrayList<IInitiliser>();
	
	public static void addSelf(){
		initList.add(new InitilisationController());
	}

	public static void preInitAll(){
		for(IInitiliser i : initList) i.preInit();
	}
	
	public static void initAll(){
		for(IInitiliser i : initList) i.init();
	}
	
	public static void postInitAll(){
		for(IInitiliser i : initList) i.postInit();
	}

	@Override
	public void preInit() {
		new BlankTile();
	}

	@Override
	public void init() {}

	@Override
	public void postInit() {}
	
}
