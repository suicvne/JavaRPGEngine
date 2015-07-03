package com.mikesantiago.javarpgengine.handlers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikesantiago.javarpgengine.core.BasicInputProcessor;
import com.mikesantiago.javarpgengine.core.Content;
import com.mikesantiago.javarpgengine.core.GameStateManager;

public class GlobalVariables 
{
	public static SpriteBatch sb;
	public static Content content;
	public static final int SCALE = 2;
	public static int V_WIDTH = 640;
	public static int V_HEIGHT = 480;

	public static BasicInputProcessor bip;
	public static GameStateManager gsm;
	public static BitmapFont bmpFnt;
	public static OrthographicCamera maincamera;
	public static OrthographicCamera hudcam;
	public static boolean running = true;
	public static String decodedPath = "";
}
