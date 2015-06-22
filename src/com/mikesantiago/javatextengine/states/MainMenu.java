package com.mikesantiago.javatextengine.states;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer.FONTSIZE;
import com.mikesantiago.javatextengine.Core.StateManager;
import com.mikesantiago.javatextengine.Core.StateManager.GameState;
import com.mikesantiago.javatextengine.Core.WindowManager;

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
		
		while(Keyboard.next())
		{
			if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState())
			{
				StateManager.SetGameState(GameState.GAME);
			}
			else if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState())
				StateManager.SetGameState(GameState.CREDITS);
		}
		
		SimpleGLDrawer.DrawText("Press space bar to start demo", 
				0, 
				WindowManager.SCREEN_HEIGHT - (32 * 2), 
				FONTSIZE.LARGE, 
				Color.yellow);
		SimpleGLDrawer.DrawText("Press C for Credits", 
				0, 
				WindowManager.SCREEN_HEIGHT - SimpleGLDrawer.SMALL_SIZE, 
				FONTSIZE.SMALL, 
				Color.white);
	}
}
