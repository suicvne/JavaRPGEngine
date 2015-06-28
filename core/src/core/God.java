package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mikesantiago.gdx2.GDX2;


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
		int tiledX = Gdx.input.getX() / 32;
		int tiledY = ((GDX2.V_HEIGHT - Gdx.input.getY() - 1) / 32);
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT) && Gdx.input.isTouched())
		{
			mapCopy.setTile(placeableTiles[TileIndex], tiledX, tiledY, false);
		}
		else if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT))
		{
			mapCopy.setTile(placeableTiles[TileIndex], tiledX, tiledY, true);
		}
		
	}
	
	public void render(SpriteBatch sb)
	{
		update();
		
		sb.begin();
		ShapeRenderer r = new ShapeRenderer();
		r.setAutoShapeType(true);
		r.begin();
		r.setColor(1, 1, 1, 0.5f);
		r.rect((Gdx.input.getX() / 32) * 32, 
				((GDX2.V_HEIGHT - Gdx.input.getY() - 1) / 32) * 32, 
				32f, 
				32f);
		r.end();
		sb.end();
	}
}
