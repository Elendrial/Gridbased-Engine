package me.hii488.controllers;

import java.util.Random;

import me.hii488.graphics.Window;
import me.hii488.objects.entities.Player;
import me.hii488.objects.tiles.BlankTile;
import me.hii488.registries.EntityRegistry;

public class GameController {
	
	public static boolean isRunning = true;
	public static boolean isPaused = false;
	
	public static Random rand = new Random();
	
	public static void setupEngine(){ //This may grow, it may not
		new BlankTile();
	}
	
	public static void loadWindow(String windowTitle, int windowWidth, int windowHeight){
		loadWindow(new Window(windowTitle, windowWidth, windowHeight));
	}
	
	public static void loadWindow(Window w){ // Separate this <- current me: separate what? What do you mean past self?! What do you meeaaannnn???!?
		UpdateController.renderController.window = w;
	}
	
	public static void startGame(){
		InitialisationController.preInitAll();
		
		if(EntityRegistry.player == null){
			System.err.println("No player set, defaulting to base.");
			EntityRegistry.player = new Player();
		}
		
		InitialisationController.initAll();
		
		InitialisationController.postInitAll();
		
		getWindow().createDisplay();
		UpdateController.start();
	}
	
	public static void closeGame(){
		isRunning = false;
	}
	
	public static Window getWindow(){
		return UpdateController.renderController.window;
	}
	
}
