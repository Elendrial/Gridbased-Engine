package me.hii488.gameWorld.baseTypes;

import java.awt.Graphics;

import me.hii488.general.Position;
import me.hii488.objects.tileTypes.BaseTile;

public class Grid {

	public BaseTile[][] grid;
	public int gridSize[] = {0,0}; // 0 = x, 1 = y
	public Position positionOffset;
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
		positionOffset = start;
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).gridPosition = (new Position(i, j));
				this.getTile(i, j).renderOffset = positionOffset;
			}
		}
	}

	public void onLoad() {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).onLoad();
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
		grid[x][y] = tile.clone();
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
	
	public void wallRectWithTile(BaseTile tile, int x1, int y1, int x2, int y2){
		for(int i = x1; i < x2; i++){
			this.setTile(tile, i, y1);
			this.setTile(tile, i, y2-1);
			
			this.getTile(i, y1).setup();
			this.getTile(i, y2-1).setup();
		}
		
		for(int i = y1; i < y2; i++){
			this.setTile(tile, x1, i);
			this.setTile(tile, x2-1, i);
			
			this.getTile(x1, i).setup();
			this.getTile(x2-1, i).setup();
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

	
	/*
	public Position getGridPositionOn(Position p) {
		Position out = p.clone();

		out.setX(Math.round((out.getAbsX() - positionOffset.getAbsX()) / tileSize));
		out.setY(Math.round((out.getAbsY() - positionOffset.getAbsY()) / tileSize));

		return out;
	}
	
	public Position getPositionFromTileCoords(Position p){
		Position out = p.clone();
		
		out.setX(out.getAbsX() * 16 + positionOffset.getAbsX());
		out.setY(out.getAbsY() * 16 + positionOffset.getAbsY());
		
		return out;
	}

	public Position getGridPositionOn(int x, int y) {
		Position out = new Position(x, y);

		out.setX(Math.round((out.getAbsX() - positionOffset.getAbsX()) / tileSize));
		out.setY(Math.round((out.getAbsY() - positionOffset.getAbsY()) / tileSize));

		return out;
	}
	*/
	
	public BaseTile getTileAtAbsPosition(int x, int y){
		return getTileAtAbsPosition(new Position(x,y));
	}
	
	public BaseTile getTileAtAbsPosition(Position p){
		return this.getTile((int)(p.getAbsX() - this.positionOffset.getAbsX())/this.tileSize, (int)(p.getAbsY() - this.positionOffset.getAbsY())/this.tileSize);
	}
	
	public Position getPositionFromTileCoords(Position p){
		return getPositionFromTileCoords(p.getX(), p.getY());
	}
	
	public Position getPositionFromTileCoords(int x, int y){
		return new Position(x*this.tileSize, y*this.tileSize);
	}
	
	public void printInfo(){
		System.out.println("Grid info:\n\tTile size: " + this.tileSize + "\n\tGrid Dimensions: " + this.gridSize[0] + ", " + this.gridSize[1] + "\n\tTop left corner position: " + this.positionOffset.toString());
	}
}
