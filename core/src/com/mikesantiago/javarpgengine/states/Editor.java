package com.mikesantiago.javarpgengine.states;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikesantiago.javarpgengine.GDX2;
import com.mikesantiago.javarpgengine.core.BasicInputProcessor;
import com.mikesantiago.javarpgengine.core.God;
import com.mikesantiago.javarpgengine.core.TileGrid;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;

public class Editor 
{
	private God god;
	private TileGrid map;
	private BasicInputProcessor bip;
	//
	
	public Editor()
	{
		map = new TileGrid(2, 2); //just load a basic map for now
		god = new God(map);
		LoadMap();
	}
	
	public void LoadMap()
	{
		map.ReadTileGridInformation();
		god = new God(map);
	}
	
	private void TryLoadingSaves()
	{
		if(new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2").exists() && new File(GlobalVariables.decodedPath + "/save/test/tiles.jte2meta").exists())
		{
			map.ReadTileGridInformation();
		}
		else
			System.out.println("no maps exists and that's alright");
	}
	
	public void TryWritingSaves()
	{
		map.WriteTileGridInformation();
	}
	
	public void update()
	{
		god.update();
	}
	
	public void render(SpriteBatch sb)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		sb.setProjectionMatrix(GlobalVariables.maincamera.combined); //cool

		map.Draw(sb);
		god.render(sb);
		
		sb.begin();
		sb.setProjectionMatrix(GlobalVariables.hudcam.combined);
		String debugFormat = String.format("Placing: %s\nTotal World Size: %s x %s\nPlayer Position: %s, %s", 
				god.getCurrentTile(), 
				map.getTotalWidth(), 
				map.getTotalHeight(),
				map.tempPlayer().GetTiledPositions().x, map.tempPlayer().GetTiledPositions().y);
		GlobalVariables.bmpFnt.draw(sb, debugFormat, 0, 480);
		sb.end();
	}

	public God getGod() {return god;}
}
