package com.mikesantiago.javatextengine.Core;

import org.newdawn.slick.opengl.Texture;

public class Entity 
{
	private TileGrid grid;
	private float x, y;
	private Texture[] entityFrames;
	private EntityDirection curDirection = EntityDirection.Down;
	private int ID;
	
	//ID is used for mapping purposes, for NPC's that have a specific dialogue for example
	public Entity(TileGrid grid, float x, float y, Texture[] frames, int ID)
	{
		this.grid = grid;
		this.x = x;
		this.y = y;
		this.entityFrames = frames;
		this.ID = ID;
	}
	
	public void Update()
	{
		
	}
	
	public void Draw()
	{
		switch(curDirection)
		{
		case Up:
			SimpleGLDrawer.DrawRectangle(entityFrames[0], x, y, 32, 32);
			break;
		case Down:
			SimpleGLDrawer.DrawRectangle(entityFrames[1], x, y, 32, 32);
			break;
		case Left:
			SimpleGLDrawer.DrawRectangle(entityFrames[2], x, y, 32, 32);
			break;
		case Right:
			SimpleGLDrawer.DrawRectangle(entityFrames[3], x, y, 32, 32);
			break;
		}
	}

	public TileGrid getGrid() {
		return grid;
	}

	public void setGrid(TileGrid grid) {
		this.grid = grid;
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

	public Texture[] getEntityFrames() {
		return entityFrames;
	}

	public void setEntityFrames(Texture[] entityFrames) {
		this.entityFrames = entityFrames;
	}

	public EntityDirection getCurDirection() {
		return curDirection;
	}

	public void setCurDirection(EntityDirection curDirection) {
		this.curDirection = curDirection;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
}
