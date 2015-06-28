package core;

public class TextureLocation
{
	private int x, y;
	
	/**
	 * The texture's location in relation to the 8x8 textures file
	 * @param x
	 * @param y
	 */
	public TextureLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	
}
