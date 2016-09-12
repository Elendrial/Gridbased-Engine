package me.hii488.gameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Random;

import me.hii488.gameWindow.Window;
import me.hii488.gameWorld.Initialisation.WorldInitialisation;
import me.hii488.gameWorld.baseTypes.GeneralWorldContainer;
import me.hii488.gameWorld.tickControl.TickController;
import me.hii488.objects.entities.Player;

public class World {

	public static boolean isRunning = true;
	public static boolean isPaused = false;
	
	public static Window mainWindow;
	public static Player player;
	
	public static Random rand = new Random();

	public static void startGame(Window w){
		mainWindow = w;
		WorldInitialisation.preInitMainWorld();
		
		if(player == null){
			System.err.println("No player set, defaulting to base.");
			player = new Player();
		}
		
		player.setup();
		
		WorldInitialisation.initMainWorld();
		
		TickController.start();
		
		mainWindow.createDisplay();
		mainWindow.start();
	}
	
	public static void startGame(String windowTitle, int windowWidth, int windowHeight){
		startGame(new Window(windowTitle, windowWidth, windowHeight));
	}
	
	public static void closeGame(){
		TickController.isRunning = false;
		mainWindow.isRunning = false;
	}
	
	
	public static int currentWorldContainerID = 0;
	public static HashMap<Integer, GeneralWorldContainer> worldContainers = new HashMap<>();

	public static class Containers {
		public static void addContainer(GeneralWorldContainer gwc) {
			if(!gwc.isSetup()) gwc.setup();
			if (gwc.ID != -1) {
				worldContainers.put(gwc.ID, gwc);
			} else {
				gwc.destroy();
			}
		}

		public static void removeContainer(GeneralWorldContainer gwc) {
			removeContainer(gwc.ID);
		}
		
		public static void removeContainer(int ID) {
			if (worldContainers.containsKey(ID)) {
				worldContainers.remove(ID);
			}
		}

		public static int getUnusedID() {
			int id = -1;
			for (int i = 0; i <= worldContainers.size(); i++) {
				if (!worldContainers.containsKey(i)) {
					id = i;
				}
			}
			return id;
		}

		public static void loadNewContainer(GeneralWorldContainer gwc) {
			loadNewContainer(gwc.ID);
		}
		
		public static void loadNewContainer(int id) {
			if (containerExists(id)) {
				unloadCurrentContainer();
				currentWorldContainerID = id;
				worldContainers.get(currentWorldContainerID).addEntity(player);
				worldContainers.get(currentWorldContainerID).onLoad();

			}
		}

		public static void unloadCurrentContainer() {
			worldContainers.get(currentWorldContainerID).onUnLoad();
			worldContainers.get(currentWorldContainerID).loaded = false;
			worldContainers.get(currentWorldContainerID).getEntities().remove(player);
		}

		public static boolean containerExists(int id) {
			if (worldContainers.containsKey(id)) {
				return true;
			}
			return false;
		}

		/*
		public static void updateContainersOnTick() {
			for (int i = 0; i < worldContainers.size(); i++) {
				if (containerExists(i)) {
					if (worldContainers.get(i).alwaysUpdate || i == currentWorldContainerID) {
						worldContainers.get(i).updateOnTick();
					}
				}
			}
		}

		public static void updateContainersOnSec() {
			for (int i = 0; i < worldContainers.size(); i++) {
				if (containerExists(i)) {
					if (worldContainers.get(i).alwaysUpdate || i == currentWorldContainerID) {
						worldContainers.get(i).updateOnSec();
					}
				}
			}
		}
		 */
	}
	
	public static GeneralWorldContainer getCurrentWorldContainer(){
		return worldContainers.get(currentWorldContainerID);
	}
	
	public static InputHandling inputHandler = new InputHandling();
	private static class InputHandling implements MouseListener, KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			World.player.keyPressed(e);
			World.getCurrentWorldContainer().keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			World.player.keyReleased(e);
			World.getCurrentWorldContainer().keyReleased(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			World.player.keyTyped(e);
			World.getCurrentWorldContainer().keyTyped(e);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			World.player.mouseClicked(e);
			World.getCurrentWorldContainer().mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			World.player.mouseEntered(e);
			World.getCurrentWorldContainer().mouseEntered(e);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			World.player.mouseExited(e);
			World.getCurrentWorldContainer().mouseExited(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			World.player.mousePressed(e);
			World.getCurrentWorldContainer().mousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			World.player.mouseReleased(e);
			World.getCurrentWorldContainer().mouseReleased(e);
		}
		
	}
	
}
