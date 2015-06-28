package com.mikesantiago.gdx2;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import core.BasicInputProcessor;
import core.Content;
import core.God;
import core.TileGrid;

public class GDX2 extends ApplicationAdapter 
{
	SpriteBatch sb;
	Texture img;
	public static Content content;
	public static final int SCALE = 2;
	public static final int V_WIDTH = 640;
	public static final int V_HEIGHT = 480;
	public static God god;
	private TileGrid map;
	private BasicInputProcessor bip;
	private BitmapFont bmp;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		content = new Content();
		content.loadTexture(new File("res/textures.png").getAbsolutePath(), "global-textures");
		map = new TileGrid();
		god = new God(map);
		bip = new BasicInputProcessor();
		Gdx.input.setInputProcessor(bip);
		
		System.out.println(new File("res/ingame-font-small.fnt").getAbsolutePath());
		
		FileHandle f = new FileHandle(new File("res/ingame-font-small.fnt").getAbsolutePath());
		
		bmp = new BitmapFont(f, false);
	}
	
	
	public void update()
	{
		god.update();
	}
	
	@Override
	public void render () 
	{
		//Update first, then render
		update();
		
		Gdx.gl.glClearColor(0, 0, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.Draw(sb);
		god.render(sb);
		
		sb.begin();
		bmp.draw(sb, "Placing: " + god.getCurrentTile(), 0, V_HEIGHT);
		sb.end();
	}
}
