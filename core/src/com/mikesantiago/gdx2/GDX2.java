package com.mikesantiago.gdx2;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import core.OSDetection;
import core.OSDetection.OSType;
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
	public static boolean running = true;
	public static String decodedPath = "";
	@Override
	public void create () 
	{
		/**
		 * Get proper paths, mainly for OS X
		 */
		String path = GDX2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		
		try {
			decodedPath = URLDecoder.decode(new File(path).getParentFile().getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO not that this should fail but u know
			e.printStackTrace();
		}
		
		System.out.println("current directory is " + decodedPath);
		
		Keyboard.enableRepeatEvents(true);
		
		sb = new SpriteBatch();
		img = new Texture(new File(decodedPath + "/res/offscreen.jpg").getAbsolutePath());
		content = new Content();
		content.loadTexture(new File(decodedPath + "/res/textures.png").getAbsolutePath(), "global-textures");
		map = new TileGrid();
		god = new God(map);
		bip = new BasicInputProcessor();
		Gdx.input.setInputProcessor(bip);
		FileHandle f = new FileHandle(new File(decodedPath + "/res/ingame-font-small.fnt").getAbsolutePath());
		bmp = new BitmapFont(f, false);
		
		SetupDirs();
		
		TryLoadingSaves();
		
		SetupCameras();
	}
	
	/**
	 * 
	 * @return The original position the camera was at
	 */
	public static Vector3 GetBottomLeft()
	{
		return new Vector3(10 * 32, 7 * 32 + 16, 0);
	}
	
	private void TryLoadingSaves()
	{
		if(new File(decodedPath + "/save/test/tiles.jte2").exists() && new File(decodedPath + "/save/test/tiles.jte2meta").exists())
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
	
	private void SetupCameras()
	{
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
	
	private void SetupDirs()
	{
		File saveDir = new File(decodedPath + "/save");
		File testSaveDir = new File(decodedPath + "/save/test");
		
		if(!saveDir.exists())
		{
			System.out.println("creating save directory @ " + saveDir.getAbsolutePath());
			saveDir.mkdir();
			
			System.out.println("making test save @ " + testSaveDir.getAbsolutePath());
			testSaveDir.mkdir();
		}
		
	}
	
	@Override
	public void resize(int width, int height)
	{
		//TODO: something...
	}
	
	@Override
	public void dispose()
	{
		TryWritingSaves();
	}
	
	public void update()
	{
		god.update();
	}
	
	@Override
	public void render () 
	{
		update();
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
				
		sb.setProjectionMatrix(maincamera.combined); //cool

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
