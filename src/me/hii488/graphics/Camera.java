package me.hii488.graphics;

import me.hii488.misc.Vector;

public class Camera {
	
	private static Vector cameraPosition = new Vector(0,0);
	private static Vector oldPosition = new Vector(0,0);
	public static float scale = 1;
	
	public static void update() {
		oldPosition.setLocation(cameraPosition);
	}
	
	public static void moveTo(Vector v){
		cameraPosition.setLocation(v);
	}
	
	public static void smoothMoveTo(Vector v, int speed){
		//TODO: Smooth move
	}
	
	public static Vector getPosition() {
		return oldPosition.clone();
	}
	
}
