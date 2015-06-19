package com.mikesantiago.javatextengine.Core;

import org.newdawn.slick.opengl.Texture;

public class Tile
{
	private float x, y, width, height;
	private TileType type;
	private Texture texture;
	private int tiledX, tiledY;
	
	public Tile(float x, float y, float width, float height, TileType type)
	{
		this.x = x;
		this.y = y;
		this.tiledX = (int)x / 32;
		this.tiledY = (int)y / 32;
		this.width = width;
		this.height = height;
		this.type = type;
		this.texture = SimpleGLDrawer.QuickLoad(type.textureName);
	}

	/**Getters and setters
	 * 
	 */
	//
	public float getX() {
		return x;
	}
	
	public int getTiledX()
	{
		return tiledX;
	}
	
	public int getTiledY()
	{
		return tiledY;
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

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public TileType getType() {
		return type;
	}

	public void setType(TileType type) {
		this.type = type;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	
}