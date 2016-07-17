package me.hii488.objects.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.hii488.helpers.EntityHelper;


public class GeneralEmptyEntity extends GeneralEntity{
	
	@Override
	public void setup() {
		collisionBox = new Rectangle(position.getX(), position.getY(),  0, 0);
	}
	
	@Override
	public void updateOnTick(){
		if(EntityHelper.isOutOfContainer(this)){
			this.destroy();
		}
	}
	
	@Override
	public void render(Graphics g){}
	
}
