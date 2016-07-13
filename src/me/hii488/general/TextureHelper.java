package me.hii488.general;

import java.util.ArrayList;

public class TextureHelper {

	public static ArrayList<String> lostTextures = new ArrayList<String>();

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

}
