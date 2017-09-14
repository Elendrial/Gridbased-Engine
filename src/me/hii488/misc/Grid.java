package me.hii488.misc;

import java.awt.Graphics;

import me.hii488.objects.tiles.BaseTile;
import me.hii488.registries.TileRegistry;

public class Grid {

	public BaseTile[][] grid;
	public Vector positionOffset;
	public int gridSize[] = {0,0}; // 0 = x, 1 = y
	
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
				grid[i][j] = TileRegistry.getBlankTile();
			}
		}
	}
	
	public void setupGrid(int sizeX, int sizeY) {
		gridSize[0] = sizeX; gridSize[1] = sizeY;
		grid = new BaseTile[sizeX][sizeY];
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				grid[i][j] = TileRegistry.getBlankTile();
			}
		}
	}

	public void positionGrid(Vector start) {
		positionOffset = start;
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				this.getTile(i, j).gridPosition = (new Vector(i, j));
//				this.getTile(i, j).renderOffset = positionOffset;
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
	
	public BaseTile getTile(Vector p) {
		try{
			return grid[p.getX()][p.getY()];
		}
		catch(Exception e){
			return null;
		}
	}

	public void setTile(String identifier, Vector p) {
		this.setTile(identifier, p.getX(), p.getY());
	}
	
	public void setTile(String identifier, int x, int y) {
		grid[x][y] = TileRegistry.getTile(identifier);
	}

	public void setTile(BaseTile tile, Vector p) {
		this.setTile(tile, p.getX(), p.getY());
	}
	
	public void setTile(BaseTile tile, int x, int y) {
		grid[x][y] = (BaseTile) tile.clone();
	}
	
	public void fillRectWithTile(String identifier, int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				this.setTile(identifier, i, j);
			}
		}
	}
	
	public void wallRectWithTile(String identifier, int x1, int y1, int x2, int y2){
		for(int i = x1; i < x2; i++){
			this.setTile(identifier, i, y1);
			this.setTile(identifier, i, y2-1);
		}
		
		for(int i = y1; i < y2; i++){
			this.setTile(identifier, x1, i);
			this.setTile(identifier, x2-1, i);
		}
	}

	public Grid clone() {
		return new Grid(this);
	}

	public void render(Graphics g) {
		for (int i = 0; i < gridSize[0]; i++) {
			for (int j = 0; j < gridSize[1]; j++) {
				getTile(i, j).render(g);
			}
		}
	}
	
	public BaseTile getTileAtAbsPosition(int x, int y){
		return getTileAtAbsPosition(new Vector(x,y));
	}
	
	public BaseTile getTileAtAbsPosition(Vector p){
		return this.getTile((int)(p.getAbsX() - this.positionOffset.getAbsX())/Settings.Texture.tileSize, (int)(p.getAbsY() - this.positionOffset.getAbsY())/Settings.Texture.tileSize);
	}
	
	public Vector getPositionFromTileCoords(Vector p){
		return getPositionFromTileCoords(p.getX(), p.getY());
	}
	
	public Vector getPositionFromTileCoords(int x, int y){
		return new Vector(x * Settings.Texture.tileSize, y * Settings.Texture.tileSize);
	}
	
	public void printInfo(){
		System.out.println("Grid info:\n\tTile size: " + Settings.Texture.tileSize + "\n\tGrid Dimensions: " + this.gridSize[0] + ", " + this.gridSize[1] + "\n\tTop left corner position: " + this.positionOffset.toString());
	}
	
	public String gridAsString(){
		String s = "";
		
		for(int i = 0; i < this.gridSize[1]; i++){
			for(int j = 0; j < this.gridSize[0]; j++){
				s+="[" + this.getTile(j, i).textureName.substring(0, 1) + "]";
			}
			s+="\n";
		}
		
		return s;
	}
}