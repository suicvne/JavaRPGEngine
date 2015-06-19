package com.mikesantiago.javatextengine.Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class CorePlayer 
{
	private TileGrid grid;
	private TileType[] placeableTypes;
	private int CurTile;
	private float x, y;
	private boolean floorTiles = false;
	private Texture[] playerFrames;
	private PlayerDirection curDirection = PlayerDirection.Up;
	
	public CorePlayer(TileGrid grid, Texture[] playerFrames, float x, float y)
	{
		this.grid = grid;
		this.placeableTypes = new TileType[TileType.values().length];
		for(int i = 0; i < TileType.values().length; i++)
		{
			placeableTypes[i] = TileType.values()[i];
		}
		this.CurTile = 0;
		
		this.playerFrames = playerFrames;
		
		this.x = x;
		this.y = y;
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
	public void SetTile(TileType type, boolean isFloor)
	{
		grid.SetTile((int)Math.floor(Mouse.getX() / 32), 
				(int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32), 
				type,
				isFloor);
	}
	
	public void Draw()
	{
		switch(curDirection)
		{
		case Up:
			SimpleGLDrawer.DrawRectangle(playerFrames[0], x, y, 32, 32);
			break;
		case Down:
			SimpleGLDrawer.DrawRectangle(playerFrames[1], x, y, 32, 32);
			break;
		case Left:
			SimpleGLDrawer.DrawRectangle(playerFrames[2], x, y, 32, 32);
			break;
		case Right:
			SimpleGLDrawer.DrawRectangle(playerFrames[3], x, y, 32, 32);
			break;
		}
	}
	
	public TileType getCurrentTile()
	{
		return TileType.values()[CurTile];
	}
	
	public void Update()
	{
		if(Mouse.isButtonDown(0))
			SetTile(placeableTypes[CurTile]);
		else if(Mouse.isButtonDown(1)) //sets a non-floor (foreground)
			SetTile(placeableTypes[CurTile], true); //set a floor instead
		
		int dWheel = Mouse.getDWheel();
	    if (dWheel < 0) 
	    {
	    	if(CurTile <= 0)
				CurTile = placeableTypes.length - 1;
			else
				CurTile--;
	    } 
	    else if (dWheel > 0)
	    {
	    	if(CurTile >= placeableTypes.length - 1)
				CurTile = 0;
			else
				CurTile++;
        }
		
		Keyboard.enableRepeatEvents(true);
		
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		{
			curDirection = PlayerDirection.Up;
			this.y -= 1;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		{
			curDirection = PlayerDirection.Down;
			this.y += 1;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		{
			curDirection = PlayerDirection.Left;
			this.x -= 1;
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		{
			curDirection = PlayerDirection.Right;
			this.x += 1;
		}
		
	}
	
	public void checkMouseWheel() {
		    int dWheel = Mouse.getDWheel();
		    if (dWheel < 0) {
		        System.out.println("DOWN");
		    } else if (dWheel > 0){
		        System.out.println("UP");
	        }
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public PlayerDirection getCurDirection() {
		return curDirection;
	}

	public void setCurDirection(PlayerDirection curDirection) {
		this.curDirection = curDirection;
	}
	
	
}
