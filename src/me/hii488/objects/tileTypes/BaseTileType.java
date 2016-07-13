package me.hii488.objects.tileTypes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import me.hii488.general.Settings;
import me.hii488.general.TextureHelper;

public class BaseTileType {

	public boolean isCollidable;

	public int zLayer; // 0 = ground,

	public String textureName = "";
	public BufferedImage i;

	public boolean multipleStates = false;
	public int state = 0;
	
	public void onLoad() {
		
	}

	public void setup() {
		if (!multipleStates) {
			try {
				i = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\textures\\tiles\\" + textureName));
			} catch (Exception e) {
				try {
					TextureHelper.TextureNotFoundPrint(textureName, this.getClass());
					i = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\" + Settings.defaultTileTextureLocation));
				//	e.printStackTrace();
				} catch (Exception e1) {
					TextureHelper.TextureNotFoundPrint(Settings.defaultTileTextureLocation, this.getClass());
				}
			}
		} else {
			try {
				i = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\textures\\tiles\\"
						+ textureName.substring(0, textureName.length() - 4) + "_" + state
						+ textureName.substring(textureName.length() - 4)));
			} catch (Exception e) {
				try {
					TextureHelper.TextureNotFoundPrint(textureName + "_" + state, this.getClass());
					i = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\" + Settings.defaultTileTextureLocation));

				} catch (Exception e1) {
					TextureHelper.TextureNotFoundPrint(Settings.defaultTileTextureLocation, this.getClass());
				}
			}
		}
	}

	public void updateOnTick() {

	}

	public void updateOnSec() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(i, x, y, null);
	}
}
