package core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mikesantiago.gdx2.GDX2;

public class Tile
{
	private float x, y, width, height;
	private TileType type;
	private Texture texture;
	private int tiledX, tiledY;
	private boolean isFloorTile = false;
	
	public Tile(float x, float y, float width, float height, TileType type, boolean isFloorTile)
	{
		this.x = x;
		this.y = y;
		this.tiledX = (int)x / 32; //32 is our tile size
		this.tiledY = (int)y / 32; //32 is our tile size
		this.width = width; //should just be 32 but just being cautious here
		this.height = height;
		this.type = type;
		
		this.isFloorTile = isFloorTile;
	}
	
	public void Draw(SpriteBatch sb)
	{
		TextureRegion[][] reg = 
				TextureRegion.split(GDX2.content.getTexture("global-textures"), 
						32, 
						32);
		sb.begin();
		sb.draw(reg[type.textureLocation.getY()][type.textureLocation.getX()], x, y, width, height);
		sb.end();
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getWidth()
	{
		return width;
	}

	public void setWidth(float width)
	{
		this.width = width;
	}

	public float getHeight()
	{
		return height;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}

	public TileType getType()
	{
		return type;
	}

	public void setType(TileType type)
	{
		this.type = type;
	}

	public Texture getTexture()
	{
		return texture;
	}

	public void setTexture(Texture texture)
	{
		this.texture = texture;
	}

	public int getTiledX()
	{
		return tiledX;
	}

	public void setTiledX(int tiledX)
	{
		this.tiledX = tiledX;
	}

	public int getTiledY()
	{
		return tiledY;
	}

	public void setTiledY(int tiledY)
	{
		this.tiledY = tiledY;
	}

	public boolean isFloorTile()
	{
		return isFloorTile;
	}

	public void setFloorTile(boolean isFloorTile)
	{
		this.isFloorTile = isFloorTile;
	}
	
	
}
