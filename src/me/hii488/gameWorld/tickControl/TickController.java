package me.hii488.gameWorld.tickControl;

import java.util.ArrayList;
import java.util.Map;

import me.hii488.gameWorld.World;
import me.hii488.gameWorld.baseTypes.GeneralWorldContainer;
import me.hii488.general.Settings;
import me.hii488.objects.entities.GeneralEntity;

public class TickController implements Runnable{
	
	public static ArrayList<ITickable> additionalEarlyTicking = new ArrayList<ITickable>();
	public static ArrayList<ITickable> additionalLateTicking = new ArrayList<ITickable>();
	
	public static void updateTickableOnTick(){
		// Early tickers tick first
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnTick();
		
		// Tick the loaded container now.
		World.getCurrentWorldContainer().updateOnTick();
		
		// Tick always ticking containers next.
		for (Map.Entry<Integer, GeneralWorldContainer> entry : World.worldContainers.entrySet()) {
			if(entry.getValue().alwaysTicks()) {if (entry.getKey() != World.currentWorldContainerID) { entry.getValue().updateOnTick();}} // Eclipse had a hissy fit at me if I combined the conditions :/
		}
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnTick();
	}
	
	public static void updateTickableOnSec(){
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnSec();
		
		// Tick the loaded container now.
		World.getCurrentWorldContainer().updateOnSec();
		
		// Tick always ticking containers next.
		for (Map.Entry<Integer, GeneralWorldContainer> entry : World.worldContainers.entrySet()) {
			if(entry.getValue().alwaysTicks()) {if (entry.getKey() != World.currentWorldContainerID) { entry.getValue().updateOnSec();}} // Eclipse had a hissy fit at me if I combined the conditions :/
		}
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnSec();
	}
	
	public static void updateTickableOnRandTick(){
		for(ITickable toTick: additionalEarlyTicking) if(World.rand.nextFloat() < toTick.randTickChance()) toTick.updateOnRandTick();
		
		// Tick the loaded container now.
		if(World.rand.nextFloat() < World.getCurrentWorldContainer().randTickChance())World.getCurrentWorldContainer().updateOnRandTick();
		for(GeneralEntity ge : World.getCurrentWorldContainer().getEntities())if(World.rand.nextFloat() < ge.randTickChance()) ge.updateOnRandTick();
		
		
		// Tick always ticking containers next.
		for (Map.Entry<Integer, GeneralWorldContainer> entry : World.worldContainers.entrySet()) {
			if(entry.getValue().alwaysTicks()) if (entry.getKey() != World.currentWorldContainerID) if(World.rand.nextFloat() < entry.getValue().randTickChance()) entry.getValue().updateOnRandTick(); // Eclipse had a hissy fit at me if I combined the conditions :/
			for(GeneralEntity ge : entry.getValue().getEntities()) if(World.rand.nextFloat() < ge.randTickChance()) ge.updateOnRandTick();
		}
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) if(World.rand.nextFloat() < toTick.randTickChance()) toTick.updateOnTick();
	}

	public static void tickClearup(){
		World.getCurrentWorldContainer().tickEndCleanup();
		
		for (Map.Entry<Integer, GeneralWorldContainer> entry : World.worldContainers.entrySet()) {
			if(entry.getValue().alwaysTicks()) {if (entry.getKey() != World.currentWorldContainerID) { entry.getValue().tickEndCleanup();}} // Eclipse had a hissy fit at me if I combined the conditions :/
		}
	}
	
	
	public static void start(){
		new Thread(new TickController()).start();
	}
	
	
	public static int TPS = 0;
	
	public static boolean isRunning = true;
	
	@Override
	public void run() {
		int tick = 0;
		
		int targetTPS = (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed);
		
		double fpsTimer = System.currentTimeMillis();
		double secondsPerTick = 1D / targetTPS;
		double nsPerTick = secondsPerTick * 1000000000D;
		double then = System.nanoTime();
		double now;
		double unprocessed = 0;
		
		while (isRunning && World.isRunning) {
			if(targetTPS != (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed)){
				targetTPS = (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed);
				nsPerTick = (1D / targetTPS) * 1000000000D;
			}
			
			now = System.nanoTime();
			unprocessed += (now - then) / nsPerTick;
			then = now;
			while (unprocessed >= 1) {
				updateTickableOnTick();
				updateTickableOnRandTick();
				tickClearup();
				tick++;
				unprocessed--;
			}

			// If the current time is 1 second greater than the last time we printed
			if (System.currentTimeMillis() - fpsTimer >= 1000) {
				TPS = tick;
				tick = 0;
				fpsTimer += 1000;
				updateTickableOnSec();
				tickClearup();
			}
			
			// This is NOT to sleep, but to limit the game loop
			try { Thread.sleep(1);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
	
}
