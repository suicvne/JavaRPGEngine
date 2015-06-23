package com.mikesantiago.javatextengine.Core;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;


public class Enemy 
{
	private int width, height, currentHealth, maximumHealth, currentCheckpointIndex;
	private float speed, x, y;
	private Texture texture;
	private Tile startTile;
	private boolean firstCalc = true;
	private TileGrid grid;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;
	
	//this ones number one
	public Enemy(Tile startTile, int width, int height, int currentHealth, int maximumHealth, float speed, Texture texture, TileGrid grid)
	{
		this.x = startTile.getX();
		this.y = startTile.getX();
		this.speed = speed;
		this.width = width;
		this.height = height;
		this.currentHealth = currentHealth;
		this.maximumHealth = maximumHealth;
		this.texture = texture;
		this.startTile = startTile;
		this.grid = grid;
		
		this.checkpoints = new ArrayList<Checkpoint>();
		this.directions = new int[2];
		this.directions[0] = 0; //x direction
		this.directions[1] = 0; //y direction
		
		this.currentCheckpointIndex = 0;
		PopulateCheckpointList();
	}
	
	/*
	private boolean pathContinues()
	{
		boolean answer = true;
		
		Tile curTile = grid.GetTile((int)(x / 32), (int)(y / 32));
		Tile nextTile = grid.GetTile((int)(x / 32) + 1, (int)(y / 32)); //for right movement only though..WIP
		
		if(!nextTile.getType().canPass)
		{
			answer = false;
		}
		
		return answer;
	}*/
	
	public void Update()
	{
		if(firstCalc)
			firstCalc = false;
		else
		{
			if(CheckpointReached())
			{
				currentCheckpointIndex++;
			}
			else
			{
				x += Clock.Delta() * checkpoints.get(currentCheckpointIndex).getxDir() * speed;
				y += Clock.Delta() * checkpoints.get(currentCheckpointIndex).getyDir() * speed;
			}
			
		}
	}
	
	private void PopulateCheckpointList()
	{
		checkpoints.add(findNextCheckpoint(startTile, directions = findNextDir(startTile)));
		
		int counter = 0;
		boolean cont = true;
		while(cont)
		{
			int[] curDirection = findNextDir(checkpoints.get(counter).getTile());
			if(curDirection[0] == 2 || counter == 10) //another random constant, so enemies can only have a max of 20 checkpoints.
				cont = false;
			else
			{
				checkpoints.add(findNextCheckpoint(checkpoints.get(counter).getTile(), 
						directions = findNextDir(checkpoints.get(counter).getTile())));
				counter++;
			}
		}
	}
	
	private boolean CheckpointReached()
	{
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpointIndex).getTile();
		if(x > t.getX() - 3 && x < t.getX() + 3
				&& y > t.getY() -3 && y < t.getY() + 3) //3 is just a random constant, it allows for leeway when calculating
		{
			reached = true;
			x = t.getX();
			y = t.getY();
		}
		
		return reached;
	}
	
	private Checkpoint findNextCheckpoint(Tile startTile, int[] direction)
	{
		Tile next = null;
		Checkpoint c = null;
		boolean found = false;
		int counter = 1;
		
		Tile t;
		while(found == false)
		{
			int nextX = startTile.getTiledX() + direction[0] * counter;
			int nextY = startTile.getTiledY() + direction[1] * counter;
			
			t = grid.GetTile(nextX, nextY);
			
			
			if(startTile.getType() != t.getType())
			{
				found = true;
				counter -= 1;
				next = grid.GetTile(nextX, nextY);
			}
			counter++;
		}
		
		c = new Checkpoint(next, direction[0], direction[1]);
		return c;
	}
	
	private int[] findNextDir(Tile startTile)
	{		
		int[] dir = new int[2];
		
		Tile u = grid.GetTile((int)startTile.getTiledX(), (int)(startTile.getTiledY() - 1)); //up
		Tile l = grid.GetTile((int)startTile.getTiledX() - 1, (int)startTile.getTiledY()); //left
		Tile d = grid.GetTile((int)startTile.getTiledX(), (int)startTile.getTiledY() + 1); //down
		Tile r = grid.GetTile((int)startTile.getTiledX() + 1, (int)startTile.getTiledY()); //right
		
		if(startTile.getType() == u.getType() && directions[1] != 1)
		{
			dir[0] = 0;
			dir[1] = -1;
		}
		else if(startTile.getType() == l.getType() && directions[0] != 1)
		{
			dir[0] = -1;
			dir[1] = 0;
		}
		else if(startTile.getType() == d.getType() && directions[1] != -1)
		{
			dir[0] = 0;
			dir[1] = 1;
		}
		else if(startTile.getType() == r.getType() && directions[0] != -1)
		{
			dir[0] = 1;
			dir[1] = 0;
		}
		else
		{
			dir[0] = 2; //will never occur, used as an indicator so there's no direction found 
			dir[1] = 2;
		}
		
		//curTile = grid.GetTile((curTile.getTiledX() + dir[0]), (curTile.getTiledY() + dir[1]));
		return dir;
	}
	
	public void Draw()
	{
		SimpleGLDrawer.DrawRectangle(texture, x, y, width, height);
	}
	
	

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	public int getMaximumHealth() {
		return maximumHealth;
	}

	public void setMaximumHealth(int maximumHealth) {
		this.maximumHealth = maximumHealth;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
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

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Tile getStartTile() {
		return startTile;
	}

	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}

	public boolean isFirstCalc() {
		return firstCalc;
	}

	public void setFirstCalc(boolean firstCalc) {
		this.firstCalc = firstCalc;
	}
	
	public TileGrid getGrid() 
	{
		return grid;
	}
	
	public void setGrid(TileGrid grid)
	{
		this.grid = grid;
	}
}
