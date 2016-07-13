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

	public void setX(float x) {
		this.x = x;
	}

	public int getY() {
		return Math.round(y);
	}

	public float getAbsY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void addToLocation(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public Position addVector(Vector v) {
		this.x += v.getDx();
		this.y += v.getDy();
		return this;
	}

	public Position clone() {
		return new Position(x, y);
	}

	public void print() {
		System.out.println("x: " + this.getX() + "; y: " + this.getY());
	}
}
