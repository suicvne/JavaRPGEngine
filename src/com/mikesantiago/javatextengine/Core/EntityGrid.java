package com.mikesantiago.javatextengine.Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EntityGrid 
{
	private Entity[][] map;
	private TileGrid grid;
	
	public EntityGrid(TileGrid grid)
	{
		this.grid = grid;
		
		map = new Entity[grid.getMaxX()][grid.getMaxY()];
	}
	
	public void AddEntity(Entity entity, int tiledX, int tiledY)
	{
		map[tiledX][tiledY] = entity;
	}
	
	public Entity GetEntityAt(int tiledX, int tiledY)
	{
		return map[tiledX][tiledY];
	}
	
	public Entity ReturnFirstOf(Entity entity)
	{
		Entity returnVal = null;
		
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				if(map[x][y].getID() == entity.getID())
					returnVal = map[x][y];
			}
		}
		
		return returnVal;
	}
	
	public CorePlayer ReturnPlayerStub()
	{
		CorePlayer returnVal = null;
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				if(map[x][y] != null)
					if(map[x][y].getID() == -1)
						returnVal = (CorePlayer)map[x][y];
			}
		}
		return returnVal;
	}
	
	public void Draw()
	{
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				if(map[x][y] != null)
					if(map[x][y].getID() != -1)
						map[x][y].Draw();
			}
		}
	}
	
	public void Update()
	{
		for(int x = 0; x < map.length; x++)
		{
			for(int y = 0; y < map[x].length; y++)
			{
				if(map[x][y] != null)
					if(map[x][y].getID() != -1)
						map[x][y].Update();
			}
		}
	}
	
	public void WriteToFile()
	{

		try 
		{
			
			File saveDir = new File("save");
			File saveFile = new File("save/entities.jte");
			
			if(saveDir.exists() != true)
				saveDir.mkdir();
			if(saveFile.exists() != true)
				saveFile.createNewFile();
			
			FileWriter writer = new FileWriter("save/entities.jte");
			
			System.out.println("writing file to '" + saveFile.getAbsolutePath() + "'");
			for(int x = 0; x < map.length; x++)
			{
				for(int y = 0; y < map[x].length; y++)
				{
					Entity retrievedEntity = map[x][y];
					if(retrievedEntity != null)
					{
						String formatted = String.format("%s:%s:%s", retrievedEntity.getID(), retrievedEntity.getX(), retrievedEntity.getY());
						writer.write(formatted);
					}
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
					FileReader reader = new FileReader("save/entities.jte");
					BufferedReader br = new BufferedReader(reader);
					
					String curLine = br.readLine();
					
					System.out.println("reading file from '" + new File("save/entities.jte").getAbsolutePath() + "'");
					
					while(curLine != null)
					{
						//it's in floats so we get to have fun w potential decimals
						String[] parts = curLine.split(":");
						//x, y, w, h, type
						
						Entity temp;
						float x, y;
						int id;
						id = Integer.parseInt(parts[0]);
						x = Float.parseFloat(parts[1]);
						y = Float.parseFloat(parts[2]);
						
						temp = EntityMapping.GetEntityFromID(id, x, y, grid);
						
						
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
