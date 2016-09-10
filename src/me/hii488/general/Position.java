package me.hii488.general;

public class Position {
	private float x = 0;
	private float y = 0;

	public Position() {
	}

	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return Math.round(x);
	}

	public float getAbsX() {
		return x;
	}

	public Position setX(float x) {
		this.x = x;
		return this;
	}
	
	public Position addToX(float x){
		this.x += x;
		return this;
	}
	
	

	public int getY() {
		return Math.round(y);
	}

	public float getAbsY() {
		return y;
	}

	public Position setY(float y) {
		this.y = y;
		return this;
	}

	public Position addToY(float y){
		this.y += y;
		return this;
	}
	
	
	public Position setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Position addToLocation(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Position addPosition(Position v) {
		this.x += v.getAbsX();
		this.y += v.getAbsY();
		return this;
	}

	public Position clone() {
		return new Position(x, y);
	}

	public Position print() {
		System.out.println("x: " + this.getX() + "; y: " + this.getY());
		return this;
	}
	
	public String toString(){
		return "x: " + this.getX() + "; y: " + this.getY();
	}
}
