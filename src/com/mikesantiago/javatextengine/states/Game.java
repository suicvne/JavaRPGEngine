package com.mikesantiago.javatextengine.states;

import java.io.File;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.CorePlayer;
import com.mikesantiago.javatextengine.Core.EntityGrid;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.TileGrid;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer.FONTSIZE;

public class Game 
{
	private TileGrid grid;
	private EntityGrid eGrid;
	private CorePlayer p = new CorePlayer(grid, 0, 0, null, -1);
	private boolean loading = false;
	private Texture bg;
	
	public Game(TileGrid grid, EntityGrid eGrid)
	{
		this.grid = grid;
		this.eGrid = eGrid;
		
		bg = SimpleGLDrawer.QuickLoad("tile-air");
		
		LoadExSave();
	}
	
	public Game()
	{
		LoadExSave();
	}
	
	private void LoadExSave()
	{
		loading = true;
		
		System.out.println("Loading map..please be patien. BufferedImages are slow ;)");
		
		File tempTilesSave = new File("save/tiles.jte");
		File tempEntitiesSave = new File("save/entities.jte");
		
		grid = new TileGrid();
		eGrid = new EntityGrid(grid);
		
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
		
		loading = false;
	}
	
	
	public void Update()
	{
		if(!loading)
		{
			grid.Draw();
			eGrid.Update();
		
			p.Update(); //Player is manually updated
			p.Draw(); //And manually drawn
		
			eGrid.Draw();
		}
		else
		{
			for(int tiledX = 0; tiledX < 20; tiledX++)
			{	
				for(int tiledY = 0; tiledY < 15; tiledY++)
				{
					SimpleGLDrawer.DrawRectangleDarkened(bg, 
							tiledX * 32, 
							tiledY * 32, 
							32, 32);
				}
			}
			SimpleGLDrawer.DrawText("Loading map..", (float)(WindowManager.SCREEN_HEIGHT - (32 * 10.5)), 
					0, FONTSIZE.LARGE, Color.yellow);
		}
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
