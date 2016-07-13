package me.hii488.gameWorld.tickControl;

public interface ITickable {
	
	public abstract boolean alwaysTicks();
	public abstract float randTickChance();
	
	public abstract void updateOnTick();
	public abstract void updateOnSec();
	public abstract void updateOnRandTick();
	
}
