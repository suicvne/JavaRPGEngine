package com.mikesantiago.javatextengine.Core;

public enum TileType {
	
	Grass("placeholder-grass", true), Stone("placeholder-stone", false),
	Water("placeholder-water", true), SpecialTile("placeholder-icon", false);
	
	
	String textureName;
	boolean canPass;
	
	TileType(String textureName, boolean canPass)
	{
		this.textureName = textureName;
		this.canPass = canPass;
	}
}
