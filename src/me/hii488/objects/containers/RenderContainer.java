package me.hii488.objects.containers;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import me.hii488.graphics.GUI.GUI;
import me.hii488.objects.entities.BaseEntity;

public class RenderContainer extends BaseContainer{
	
	public void onLoad(){}
	public void updateOnTick(){}
	public void updateOnSec(){}
	public void endOfTick(){}
	public void mouseClicked(MouseEvent arg0) {}
	public void keyPressed(KeyEvent arg0){}
	public void addEntity(BaseEntity e){}
	public void removeEntity(BaseEntity e){}
	
	public boolean showEntities = true;
	public boolean showGUI = true;
	
	public void render(Graphics g) {
		grid.render(g);
		if(showEntities) for (BaseEntity e : entities) if(e.shouldRender()) e.render(g);
		if(showGUI) for(GUI gui : guis) gui.render(g);
	}
}
