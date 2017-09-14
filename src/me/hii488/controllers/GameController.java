package me.hii488.controllers;

import java.util.Random;

import me.hii488.graphics.Window;
import me.hii488.objects.entities.Player;
import me.hii488.registries.EntityRegistry;

public class GameController {
	
	public static boolean isRunning = true;
	public static boolean isPaused = false;
	
	public static Window[] windows; // Goal is to modify containers to allow for multiple windows.
	public static Random rand = new Random();
	
	
	public static void startGame(String windowTitle, int windowWidth, int windowHeight){
		startGame(new Window(windowTitle, windowWidth, windowHeight));
	}
	
	public static void startGame(Window w){
		windows[0] = w;
		InitilisationController.preInit();
		
		if(EntityRegistry.player == null){
			System.err.println("No player set, defaulting to base.");
			EntityRegistry.player = new Player();
		}
		
		InitilisationController.init();
		InitilisationController.postInit();
		
		TickController.start();
		
		windows[0].createDisplay();
		windows[0].start();
	}
	
	public static void closeGame(){}
	
}
