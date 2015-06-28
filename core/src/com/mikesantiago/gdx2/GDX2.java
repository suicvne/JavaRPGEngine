package com.mikesantiago.gdx2;

import java.io.File;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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
	public static int V_WIDTH = 640;
	public static int V_HEIGHT = 480;
	public static God god;
	private TileGrid map;
	private BasicInputProcessor bip;
	private BitmapFont bmp;
	public static OrthographicCamera maincamera;
	public static OrthographicCamera hudcam;
	
	@Override
	public void create () 
	{
		Keyboard.enableRepeatEvents(true);
		
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
		
		maincamera = new OrthographicCamera(20, 15); //20 tiles across, 15 tiles down
		maincamera.translate(20, 15); //should translate it so 0, 0 is in the bottom left 
		maincamera.zoom = 32f; //size of tiles
		maincamera.position.set(new Vector3(10 * 32, 7 * 32 + 16, 0)); //xyz
		maincamera.update(); //call this whenver the camera is changed
		
		//Hudcam is CONSTANT
		hudcam = new OrthographicCamera(20, 15);
		hudcam.translate(20, 15);
		hudcam.zoom = 32f;
		hudcam.position.set(new Vector3(10 * 32, 7 * 32 + 16, 0));
		hudcam.update();
	}
	
	@Override
	public void resize(int width, int height)
	{
		//TODO: something...
	}
	
	public void update()
	{
		god.update();
	}
	
	@Override
	public void render () 
	{
		update();
		
		sb.setProjectionMatrix(maincamera.combined); //cool
		
		Gdx.gl.glClearColor(0, 0, .5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		map.Draw(sb);
		god.render(sb);
		
		sb.begin();
		sb.setProjectionMatrix(hudcam.combined);
		String debugFormat = String.format("Placing: %s\nTotal World Size: %s x %s\nMouse Position: %s, %s", 
				god.getCurrentTile(), 
				map.getTotalWidth(), 
				map.getTotalHeight(),
				god.getMousePos().x, god.getMousePos().y);
		bmp.draw(sb, debugFormat, 0, 480);
		sb.end();
	}
}
