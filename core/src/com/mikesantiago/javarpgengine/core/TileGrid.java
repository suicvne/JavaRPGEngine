package com.mikesantiago.javarpgengine.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mikesantiago.javarpgengine.GDX2;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;

public class TileGrid
{
	public Tile[][] map;
	private ShapeRenderer sr = new ShapeRenderer();
	private float totalWidth, totalHeight;
	private Player tempPlayer = new Player();
	
	public TileGrid()
	{
		map = new Tile[40][40]; //technically 41x41?
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass, false);
			}
		}
		
		totalWidth = 40 * 32;
		totalHeight = 40 * 32;
	}
	
	public TileGrid(int w, int h)
	{
		map = new Tile[w][h];
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.Grass, false);
			}
		}
		
		totalWidth = w * 32;
		totalHeight = h * 32;
	}
	
	public void WriteTileGridInformation()
	{
		try
		{
			File tileGridFile = new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2");
			FileWriter writer = new FileWriter(tileGridFile.getAbsolutePath());
			
			System.out.println("writing save file to '" + tileGridFile.getAbsolutePath() + "'");
			for(int x = 0; x < map.length; x++)
			{
				for(int y = 0; y < map[x].length; y++)
				{
					//type:tiledX:tiledY:width:height:floorTile
					Tile retrieved = map[x][y];
					int isFloor = (retrieved.isFloorTile()) ? 1 : 0;
					String formatted = String.format("%s:%s:%s:%s:%s:%s\n",
							retrieved.getType().ordinal(),
							retrieved.getTiledX(),
							retrieved.getTiledY(),
							32,
							32,
							isFloor);
					writer.write(formatted);
				}
			}
			writer.flush();
			writer.close();
			System.out.println("written!");
			
			System.out.println("writing map metadata..");
			{
				WriteMapMetadata();
			}
			System.out.println("written!");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getStackTrace().toString(), "An Error Occurred While Trying to Save", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}
	
	public Player tempPlayer(){return tempPlayer;}
	
	private void WriteMapMetadata()
	{
		File metaDataFile = new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2meta");
		try
		{
			FileWriter writer = new FileWriter(metaDataFile.getAbsolutePath());
			writer.write("w=" + map.length + "\n");
			writer.write("h=" + map[0].length + "\n");
			writer.flush();
			writer.close();
		}
		catch(Exception ex)
		{
			//TODO: error handling or smth 
		}
	}
	
	private void ReadMapMetadata()
	{
		System.out.println("reading map metadata..");
		
		File tileGridFile = new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2meta");
		try 
		{
			FileReader reader = new FileReader(tileGridFile.getAbsolutePath());
			BufferedReader br = new BufferedReader(reader);
			String curLine = br.readLine();
			//declare metadata
				int w = 0;
				int h = 0;
				
			while(curLine != null)
			{
				String[] split = curLine.split("="); //works like an ini go figure right?
				if(split[0].equals("w"))
				{
					w = Integer.parseInt(split[1]);
				}
				else if(split[0].equals("h"))
				{
					h = Integer.parseInt(split[1]);
				}
				
				curLine = br.readLine();
			}
			
			map = new Tile[w][h];
			
			br.close();
		} 
		catch (Exception e) 
		{
			e.getMessage();
		}
	}
	
	public void ReadTileGridInformation()
	{
		ReadMapMetadata();
		
		try
		{
			File tileGridFile = new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2");
			FileReader reader = new FileReader(tileGridFile.getAbsolutePath());
			BufferedReader br = new BufferedReader(reader);
			
			String curLine = br.readLine();
			
			System.out.println("reading file from '" + tileGridFile.getAbsolutePath() + "'");
			while(curLine != null)
			{
				//max index of 5
				String[] parts = curLine.split(":");
				boolean isFloorTile;
				int isFloorInt = Integer.parseInt(parts[5]);
				if(isFloorInt == 0)
					isFloorTile = false;
				else
					isFloorTile = true;
				
				float x, y;
				x = (Float.parseFloat(parts[1]) * 32);
				y = (Float.parseFloat(parts[2]) * 32);
				try
				{
				//type:tiledX:tiledY:width:height:floorTile
					Tile temp = new Tile(x,	y, Float.parseFloat(parts[3]), (Float.parseFloat(parts[4])),
						TileType.values()[Integer.parseInt(parts[0])], isFloorTile);
					map[(int)(x / 32)][(int)(y / 32)] = temp;
				}
				catch(Exception ex)
				{
					System.out.println("tried to place invalid tile at " + x + ", " + y);
				}
				curLine = br.readLine();
			}
			
			br.close();
			
			tempPlayer = new Player(this);
			this.totalWidth = map.length * 32;
			this.totalHeight = map[0].length * 32;
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getStackTrace().toString(), "An Error Occurred While Trying to Read the Save", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Tile getTileAt_offsetLeft(float x, float y)
	{
		int tx = (int)Math.floor(x / 32);
		tx -= 1;
		int ty = (int)Math.floor(y / 32);
		
		if(tx < 0 || ty < 0 || tx > map.length - 1 || ty > map[0].length - 1)
		{
			Tile t = new Tile(0,0,32,32,TileType.Stone,false);
			return t;
		}
		
		return map[tx][ty];
	}
	
	public Tile getTileAt(float x, float y)
	{
		int tx = (int)Math.floor((double)x / 32);
		int ty = (int)Math.floor((double)y / 32);
		
		if(tx < 0 || ty < 0 || tx > map.length - 1 || ty > map[0].length - 1)
		{
			Tile t = new Tile(0,0,32,32,TileType.Stone,false);
			return t;
		}
		
		return map[tx][ty];
	}
	
	public void DrawCollisionRectangles(SpriteBatch sb)
	{
		boolean hadToStart = false;
		ShapeRenderer rend = new ShapeRenderer();
		
		if(!sb.isDrawing())
			{sb.begin(); hadToStart = true;}
		
		rend.begin(ShapeType.Filled);
		rend.setProjectionMatrix(GlobalVariables.maincamera.combined);
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				rend.rect(map[x][y].getX() * 32, map[x][y].getY() * 32, 32, 32);	
			}
		}
		
		rend.end();
		if(hadToStart)
			sb.end();
	}
	
	public Tile getTileAt_AutoRoundX(float x, float y)
	{	
		int tx = (int)Math.floor((double)x / 32) + 1;
		int ty = (int)Math.floor((double) y / 32);
		
		if(tx < 0 || ty < 0 || tx > map.length - 1 || ty > map[0].length - 1)
		{
			Tile t = new Tile(0,0,32,32,TileType.Stone,false);
			return t;
		}
		
		return map[tx][ty];
	}
	
	public Tile getTileAt_AutoRoundY(float x, float y)
	{
		int tx = (int)Math.floor((double)x / 32);
		int ty = (int)Math.floor((double) y / 32) - 1;
		
		if(tx < 0 || ty < 0 || tx > map.length - 1 || ty > map[0].length - 1)
		{
			Tile t = new Tile(0,0,32,32,TileType.Stone,false);
			return t;
		}
		
		return map[tx][ty];
	}
	
	public Tile getTileAt(int tx, int ty)
	{
		if(tx < 0 || ty < 0 || tx > map.length - 1 || ty > map[0].length - 1)
		{
			Tile t = new Tile(0,0,32,32,TileType.Stone,false);
			return t;
		}
		return map[tx][ty];
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
		//render a temp player
		tempPlayer.render(sb);
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
