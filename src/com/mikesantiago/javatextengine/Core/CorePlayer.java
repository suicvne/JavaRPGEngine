package com.mikesantiago.javatextengine.Core;

import org.lwjgl.input.Mouse;

public class CorePlayer 
{
	private TileGrid grid;
	
	public CorePlayer(TileGrid grid)
	{
		this.grid = grid;
	}
	
	public void SetTile()
	{
		grid.SetTile((int)Math.floor(Mouse.getX() / 32), 
				(int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32), 
				TileType.Water);
	}
	public void SetTile(TileType type)
	{
		grid.SetTile((int)Math.floor(Mouse.getX() / 32), 
				(int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32), 
				type);
	}
	
	public void Update()
	{
		if(Mouse.isButtonDown(0))
			SetTile();
		else if(Mouse.isButtonDown(1))
			SetTile(TileType.Grass);
	}
}
