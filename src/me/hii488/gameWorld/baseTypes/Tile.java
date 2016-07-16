package me.hii488.gameWorld.baseTypes;

import java.awt.Graphics;

import me.hii488.general.Position;
import me.hii488.objects.tileTypes.BaseTileType;

public class Tile {

	protected BaseTileType tileType;
	public Position position;

	public Tile(BaseTileType tileType) {
		this.tileType = tileType;
	}

	public BaseTileType getTileType() {
		return tileType;
	}

	public void setTileType(BaseTileType tileType) {
		this.tileType = tileType;
	}

	public void setup() {
		tileType.setup();
	}

	public void onLoad() {
		tileType.onLoad();
	}

	public void updateOnTick() {
		tileType.updateOnTick();
	}

	public void updateOnSec() {
		tileType.updateOnSec();
	}

	public void render(Graphics g) {
		tileType.render(g, position.getX(), position.getY());
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Tile clone() {
		Tile t = new Tile(this.tileType);
		t.position = this.position;
		return t;
	}

}
