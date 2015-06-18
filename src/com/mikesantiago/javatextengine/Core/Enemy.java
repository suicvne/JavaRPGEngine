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
}
