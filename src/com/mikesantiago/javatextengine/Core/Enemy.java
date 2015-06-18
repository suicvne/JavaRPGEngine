package com.mikesantiago.javatextengine.Core;

import org.newdawn.slick.opengl.Texture;


public class Enemy 
{
	private int width, height, currentHealth, maximumHealth;
	private float speed, x, y;
	private Texture texture;
	private Tile startTile;
	private boolean firstCalc = true;
	
	//this ones number one
	public Enemy(Tile startTile, int width, int height, int currentHealth, int maximumHealth, float speed, Texture texture)
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
	}
	
	public void Update()
	{
		if(firstCalc)
			firstCalc = false;
		else
			x += Clock.Delta() * speed;
	}
	
	public void Draw()
	{
		SimpleGLDrawer.DrawRectangle(texture, x, y, width, height);
	}
}
