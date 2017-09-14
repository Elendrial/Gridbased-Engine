package me.hii488.controllers;

import java.util.Random;

import me.hii488.graphics.Window;
import me.hii488.objects.entities.Player;
import me.hii488.objects.tiles.BlankTile;
import me.hii488.registries.EntityRegistry;

public class GameController {
	
	public static boolean isRunning = true;
	public static boolean isPaused = false;
	
	public static Window[] windows = new Window[1]; // Goal is to modify containers to allow for multiple windows.
	public static Random rand = new Random();
	
	public static void setupEngine(){ //This may grow, it may not
		new BlankTile();
	}
	
	public static void loadWindow(String windowTitle, int windowWidth, int windowHeight){
		loadWindow(new Window(windowTitle, windowWidth, windowHeight));
	}
	
	public static void loadWindow(Window w){ // seperate this
		windows[0] = w;
	}
	
	public static void startGame(){
		InitilisationController.preInitAll();
		
		if(EntityRegistry.player == null){
			System.err.println("No player set, defaulting to base.");
			EntityRegistry.player = new Player();
		}
		
		InitilisationController.initAll();
		
		TickController.start();
		
		InitilisationController.postInitAll();
		
		windows[0].createDisplay();
		windows[0].start();
	}
	
	public static void closeGame(){}
	
}
