package com.mikesantiago.javatextengine.Utils;

public class AbsoluteLocation 
{
	
	private float x, y;
	
	public AbsoluteLocation(int gridX, int gridY)
	{
		this.x = gridX * 32;
		this.y = gridY * 32;
	}
	
	public AbsoluteLocation(float absX, float absY)
	{
		this.x = absX;
		this.y = absY;
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

}
