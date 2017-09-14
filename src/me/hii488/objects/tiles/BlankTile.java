package me.hii488.objects.tiles;

import me.hii488.misc.Settings;

public class BlankTile extends BaseTile{

	public BlankTile(){super();}
	public BlankTile(BlankTile b){super(b);}
	
	@Override
	public float randTickChance() {return 0;}

	@Override
	public void updateOnTick() {}

	@Override
	public void updateOnSec() {}

	@Override
	public void updateOnRandTick() {}
	
	@Override
	public void onLoad() {}
	
	@Override
	public void onDestroy() {}
	
	@Override
	public void initVars() {
		this.states = 0;
		this.textureName = Settings.Texture.defaultTileTextureLocation;
		this.identifier = "blankTile";
	}

	@Override
	public BlankTile clone() {
		return new BlankTile(this);
	}

}
