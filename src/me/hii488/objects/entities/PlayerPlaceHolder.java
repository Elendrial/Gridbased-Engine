package me.hii488.objects.entities;

import me.hii488.graphics.Camera;
import me.hii488.objects.TexturedObject;

public class PlayerPlaceHolder extends BaseEntity {
	
	public PlayerPlaceHolder(Player p) {
		this.textureName = p.textureName;
		this.position = p.position;
	}
	
	@Override
	public float randTickChance() {return 0;}

	@Override
	public void updateOnSec() {}

	@Override
	public void updateOnRandTick() {}

	@Override
	public void initVars() {}

	@Override
	public void onLoad() {}

	@Override
	public void onDestroy() {}

	@Override
	public TexturedObject clone() {return null;} // This should never be cloned... in theory.
	
	public void updateRenderPosition() {
		renderPosA.setX(position.getX() - Camera.getPosition().getX());
		renderPosA.setY(position.getY() - Camera.getPosition().getY());
		renderPosB.setX(renderPosA.getAbsX() + (getTexture().getWidth() * Camera.scale));
		renderPosB.setY(renderPosA.getAbsY() + (getTexture().getHeight() * Camera.scale));
	}
	
}
