package me.hii488.objects.entities;

import me.hii488.objects.TexturedObject;

public class RenderEntity extends BaseEntity {
	
	public RenderEntity(BaseEntity e) {
		this.textureName = e.textureName;
		this.currentState = e.currentState;
		this.states = e.states;
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
	
}
