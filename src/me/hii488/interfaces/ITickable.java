package me.hii488.interfaces;

public interface ITickable {
	public float randTickChance();
	public void updateOnTick();
	public void updateOnSec();
	public void updateOnRandTick();
	
	/* This isn't needed in most cases, and so has the default tag
	     to stop it appearing in places where it really isn't needed. */
	public default void endOfTick(){}
}
