package com.mikesantiago.javatextengine.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TileGrid 
{
	public Tile[][] map;
	
	public TileGrid()
	{
		map = new Tile[20][15];
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				map[x][y] = new Tile(x * 32, y *32, 32, 32, TileType.Stone, false);
			}
		}
	}
	
	public int getMaxX()
	{
		return map.length;
	}
	
	public int getMaxY()
	{
		return map[1].length;
	}
	
	public void Draw()
	{
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				if(map[x][y].getIsFloorTile())
				{
					SimpleGLDrawer.DrawFloorTile(map[x][y]);
				}
				else
					SimpleGLDrawer.DrawTile(map[x][y]);
			}
		}
	}
	
	public void SetTile(int x, int y, TileType replaceWith)
	{
		map[x][y] = new Tile(x * 32, y * 32, 32, 32, replaceWith, false);
	}
	
	public void SetTile(int x, int y, TileType replaceWith, boolean isFloorTile)
	{
		map[x][y] = new Tile(x * 32, y * 32, 32, 32, replaceWith, isFloorTile);
	}
	
	public Tile GetTile(int tiledX, int tiledY)
	{
		return map[tiledX][tiledY];
	}
	
	public void GenerateRandomMap()
	{
		Random ran = new Random();
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				int gen = ran.nextInt(2);
				
				map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.values()[gen], false);
			}
		}
	}
	
	public void WriteMapToFile()
	{
		//Format
		//0:32:0:32:32
		//Tile index, x, y, width, height
		
		try 
		{
			
			File saveDir = new File("save");
			File saveFile = new File("save/tiles.jte");
			
			if(saveDir.exists() != true)
				saveDir.mkdir();
			if(saveFile.exists() != true)
				saveFile.createNewFile();
			
			FileWriter writer = new FileWriter("save/tiles.jte");
			
			System.out.println("writing file to '" + saveFile.getAbsolutePath() + "'");
			for(int x = 0; x < map.length; x++)
			{
				for(int y = 0; y < map[x].length; y++)
				{
					Tile retrievedTile = map[x][y];
					int isFloor = (retrievedTile.getIsFloorTile()) ? 1 : 0;
					String formatted = String.format("%s:%s:%s:%s:%s:%s\n", retrievedTile.getType().ordinal(), 
							retrievedTile.getX(),
							retrievedTile.getY(),
							retrievedTile.getWidth(),
							retrievedTile.getHeight(),
							isFloor);
					writer.write(formatted);
				}
			}
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WriteMapToFile(String fileToWrite)
	{
		//Format
		//0:32:0:32:32
		//Tile index, x, y, width, height
		
		try 
		{			
			FileWriter writer = new FileWriter(fileToWrite);
			
			System.out.println("writing file to '" + fileToWrite + "'");
			for(int x = 0; x < map.length; x++)
			{
				for(int y = 0; y < map[x].length; y++)
				{
					Tile retrievedTile = map[x][y];
					int isFloor = (retrievedTile.getIsFloorTile()) ? 1 : 0;
					String formatted = String.format("%s:%s:%s:%s:%s:%s\n", retrievedTile.getType().ordinal(), 
							retrievedTile.getX(),
							retrievedTile.getY(),
							retrievedTile.getWidth(),
							retrievedTile.getHeight(),
							isFloor);
					writer.write(formatted);
				}
			}
			writer.flush();
			writer.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReadFromFile()
	{
		//So, to get the actual position of the tile on the map, you use
		//x*32
		//So in theory, to get the tiled positioning, you would use
		//x/32
		try
		{
			FileReader reader = new FileReader("save/tiles.jte");
			BufferedReader br = new BufferedReader(reader);
			
			String curLine = br.readLine();
			int curLineNumb = 0;
			
			System.out.println("reading file from '" + new File("save/tiles.jte").getAbsolutePath() + "'");
			
			while(curLine != null)
			{
				//System.out.println("parsing line " + curLineNumb);
				//1:0.0:0.0:32.0:32.0
				//it's in floats so we get to have fun w potential decimals
				String[] parts = curLine.split(":");
				//x, y, w, h, type
				
				Tile temp;
				if(parts.length == 5)
				{
					temp = new Tile(Float.parseFloat(parts[1]), 
							Float.parseFloat(parts[2]), 
							Float.parseFloat(parts[3]), 
							Float.parseFloat(parts[4]), 
							TileType.values()[Integer.parseInt(parts[0])], false);
				}
				else if(parts.length == 6)
				{
					boolean isFloor;
					int isFloorInt = Integer.parseInt(parts[5]);
					if(isFloorInt == 0)
						isFloor = false;
					else
						isFloor = true;
					
					temp = new Tile(Float.parseFloat(parts[1]), 
							Float.parseFloat(parts[2]), 
							Float.parseFloat(parts[3]), 
							Float.parseFloat(parts[4]), 
							TileType.values()[Integer.parseInt(parts[0])], isFloor);
				}
				else
					temp = new Tile(0, 0, 32, 32, TileType.Air, false);
				
				int tiledX, tiledY;
				
				if(temp.getX() != 0)
					tiledX = (int) (temp.getX() / 32);
				else
					tiledX = 0;
				
				if(temp.getY() != 0)
					tiledY = (int) (temp.getY() / 32);
				else
					tiledY = 0;
				
				map[tiledX][tiledY] = temp;
				
				//
				curLine = br.readLine();
				curLineNumb++;
			}
			br.close();
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void ReadFromFile(String toReadFrom)
	{
		//So, to get the actual position of the tile on the map, you use
		//x*32
		//So in theory, to get the tiled positioning, you would use
		//x/32
		try
		{
			FileReader reader = new FileReader(toReadFrom);
			BufferedReader br = new BufferedReader(reader);
			
			String curLine = br.readLine();
			int curLineNumb = 0;
			
			System.out.println("reading file from '" + new File(toReadFrom).getAbsolutePath() + "'");
			
			while(curLine != null)
			{
				//System.out.println("parsing line " + curLineNumb);
				//1:0.0:0.0:32.0:32.0
				//it's in floats so we get to have fun w potential decimals
				String[] parts = curLine.split(":");
				//x, y, w, h, type
				Tile temp;
				if(parts.length == 5)
				{
					temp = new Tile(Float.parseFloat(parts[1]), 
							Float.parseFloat(parts[2]), 
							Float.parseFloat(parts[3]), 
							Float.parseFloat(parts[4]), 
							TileType.values()[Integer.parseInt(parts[0])], false);
				}
				else
				{
					boolean isFloor;
					int isFloorInt = Integer.parseInt(parts[5]);
					if(isFloorInt == 0)
						isFloor = false;
					else
						isFloor = true;
					
					temp = new Tile(Float.parseFloat(parts[1]), 
							Float.parseFloat(parts[2]), 
							Float.parseFloat(parts[3]), 
							Float.parseFloat(parts[4]), 
							TileType.values()[Integer.parseInt(parts[0])], isFloor);
				}
				int tiledX, tiledY;
				
				if(temp.getX() != 0)
					tiledX = (int) (temp.getX() / 32);
				else
					tiledX = 0;
				
				if(temp.getY() != 0)
					tiledY = (int) (temp.getY() / 32);
				else
					tiledY = 0;
				
				map[tiledX][tiledY] = temp;
				
				//
				curLine = br.readLine();
				curLineNumb++;
			}
			br.close();
			reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
