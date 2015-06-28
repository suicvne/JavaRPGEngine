package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
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
		mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		mousePos = GDX2.maincamera.unproject(mousePos);
		GDX2.maincamera.update();
		
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
		update();
		
		
		
		sb.begin();
		ShapeRenderer r = new ShapeRenderer();
		r.setAutoShapeType(true);
		r.begin();
		r.setColor(1, 1, 1, 0.5f);
		/*{
			r.rect(((Gdx.input.getX() / 32) * 32), 
					((GDX2.V_HEIGHT - Gdx.input.getY() - 1) / 32) * 32, 
					32f, 
					32f);	
		}*/
		//r.rect((float)Math.floor((touchPos.x / 32) * 32), (float)Math.floor((touchPos.y / 32) * 32), 32f, 32f);
		//r.rect((float)(Math.floor(mousePos.x / 32) * 32), (float)(Math.floor((mousePos.y / 32)) * 32), 32f, 32f);
		r.end();
		sb.end();
	}
}
