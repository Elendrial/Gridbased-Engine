package me.hii488.misc;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.hii488.graphics.Camera;
import me.hii488.interfaces.ITickable;
import me.hii488.objects.tiles.BaseTile;
import me.hii488.registries.TileRegistry;

public class Grid implements ITickable{

	public BaseTile[][] grid;
	public Vector dimensions = new Vector(0,0);
	
	public Grid() {}

	public Grid(Grid g) {
		this.dimensions = g.dimensions;
		grid = new BaseTile[dimensions.getX()][dimensions.getY()];
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				setTile(g.getTile(i, j), i, j);
			}
		}
	}

	public void setupGrid(int size) {
		dimensions.setX(size); dimensions.setY(size);
		grid = new BaseTile[size][size];
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				grid[i][j] = TileRegistry.getBlankTile();
			}
		}
	}
	
	public void setupGrid(int sizeX, int sizeY) {
		dimensions.setX(sizeX); dimensions.setY(sizeY);
		grid = new BaseTile[sizeX][sizeY];
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				grid[i][j] = TileRegistry.getBlankTile();
			}
		}
	}

	public void onLoad() {
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				this.getTile(i, j).onLoad();
			}
		}
	}

	public void updateOnTick() {
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				this.getTile(i, j).updateOnTick();
			}
		}
	}

	public void updateOnSec() {
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				this.getTile(i, j).updateOnSec();
			}
		}
	}
	
	public void endOfTick(){
		
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
		grid[x][y].gridPosition.setLocation(x, y);
	}
	
	public void setTile(String identifier, int state, Vector p) {
		this.setTile(identifier, state, p.getX(), p.getY());
	}
	
	public void setTile(String identifier, int state, int x, int y) {
		grid[x][y] = TileRegistry.getTile(identifier);
		grid[x][y].gridPosition.setLocation(x, y);
		grid[x][y].currentState = state;
	}

	public void setTile(BaseTile tile, Vector p) {
		this.setTile(tile, p.getX(), p.getY());
	}
	
	public void setTile(BaseTile tile, int x, int y) {
		grid[x][y] = (BaseTile) tile.clone();
		grid[x][y].gridPosition.setLocation(x, y);
	}
	
	public void fillRectWithTile(String identifier, int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				this.setTile(identifier, i, j);
			}
		}
	}
	
	public void fillRectWithTile(String identifier, int state, int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				this.setTile(identifier, state, i, j);
			}
		}
	}
	
	public void fillRectWithTile(BaseTile tile, int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				this.setTile(tile, i, j);
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
	
	public void wallRectWithTile(String identifier, int state, int x1, int y1, int x2, int y2){
		for(int i = x1; i < x2; i++){
			this.setTile(identifier, state, i, y1);
			this.setTile(identifier, state, i, y2-1);
		}
		
		for(int i = y1; i < y2; i++){
			this.setTile(identifier, state, x1, i);
			this.setTile(identifier, state, x2-1, i);
		}
	}
	
	public void wallRectWithTile(BaseTile tile, int x1, int y1, int x2, int y2){
		for(int i = x1; i < x2; i++){
			this.setTile(tile, i, y1);
			this.setTile(tile, i, y2-1);
		}
		
		for(int i = y1; i < y2; i++){
			this.setTile(tile, x1, i);
			this.setTile(tile, x2-1, i);
		}
	}

	public Grid clone() {
		return new Grid(this);
	}

	public void renderArea(Graphics g, Rectangle r) {
		for(int i = r.x; i < r.x + r.width; i++) {
			for(int j = r.y; j < r.y + r.height; j++) {
				if(getTile(i, j).shouldRender()) getTile(i,j).render(g);
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < dimensions.getX(); i++) {
			for (int j = 0; j < dimensions.getY(); j++) {
				if(getTile(i, j).shouldRender()) getTile(i,j).render(g);
			}
		}
	}
	
	public BaseTile getTileAtVector(int x, int y){
		return this.getTile((int) Math.floor((x)/Settings.Texture.tileSize), (int) Math.floor((y)/Settings.Texture.tileSize));
	}
	
	public BaseTile getTileAtVector(Vector p){
		return this.getTile((int) Math.floor((p.getAbsX())/Settings.Texture.tileSize), (int) Math.floor((p.getAbsY())/Settings.Texture.tileSize));
	}
	
	public static Vector getGridPosAtVector(int x, int y){
		return new Vector((float) Math.floor(x)/Settings.Texture.tileSize, (float) Math.floor(y)/Settings.Texture.tileSize);
	}
	
	public static Vector getGridPosAtVector(Vector p){
		return new Vector((float) Math.floor((p.getAbsX())/Settings.Texture.tileSize), (float) Math.floor((p.getAbsY())/Settings.Texture.tileSize));
	}
	
	public static Vector getVectorAtGridPos(int x, int y) {
		return new Vector(x * Settings.Texture.tileSize, y * Settings.Texture.tileSize);
	}
	
	public static Vector getVectorAtGridPos(Vector v) {
		return new Vector(v.getX() * Settings.Texture.tileSize, v.getY() * Settings.Texture.tileSize);
	}
	
	
	public void printInfo(){
		System.out.println("Grid info:\n\tTile size: " + Settings.Texture.tileSize + "\n\tGrid Dimensions: " + this.dimensions.getX() + ", " + this.dimensions.getY() + "\n\tTop left corner position: " + Camera.getPosition().toString());
	}
	
	public String gridAsString(){
		String s = "";
		
		for(int i = 0; i < this.dimensions.getY(); i++){
			for(int j = 0; j < this.dimensions.getX(); j++){
				s+="[" + this.getTile(j, i).textureName.substring(0, 1) + "]";
			}
			s+="\n";
		}
		
		return s;
	}

	@Override
	public float randTickChance() {return 0;}

	@Override
	public void updateOnRandTick() {}
}
