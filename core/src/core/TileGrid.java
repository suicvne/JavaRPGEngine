package core;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class TileGrid
{
	public Tile[][] map;
	private ShapeRenderer sr = new ShapeRenderer();
	private float totalWidth, totalHeight;
	
	public TileGrid()
	{
		map = new Tile[40][40]; //640x480
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass, false);
			}
		}
		map[0][0].setType(TileType.Stone);
		map[0][0].setFloorTile(true);
		
		totalWidth = 20 * 32;
		totalHeight = 20 * 32;
	}
	
	public void Draw(SpriteBatch sb)
	{
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map.length; y++)
			{
				Tile toDraw = map[x][y];
				if(toDraw.isFloorTile())
				{
					sb.setColor(0.5f, 0.5f, 0.5f, 1);
					map[x][y].Draw(sb);
					sb.setColor(1f, 1f, 1f, 1f);
				}
				else
					map[x][y].Draw(sb);
			}
		}
	}
	
	public void setTile(TileType tile, int tiledX, int tiledY)
	{
		map[tiledX][tiledY].setType(tile);
	}
	
	public void setTile(TileType tile, float x, float y)
	{
		map[(int)Math.floor(x / 32)][(int)Math.floor(y / 32)].setType(tile);
	}
	
	public void setTile(TileType tile, int tiledX, int tiledY, boolean isFloor)
	{
		if(tiledX > map.length - 1 || tiledX < 0 ||
				tiledY > map[0].length - 1 || tiledY < 0)
		{
			System.out.println("tried to place at invalid pos " + tiledX + ", " + tiledY);
		}
		else
		{
			Tile toSet = map[tiledX][tiledY];
			toSet.setType(tile);
			toSet.setFloorTile(isFloor);
		}
	}
	
	public void setTile(TileType tile, float x, float y, boolean isFloor)
	{
		Tile toSet = map[(int)Math.floor(x / 32)][(int)Math.floor(y / 32)];
		toSet.setType(tile);
		toSet.setFloorTile(isFloor);
	}
	
	public float getTotalWidth() {return totalWidth;}
	public float getTotalHeight() {return totalHeight;}
}
