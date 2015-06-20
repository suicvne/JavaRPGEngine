package com.mikesantiago.javatextengine.states;

import java.io.File;

import com.mikesantiago.javatextengine.Core.CorePlayer;
import com.mikesantiago.javatextengine.Core.Enemy;
import com.mikesantiago.javatextengine.Core.EntityGrid;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.TileGrid;

public class Game 
{
	private TileGrid grid;
	private EntityGrid eGrid;
	private CorePlayer p;
	
	public Game(TileGrid grid, EntityGrid eGrid)
	{
		this.grid = grid;
		this.eGrid = eGrid;
		
		LoadExSave();
	}
	
	public Game()
	{
		LoadExSave();
	}
	
	private void LoadExSave()
	{
		File tempTilesSave = new File("save/tiles.jte");
		File tempEntitiesSave = new File("save/entities.jte");
		
		this.grid = new TileGrid();
		this.eGrid = new EntityGrid(grid);
		
		if(tempTilesSave.exists() == false)
		{
			grid.GenerateRandomMap();
		}
		else
		{
			grid.ReadFromFile();
		}
		if(tempEntitiesSave.exists())
		{
			eGrid.ReadFromFile();
		}
		else
			eGrid.AddEntity(new CorePlayer(grid, (3*32), (3*32), null, -1), 3, 3);
		
		p = new CorePlayer(grid, eGrid.ReturnPlayerStub().getX(), eGrid.ReturnPlayerStub().getY(), null, -1);
	}
	
	private void ExampleData()
	{
		Enemy test = new Enemy(grid.GetTile(5, 5), 32, 32, 5, 5, 2f, SimpleGLDrawer.QuickLoad("entity-ufo"), grid);
	}
	
	public void Update()
	{
		grid.Draw();
		eGrid.Update();
		
		p.Update(); //Player is manually updated
		p.Draw(); //And manually drawn
		
		eGrid.Draw();
	}
	
	public void WriteMapToFile()
	{
		grid.WriteMapToFile();
		eGrid.WriteToFile();
	}
	
	public CorePlayer GetPlayer()
	{
		return p;
	}
	
	public void WriteMapToFile(String tilePath, String entitiesPath)
	{
		grid.WriteMapToFile(tilePath);
		eGrid.WriteToFile();
	}
}
