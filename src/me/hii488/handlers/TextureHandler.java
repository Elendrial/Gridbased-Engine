package me.hii488.handlers;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import me.hii488.misc.Settings;
import me.hii488.objects.entities.BaseEntity;

public class TextureHandler {

	public static ArrayList<String> lostTextures = new ArrayList<String>();
	private static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	
	static {
		try {
			BufferedImage i = ImageIO.read(TextureHandler.class.getClassLoader().getResourceAsStream(Settings.Texture.defaultTileTextureLocation));
			textures.put("undefined", i);
		} catch (IOException e) {
			System.err.println("Default textures not found, something wrong, exiting.");
			System.exit(1);
		}
		
	}

	private static void TextureNotFound(String s, Class<?> c, boolean print) {
		if (!lostTextures.contains(s)) {
			lostTextures.add(s);
			if(print) System.err.println("Texture Not Found : " + s + "\n\t       in : " + c.getName());
		}
	}

	public static void PrintLostAllTextures() {
		for (int i = 0; i < lostTextures.size(); i++) {
			System.err.println("Textures Not Found : " + lostTextures.get(i));
		}
	}
	
	public static void loadTexture(String path, String imageName, Object obj, String key){
		if(textures.containsKey(key)) return;
		BufferedImage i = null;
		try {
			i = ImageIO.read(TextureHandler.class.getClassLoader().getResourceAsStream(path + imageName));
		} catch (Exception e) {
			try {
				TextureNotFound(path + imageName, obj.getClass(), true);
				i = ImageIO.read(TextureHandler.class.getClassLoader().getResourceAsStream((obj instanceof BaseEntity) ? Settings.Texture.defaultEntityTextureLocation : Settings.Texture.defaultTileTextureLocation));
			} catch (Exception e1) {
				System.err.println("Default textures not found, something seriously wrong, exiting.");
				System.exit(1);
			}
		}
		
		if(Settings.Logging.debug) System.out.println("Loading texture: \"" + key + "\" from \"" + path + imageName + "\"");
		
		textures.put(key, i);
	}
	
	public static void loadTextureSet(String path, String imageName, Object obj, String key, int states){
		String[] im = imageName.split("\\.");
		int len = im.length;
		if(len >= 2) for(int i = 0; i < states; i++) loadTexture(path, im[len-2] + "_" + i + "." + im[len-1], obj, key + "_" + i);
		else for(int i = 0; i < states; i++) loadTexture(path, imageName + "_" + i, obj, key + "_" + i);
	}
	
	public static BufferedImage getTexture(String key){
		if(textures.containsKey(key)) return textures.get(key);
		System.out.println("Texture \"" + key + "\" not loaded");
		return textures.get("undefined");
	}
	
	public static void addTexture(BufferedImage i, String key) {
		textures.put(key, i);
	}
	
	public static boolean containsTexture(String key) {
		return textures.containsKey(key);
	}
	
	public static BufferedImage cloneTexture(BufferedImage bi) {
		 ColorModel cm = bi.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = bi.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
}
