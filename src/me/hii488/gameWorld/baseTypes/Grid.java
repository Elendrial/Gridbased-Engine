package me.hii488.gameWorld.baseTypes;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.hii488.general.Position;
import me.hii488.objects.tileTypes.BaseTile;

public class Grid {

	public BaseTile[][] grid;
	public int gridSize[] = {0,0}; // 0 = x, 1 = y
	public Position metaPosition;
	public int tileSize = 16; // Height and width of tiles.
	
	public Grid() {}

	public Grid(Grid g) {
		this.gridSize = g.gridSize;
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				setTile(g.getTile(i, j), i, j);
			}
		}
	}

	public void setupGrid(int size) {
		gridSize[0] = size; gridSize[1] = size;
		grid = new BaseTile[size][size];
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				grid[i][j] = new BaseTile();
			}
		}
	}
	
	public void setupGrid(int sizeX, int sizeY) {
		gridSize[0] = sizeX; gridSize[1] = sizeY;
		grid = new BaseTile[sizeX][sizeY];
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				grid[i][j] = new BaseTile();
			}
		}
	}

	public void positionGrid(Position start) {
		metaPosition = start;
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).gridPosition = (new Position(start.getAbsX() + i * 16, start.getAbsY() + j * 16));
			}
		}
	}

	public void onLoad() {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).onLoad();
				// System.out.println(this.getTile(i,
				// j).getTileType().getClass().getName());
			}
		}
	}

	public void updateOnTick() {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).updateOnTick();
			}
		}
	}

	public void updateOnSec() {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).updateOnSec();
			}
		}
	}

	public BaseTile getTile(int x, int y) {
		try{
			return grid[x][y];
		}
		catch(Exception e){
			return null;
		}
	}
	
	public BaseTile getTile(Position p) {
		try{
			return grid[p.getX()][p.getY()];
		}
		catch(Exception e){
			return null;
		}
	}

	public void setTile(BaseTile tile, Position p) {
		this.setTile(tile, p.getX(), p.getY());
	}
	
	public void setTile(BaseTile tile, int x, int y) {
		grid[x][y] = tile;
		this.getTile(x, y).setup();
	}

	public void fillRectWithTile(BaseTile tile, int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				this.setTile(tile, i, j);
				this.getTile(i, j).setup();
			}
		}
	}

	public Grid clone() {
		return new Grid(this);
	}

	public void render(Graphics g) {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				getTile(i, j).render(g, tileSize);
			}
		}
	}

	public Position getGridPositionOn(Position p) {
		Position out = p.clone();

		out.setX(Math.round((out.getAbsX() - metaPosition.getAbsX()) / 16));
		out.setY(Math.round((out.getAbsY() - metaPosition.getAbsY()) / 16));

		return out;
	}
	
	public Position getPositionFromTileCoords(Position p){
		Position out = p.clone();
		
		out.setX(out.getAbsX() * 16 + metaPosition.getAbsX());
		out.setY(out.getAbsY() * 16 + metaPosition.getAbsY());
		
		return out;
	}

	public Position getGridPositionOn(int x, int y) {
		Position out = new Position(x, y);

		out.setX(Math.round((out.getAbsX() - metaPosition.getAbsX()) / 16));
		out.setY(Math.round((out.getAbsY() - metaPosition.getAbsY()) / 16));

		return out;
	}
}
