package me.hii488.controllers;

import java.util.ArrayList;

import me.hii488.graphics.Camera;
import me.hii488.handlers.ContainerHandler;
import me.hii488.interfaces.ITickable;
import me.hii488.misc.Settings;
import me.hii488.objects.entities.BaseEntity;

public class TickController implements Runnable {
	public static ArrayList<ITickable> additionalEarlyTicking = new ArrayList<ITickable>();
	public static ArrayList<ITickable> additionalLateTicking = new ArrayList<ITickable>();
	
	public boolean endOfTick = false;
	public boolean stillRendering = false;
	public boolean renderWaiting = false;
	public boolean tickWaiting = false;
	
	public static void updateTickableOnTick(){
		// Early tickers tick first
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnTick();
		
		// Tick the loaded container now.
		ContainerHandler.getLoadedContainer().updateOnTick();
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnTick();
	}
	
	public static void updateTickableOnSec(){
		for(ITickable toTick: additionalEarlyTicking) toTick.updateOnSec();
		
		// Tick the loaded container now.
		ContainerHandler.getLoadedContainer().updateOnSec();
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) toTick.updateOnSec();
	}
	
	public static void updateTickableOnRandTick(){
		for(ITickable toTick: additionalEarlyTicking) if(GameController.rand.nextFloat() < toTick.randTickChance()) toTick.updateOnRandTick();
		
		// Tick the loaded container now.
		if(GameController.rand.nextFloat() < ContainerHandler.getLoadedContainer().randTickChance()) ContainerHandler.getLoadedContainer().updateOnRandTick();
		for(BaseEntity ge : ContainerHandler.getLoadedContainer().getEntities()) if(GameController.rand.nextFloat() < ge.randTickChance()) ge.updateOnRandTick();
		
		// TICK ADDITIONAL AFTER
		for(ITickable toTick: additionalLateTicking) if(GameController.rand.nextFloat() < toTick.randTickChance()) toTick.updateOnTick();
	}

	
	
	public synchronized void tickClearup(){
		endOfTick = true;
		ContainerHandler.getLoadedContainer().endOfTick();
		Camera.update();
		endOfTick = false;
		if(renderWaiting) notifyAll();
	}
	
	public synchronized void okayToRender() {
		while(endOfTick) {
			try {
				renderWaiting = true;
				wait();
			} catch (InterruptedException e) {e.printStackTrace();}
			finally {renderWaiting = false;}
		}
	}
	
	
	
	
	public static void start(){
		new Thread(GameController.tickController).start();
	}
	
	
	public static int TPS = 0;
	public static int actualTPS;
	
	public static boolean isRunning = true;
	
	@Override
	public void run() {
		int tick = 0;
		
		int targetTPS = (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed);
		actualTPS = targetTPS;
		double fpsTimer = System.currentTimeMillis();
		double secondsPerTick = 1D / targetTPS;
		double nsPerTick = secondsPerTick * 1000000000D;
		double then = System.nanoTime();
		double now;
		double unprocessed = 0;
		
		while (isRunning && GameController.isRunning) {
			if(targetTPS != (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed)){
				targetTPS = (int) (Settings.WorldSettings.TargetTPS * Settings.WorldSettings.currentSpeed);
				nsPerTick = (1D / targetTPS) * 1000000000D;
				actualTPS = targetTPS;
				System.err.println("Target TPS changed to: " + targetTPS);
			}
			
			now = System.nanoTime();
			unprocessed += (now - then) / nsPerTick;
			then = now;
			while (unprocessed >= 1 && ((unprocessed < actualTPS) /*&& Settings.WorldSettings.debug) || !Settings.WorldSettings.debug*/)) {
				if(!GameController.isPaused){
					try{updateTickableOnTick();}catch(Exception e){e.printStackTrace();}
					try{updateTickableOnRandTick();}catch(Exception e){e.printStackTrace();}
					try{tickClearup();}catch(Exception e){e.printStackTrace();}
				}
				tick++;
				unprocessed--;
			}
			
			if(unprocessed >= actualTPS) unprocessed = 0;
			
			// If the current time is 1 second greater than the last time we printed
			if (System.currentTimeMillis() - fpsTimer >= 1000) {
				TPS = tick;
				tick = 0;
				fpsTimer += 1000;
				
				try{updateTickableOnSec();}catch(Exception e){e.printStackTrace();}
				try{tickClearup();} catch(Exception e){e.printStackTrace();}
				
				if(Settings.Logging.tpsPrinter) System.out.println("TPS: " + TPS);
				
				if(TPS > actualTPS * 2.5){
					then = System.nanoTime();
					actualTPS -= actualTPS * 0.05D;
					nsPerTick = (1D / actualTPS) * 1000000000D;
					System.err.println("Target TPS lowered to: " + actualTPS + " in order to avoid runaway.");
					System.err.println("Skipped " + (int)unprocessed + " in the process.");
					unprocessed = 0;
					fpsTimer = System.currentTimeMillis() + 1000;
				}
			}
			
			// This is NOT to sleep, but to limit the game loop
			try { if(actualTPS > 500) Thread.sleep(1);
				  else Thread.sleep(10); 
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
}
