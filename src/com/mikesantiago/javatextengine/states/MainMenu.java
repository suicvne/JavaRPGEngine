package com.mikesantiago.javatextengine.states;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;

public class MainMenu 
{
	private Texture bg;
	
	public MainMenu()
	{
		bg = SimpleGLDrawer.QuickLoad("tile-stone"); //we'll tile this, like Minecraft does
	}
	
	public void Update()
	{	
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		for(int tiledX = 0; tiledX < 20; tiledX++)
		{	
			for(int tiledY = 0; tiledY < 15; tiledY++)
			{
				SimpleGLDrawer.DrawRectangleDarkened(bg, tiledX * 32, tiledY * 32, 32, 32);
			}
		}
		
		SimpleGLDrawer.DrawText("Hello world", 0, 0, 24, Color.cyan);
	}
}
