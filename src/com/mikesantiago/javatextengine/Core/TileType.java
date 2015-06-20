package com.mikesantiago.javatextengine.Core;

public enum TileType {
	
	Grass("tile-grass", true), Stone("tile-stone", false),
	Water("tile-water", true), SpecialTile("placeholder-icon", false),
	Air("tile-air", true);
	
	
	String textureName;
	boolean canPass;
	
	TileType(String textureName, boolean canPass)
	{
		this.textureName = textureName;
		this.canPass = canPass;
	}
}
