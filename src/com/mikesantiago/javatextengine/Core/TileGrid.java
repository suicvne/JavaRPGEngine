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
				map[x][y] = new Tile(x * 32, y *32, 32, 32, TileType.Stone);
			}
		}
	}
	
	public void Draw()
	{
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				SimpleGLDrawer.DrawTile(map[x][y]);
			}
		}
	}
	
	public void SetTile(int x, int y, TileType replaceWith)
	{
		map[x][y] = new Tile(x * 32, y * 32, 32, 32, replaceWith);
	}
	
	public Tile GetTile(int x, int y)
	{
		return map[x][y];
	}
	
	public void GenerateRandomMap()
	{
		Random ran = new Random();
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				int gen = ran.nextInt(3);
				
				map[x][y] = new Tile(x * 32, y * 32, 32, 32, TileType.values()[gen]);
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
			File saveFile = new File("save/test.jte");
			
			if(saveDir.exists() != true)
				saveDir.mkdir();
			if(saveFile.exists() != true)
				saveFile.createNewFile();
			
			FileWriter writer = new FileWriter("save/test.jte");
			
			System.out.println("writing file to '" + saveFile.getAbsolutePath() + "'");
			for(int x = 0; x < map.length; x++)
			{
				for(int y = 0; y < map[x].length; y++)
				{
					Tile retrievedTile = map[x][y];
					String formatted = String.format("%s:%s:%s:%s:%s\n", retrievedTile.getType().ordinal(), 
							retrievedTile.getX(),
							retrievedTile.getY(),
							retrievedTile.getWidth(),
							retrievedTile.getHeight());
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
			FileReader reader = new FileReader("save/test.jte");
			BufferedReader br = new BufferedReader(reader);
			
			String curLine = br.readLine();
			int curLineNumb = 0;
			
			System.out.println("reading file from '" + new File("save/test.jte").getAbsolutePath() + "'");
			
			while(curLine != null)
			{
				//System.out.println("parsing line " + curLineNumb);
				//1:0.0:0.0:32.0:32.0
				//it's in floats so we get to have fun w potential decimals
				String[] parts = curLine.split(":");
				//x, y, w, h, type
				Tile temp = new Tile(Float.parseFloat(parts[1]), 
						Float.parseFloat(parts[2]), 
						Float.parseFloat(parts[3]), 
						Float.parseFloat(parts[4]), 
						TileType.values()[Integer.parseInt(parts[0])]);
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