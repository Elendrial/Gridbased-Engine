package me.hii488.misc;

public class Vector {
	private float x = 0;
	private float y = 0;

	public Vector() {
	}

	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return Math.round(x);
	}

	public float getAbsX() {
		return x;
	}

	public Vector setX(float x) {
		this.x = x;
		return this;
	}
	
	public Vector addToX(float x){
		this.x += x;
		return this;
	}
	
	

	public int getY() {
		return Math.round(y);
	}

	public float getAbsY() {
		return y;
	}

	public Vector setY(float y) {
		this.y = y;
		return this;
	}

	public Vector addToY(float y){
		this.y += y;
		return this;
	}
	
	
	public Vector setLocation(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vector addToLocation(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}

	public Vector addPosition(Vector v) {
		this.x += v.getAbsX();
		this.y += v.getAbsY();
		return this;
	}

	public Vector clone() {
		return new Vector(x, y);
	}

	public Vector print() {
		System.out.println("x: " + this.getX() + "; y: " + this.getY());
		return this;
	}
	
	public String toString(){
		return "x: " + this.getX() + "; y: " + this.getY();
	}
}
