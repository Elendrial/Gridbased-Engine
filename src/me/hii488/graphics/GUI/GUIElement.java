package me.hii488.graphics.GUI;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import me.hii488.misc.Vector;

public abstract class GUIElement implements Comparable<GUIElement>{
	protected Vector position = new Vector(0, 0);
	protected Vector dimensions = new Vector(0,0);
	public String identifier = "", text = "";
	public GUIPriority priority = GUIPriority.LOW;
	public boolean hidden = false;
	
	public abstract void onClick(MouseEvent e);
	public abstract void render(Graphics g);
	public void onKeyTyped(KeyEvent e){}
	
	public Vector getPosition() {
		return position;
	}
	
	public GUIElement setPosition(Vector position) {
		this.position.setLocation(position);
		this.dimensions.setLocation(position.getX(), position.getY());
		return this;
	}
	
	public GUIElement setPosition(int x, int y) {
		this.position.setLocation(x, y);
		return this;
	}
	
	public Vector getDimensions() {
		return dimensions;
	}
	
	public GUIElement setDimensions(int width, int height) {
		this.dimensions.setLocation(width, height);
		return this;
	}
	
	public GUIElement setDimensions(Vector v) {
		this.dimensions.setLocation(v);
		return this;
	}
	
	public GUIElement setIdentifier(String i){
		identifier = i;
		return this;
	}
	
	public GUIElement setText(String t){
		text = t;
		return this;
	}
	
	@Override
    public int compareTo(GUIElement e) {
        if(e.priority.ordinal() < this.priority.ordinal())
            return 1;
        else if(e.priority.ordinal() > this.priority.ordinal())
            return -1;
        else
            return 0;
    }
	
}
