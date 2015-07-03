package com.mikesantiago.javarpgengine.core;

public class Vector4
{
	private float topLeft, topRight, bottomLeft, bottomRight;
	
	public Vector4(){}
	public Vector4(float topLeft, float topRight, float bottomLeft, float bottomRight)
	{
		this.topLeft = topLeft;
		this.topRight = topRight;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
	}
	public float getTopLeft()
	{
		return topLeft;
	}
	public void setTopLeft(float topLeft)
	{
		this.topLeft = topLeft;
	}
	public float getTopRight()
	{
		return topRight;
	}
	public void setTopRight(float topRight)
	{
		this.topRight = topRight;
	}
	public float getBottomLeft()
	{
		return bottomLeft;
	}
	public void setBottomLeft(float bottomLeft)
	{
		this.bottomLeft = bottomLeft;
	}
	public float getBottomRight()
	{
		return bottomRight;
	}
	public void setBottomRight(float bottomRight)
	{
		this.bottomRight = bottomRight;
	}
	
	

}
