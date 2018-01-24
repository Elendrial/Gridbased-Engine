package me.hii488.objects.entities;

import me.hii488.graphics.Camera;
import me.hii488.objects.TexturedObject;

public class RenderEntity extends BaseEntity {
	
	public RenderEntity(BaseEntity e) {
		this.textureName = e.textureName;
		this.position = e.position.clone();
	}
	
	public float randTickChance() {return 0;}
	public void updateOnSec() {}
	public void updateOnTick() {}
	public void updateOnRandTick() {}
	public void initVars() {}
	public void onLoad() {}
	public void onDestroy() {}
	public TexturedObject clone() {return null;} // This should never be cloned... in theory.
	
	public void updateRenderPosition() {
		renderPosA.setX(position.getX() - Camera.getPosition().getX());
		renderPosA.setY(position.getY() - Camera.getPosition().getY());
		renderPosB.setX(renderPosA.getAbsX() + (getTexture().getWidth() * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (getTexture().getHeight() * Camera.scale));
	}
	
}
