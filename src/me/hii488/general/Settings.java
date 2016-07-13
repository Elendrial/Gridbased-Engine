package me.hii488.general;

public class Settings {

	public static class WorldSettings {
		public static int TargetTPS = 30;
		public static float currentSpeed = 1;
	}

	// Currently unused
	public static class CameraSettings {

		public boolean movable = true;

		public static float minZoom = 0.01f;
		public static float maxZoom = 2f;
		
		public static Position currentPosition = new Position(0,0); // TODO: Add options for this

	}

	public static final String defaultTileTextureLocation = "textures\\somethingsgonewrong\\tdefault.png";
	public static final String defaultEntityTextureLocation = "textures\\somethingsgonewrong\\edefault.png";

	public static class Logging{
		// TODO : This.
	}
	
}
