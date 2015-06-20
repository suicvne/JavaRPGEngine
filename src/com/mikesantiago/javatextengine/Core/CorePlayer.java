package com.mikesantiago.javatextengine.Core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class CorePlayer extends Entity
{
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
		
		this.setEntityFrames(new Texture[]{
				SimpleGLDrawer.QuickLoad("player/player-up-0"), 
				SimpleGLDrawer.QuickLoad("player/player-down-0"),
				SimpleGLDrawer.QuickLoad("player/player-left-0"),
				SimpleGLDrawer.QuickLoad("player/player-right-0")
		}); //Overriding frames, probably going to take out the texture constructor
	}
	
	public void SetTile(TileType type)
	{
		this.getGrid().SetTile((int)Math.floor(Mouse.getX() / 32), 
				(int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32), 
				type);
	}
	public void SetTile(TileType type, boolean isFloor)
	{
		this.getGrid().SetTile((int)Math.floor(Mouse.getX() / 32), 
				(int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32), 
				type,
				isFloor);
	}
	
	//@Override
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
	
	//@Override
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
				
				int nextX = (int)Math.floor(this.getX() / 32);
				int nextY = (int)Math.floor((this.getY() / 32) - 1);
				
				if(nextY != -1)
				{
					Tile above = this.getGrid().GetTile(nextX, nextY);
				
					if(above.getIsFloorTile() || above.getType().canPass)
					{
						this.setY(above.getY());
					}
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Left);
				
				int nextX = (int)Math.floor((this.getX() / 32) - 1);
				int nextY = (int)Math.floor((this.getY() / 32));
				
				if(nextX != -1)
				{
					Tile left = this.getGrid().GetTile(nextX, nextY);
					if(left.getIsFloorTile() || left.getType().canPass)
					{
						this.setX(left.getX());
					}
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Down);
				
				int nextX = (int)Math.floor((this.getX() / 32));
				int nextY = (int)Math.floor((this.getY() / 32) + 1);
				
				if(nextY >= this.getGrid().getMaxY())
				{/*no movement*/	}
				else
				{
					Tile down = this.getGrid().GetTile(nextX, nextY);
					if(down.getIsFloorTile() || down.getType().canPass)
					{
						this.setY(down.getY());
					}
				}
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
			{
				this.setCurDirection(EntityDirection.Right);
				
				int nextX = (int)Math.floor((this.getX() / 32) + 1);
				int nextY = (int)Math.floor((this.getY() / 32));
				
				if(nextX >= this.getGrid().getMaxX())
				{}
				else
				{
					Tile right = this.getGrid().GetTile(nextX, nextY);
					if(right.getIsFloorTile() || right.getType().canPass)
					{
						this.setX(right.getX());
					}
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
	
}
