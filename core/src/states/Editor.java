package states;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikesantiago.gdx2.GDX2;

import core.BasicInputProcessor;
import core.God;
import core.TileGrid;

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
		if(new File(GDX2.decodedPath + "/save/test/tiles.jte2").exists() && new File(GDX2.decodedPath + "/save/test/tiles.jte2meta").exists())
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
				
		sb.setProjectionMatrix(GDX2.maincamera.combined); //cool

		map.Draw(sb);
		god.render(sb);
		
		sb.begin();
		sb.setProjectionMatrix(GDX2.hudcam.combined);
		String debugFormat = String.format("Placing: %s\nTotal World Size: %s x %s\nMouse Position: %s, %s", 
				god.getCurrentTile(), 
				map.getTotalWidth(), 
				map.getTotalHeight(),
				god.getMousePos().x, god.getMousePos().y);
		GDX2.bmp.draw(sb, debugFormat, 0, 480);
		sb.end();
	}

	public God getGod() {return god;}
}
