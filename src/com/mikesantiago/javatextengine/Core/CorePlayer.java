package com.mikesantiago.javatextengine.Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class CorePlayer 
{
	private TileGrid grid;
	private TileType[] placeableTypes;
	private int CurTile;
	
	public CorePlayer(TileGrid grid)
	{
		this.grid = grid;
		this.placeableTypes = new TileType[TileType.values().length];
		for(int i = 0; i < TileType.values().length; i++)
		{
			placeableTypes[i] = TileType.values()[i];
		}
		this.CurTile = 0;
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
	
	public TileType getCurrentTile()
	{
		return TileType.values()[CurTile];
	}
	
	public void Update()
	{
		if(Mouse.isButtonDown(0))
			SetTile(placeableTypes[CurTile]);
		else if(Mouse.isButtonDown(1))
			SetTile(TileType.Grass);
		
		while(Keyboard.next())
		{
			
			if(Keyboard.getEventKey() == Keyboard.KEY_HOME && Keyboard.getEventKeyState())
			{
				if(CurTile >= placeableTypes.length - 1)
					CurTile = 0;
				else
					CurTile++;
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_END && Keyboard.getEventKeyState())
			{
				if(CurTile <= 0)
					CurTile = placeableTypes.length - 1;
				else
					CurTile--;
			}
		}
		
	}
}
