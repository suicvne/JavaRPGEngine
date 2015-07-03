package com.mikesantiago.javarpgengine.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;


public class God
{
	private TileGrid mapCopy;
	private TileType[] placeableTiles;
	private int TileIndex;
	
	public God(TileGrid grid)
	{
		this.mapCopy = grid;
		TileIndex = 0;
		
		this.placeableTiles = new TileType[TileType.values().length];
		for(int i = 0; i < TileType.values().length; i++)
		{
			placeableTiles[i] = TileType.values()[i];
		}
	}
	
	public void setPlacingTile(TileType tile)
	{
		for(int i = 0; i < placeableTiles.length; i++)
		{
			if(placeableTiles[i] == tile)
			{
				TileIndex = i;
			}
		}
	}
	public TileType getCurrentTile()
	{
		return placeableTiles[TileIndex];
	}
	public void ChangeTileIndex(int dir)
	{
		if(dir == 1)
		{
			if(TileIndex >= placeableTiles.length - 1)
				TileIndex = 0;
			else
				TileIndex++;
		}
		else if(dir == -1)
		{
			if(TileIndex <= 0)
				TileIndex = placeableTiles.length - 1;
			else
				TileIndex--;
		}
	}
	
	public void update()
	{
		mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		mousePos = GlobalVariables.maincamera.unproject(mousePos);
		GlobalVariables.maincamera.update();
		
		int tiledX = (int)(mousePos.x / 32);
		int tiledY = (int)(mousePos.y / 32);
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT))
		{
			/*touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			
			float deltaX, deltaY;
			deltaX = (float)Gdx.input.getDeltaX();
			deltaY = (float)Gdx.input.getDeltaY();
			GDX2.maincamera.translate(-deltaX, deltaY, 0);
			
			touchPos = GDX2.maincamera.unproject(touchPos);*/ //this code is only useful on android
			
			
			mapCopy.setTile(placeableTiles[TileIndex], tiledX, tiledY, false);
		}
		else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			mapCopy.setTile(placeableTiles[TileIndex], tiledX, tiledY, true);
		}
		
	}
	
	Vector3 touchPos = new Vector3(0, 0, 0);
	Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
	
	public Vector3 getMousePos() {return mousePos;}
	
	public void render(SpriteBatch sb)
	{	
		sb.begin();
		sb.end();
	}
}
