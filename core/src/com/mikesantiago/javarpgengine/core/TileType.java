package com.mikesantiago.javarpgengine.core;

public enum TileType
{
	Grass(true, new TextureLocation(0,0)), Stone(false, new TextureLocation(1,0)), Water(true, new TextureLocation(2,0)), 
	Dirt(true, new TextureLocation(3,0)), 
	Air(true, new TextureLocation(4,0));
	
	public boolean canPass;
	public TextureLocation textureLocation;
	
	TileType(boolean canPass, TextureLocation textureLocation)
	{
		this.canPass = canPass;
		this.textureLocation = textureLocation;
	}
}
