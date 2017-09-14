package me.hii488.graphics;

import me.hii488.misc.Vector;

public class Camera {
	
	public static Vector cameraPosition = new Vector(0,0);
	public static float scale = 1;
	
	public static void moveTo(Vector v){
		cameraPosition.setLocation(v);
	}
	
	public static void smoothMoveTo(Vector v, int speed){
		//TODO
	}
	
}
