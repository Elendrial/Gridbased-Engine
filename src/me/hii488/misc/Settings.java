package me.hii488.misc;

public class Settings {

	public static class WorldSettings {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;
		public static boolean debug = false;	
	}

	// Currently unused
	public static class CameraSettings {

		public boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
		public static Vector currentPosition = new Vector(0,0);

	}

	public static class Logging{
		// TODO : This.
		public static boolean debug = false;
		public static boolean tpsPrinter = false;
	}

	public static class Texture{
		public static final String defaultTileTextureLocation = "textures/somethingsgonewrong/tdefault.png";
		public static final String defaultEntityTextureLocation = "textures/somethingsgonewrong/edefault.png";
		public static int tileSize = 16;
	}
	
}
