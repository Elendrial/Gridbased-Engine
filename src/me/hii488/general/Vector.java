package me.hii488.general;

// TODO: Get rid and convert everything to position (as a vector is a position or change in position)
public class Vector {
	double dx;
	double dy;

	public Vector(double x, double y) {
		dx = x;
		dy = y;
	}

	public void setVector(double x, double y) {
		dx = x;
		dy = y;
	}

	public Vector addVector(Vector v) {
		return new Vector(dx + v.dx, dy + v.dy);
	}

	public Vector clone() {
		return new Vector(dx, dy);
	}

	public Vector invertVector() {
		return new Vector(-dx, -dy);
	}

	public double getDx() {
		return dx;
	}

	public void addtoDx(double x) {
		dx += x;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public void addtoDy(double y) {
		dy += y;
	}

	public void reset() {
		dx = 0;
		dy = 0;
	}
	
}
