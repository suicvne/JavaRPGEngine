package com.mikesantiago.javatextengine.Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class CorePlayer extends Entity
{
	private TileGrid grid;
	private TileType[] placeableTypes;
	private int CurTile;
	private boolean floorTiles = false;
	
	public CorePlayer(TileGrid grid, float x, float y, Texture[] playerFrames, int ID)
	{
		super(grid, x, y, playerFrames, ID);
		
		this.placeableTypes = new TileType[TileType.values().length];
		for(int i = 0; i < TileType.values().length; i++)
		{
			placeableTypes[i] = TileType.values()[i];
		}
		this.CurTile = 0;
		
		this.setID(-1); //override entity ID to make sure that player is ALWAYS -1. 
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
		switch(this.getCurDirection())
		{
		case Up:
			SimpleGLDrawer.DrawRectangle(this.getEntityFrames()[0], this.getX(), this.getY(), 32, 32);
			break;
		case Down:
			SimpleGLDrawer.DrawRectangle(this.getEntityFrames()[1], this.getX(), this.getY(), 32, 32);
			break;
		case Left:
			SimpleGLDrawer.DrawRectangle(this.getEntityFrames()[2], this.getX(), this.getY(), 32, 32);
			break;
		case Right:
			SimpleGLDrawer.DrawRectangle(this.getEntityFrames()[3], this.getX(), this.getY(), 32, 32);
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
		
		while(Keyboard.next())
		{
			if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Up);
				Tile above = grid.GetTile((int)Math.floor(this.getX() / 32), (int)Math.floor((this.getY() / 32) - 1));
				if(above.getIsFloorTile() || above.getType().canPass)
				{
					this.setY(above.getY());
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Left);
				Tile left = grid.GetTile((int)Math.floor(this.getX() / 32) - 1, (int)Math.floor((this.getY() / 32)));
				if(left.getIsFloorTile() || left.getType().canPass)
				{
					this.setX(left.getX());
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Down);
				Tile down = grid.GetTile((int)Math.floor(this.getX() / 32), (int)Math.floor((this.getY() / 32) + 1));
				if(down.getIsFloorTile() || down.getType().canPass)
				{
					this.setY(down.getY());
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Right);
				Tile right = grid.GetTile((int)Math.floor(this.getX() / 32) + 1, (int)Math.floor((this.getY() / 32)));
				if(right.getIsFloorTile() || right.getType().canPass)
				{
					this.setX(right.getX());
				}
			}
			
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

	public EntityDirection getCurDirection() {
		return curDirection;
	}

	public void setCurDirection(EntityDirection curDirection) {
		this.curDirection = curDirection;
	}
	
	
}
