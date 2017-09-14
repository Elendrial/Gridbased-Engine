package me.hii488.handlers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import me.hii488.misc.Settings;
import me.hii488.objects.entities.BaseEntity;

public class TextureHandler {

	public static ArrayList<String> lostTextures = new ArrayList<String>();
	private static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();

	public static void TextureNotFound(String s) {
		if (!lostTextures.contains(s))
			lostTextures.add(s);
	}

	public static void TextureNotFoundPrint(String s, Class<?> c) {
		if (!lostTextures.contains(s)) {
			lostTextures.add(s);
			System.err.println("Texture Not Found : " + s + "\n\t       in : " + c.getName());
		}
	}

	public static void TextureNotFoundPrint(String s) {
		if (!lostTextures.contains(s)) {
			lostTextures.add(s);
			System.err.println("Texture Not Found : " + s);
		}
	}

	public static void PrintLostAllTextures() {
		for (int i = 0; i < lostTextures.size(); i++) {
			System.err.println("Textures Not Found : " + lostTextures.get(i));
		}
	}
	
	public static BufferedImage loadTexture(String path, String imageName, Object obj){
		if(textures.containsKey(imageName)) return textures.get(imageName);
		BufferedImage i = null;
		try {
			i = ImageIO.read(TextureHandler.class.getClassLoader().getResourceAsStream(path + imageName));
		} catch (IOException e) {
			try {
				TextureHandler.TextureNotFoundPrint(imageName, obj.getClass());
				i = ImageIO.read(TextureHandler.class.getResourceAsStream((obj instanceof BaseEntity) ? Settings.Texture.defaultEntityTextureLocation : Settings.Texture.defaultTileTextureLocation));
			} catch (Exception e1) {
				TextureHandler.TextureNotFoundPrint(Settings.Texture.defaultTileTextureLocation, obj.getClass());
			}
		}
		textures.put(imageName, i);
		return textures.get(imageName);
	}
	
	public static BufferedImage getImage(String imageName){
		return textures.get(imageName);
	}
}
