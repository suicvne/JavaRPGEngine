package com.mikesantiago.javatextengine.Core;

public class Checkpoint 
{
	private Tile tile;
	private int xDir, yDir;
	
	public Checkpoint(Tile tile, int xDir, int yDir)
	{
		this.tile = tile;
		this.xDir = xDir;
		this.yDir = yDir;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public int getxDir() {
		return xDir;
	}

	public void setxDir(int xDir) {
		this.xDir = xDir;
	}

	public int getyDir() {
		return yDir;
	}

	public void setyDir(int yDir) {
		this.yDir = yDir;
	}
	
	
}
