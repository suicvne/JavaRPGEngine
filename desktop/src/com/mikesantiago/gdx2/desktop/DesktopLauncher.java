package com.mikesantiago.gdx2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mikesantiago.gdx2.GDX2;

public class DesktopLauncher {
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		GDX2 gameInstance = new GDX2();
		config.width = 640;
		config.height = 480;
		config.title = "Java RPG Engine";
		new LwjglApplication(gameInstance, config);
		
	}
}
