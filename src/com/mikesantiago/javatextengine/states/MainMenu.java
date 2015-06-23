package com.mikesantiago.javatextengine.states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.Input;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer.FONTSIZE;
import com.mikesantiago.javatextengine.Core.StateManager.GameState;
import com.mikesantiago.javatextengine.Core.StateManager;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.javatextengine.UI.Button;

public class MainMenu 
{
	private Texture bg;
	private Button[] buttons;
	
	public MainMenu()
	{
		bg = SimpleGLDrawer.QuickLoad("tile-stone"); //we'll tile this, like Minecraft does
		buttons = new Button[3];
		buttons[0] = new Button("play", "Play", 6*32, 6*32, 8*32, 1*32);
		buttons[1] = new Button("credits", "Credits", 6*32, 8*32, 8*32, 1*32);
		buttons[2] = new Button("quit", "Quit", 6*32, 10*32, 8*32, 1*32);
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
		/*if(Input.isControllerEnabled())
		{
			SimpleGLDrawer.DrawText("Press start to start demo", 
					0, 
					WindowManager.SCREEN_HEIGHT - (32 * 2), 
					FONTSIZE.LARGE, 
					Color.yellow);
				SimpleGLDrawer.DrawText("Press Y for Credits", 
					0, 
					WindowManager.SCREEN_HEIGHT - SimpleGLDrawer.SMALL_SIZE, 
					FONTSIZE.SMALL, 
					Color.white);
				SimpleGLDrawer.DrawText("WARNING: Controller support is super buggy.", 0, 0, FONTSIZE.SMALL, Color.white);
		}
		else
		{
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
		}*/
		
		SimpleGLDrawer.DrawText("Java RPG Engine - v0.0.0.1", 
				0, 
				WindowManager.SCREEN_HEIGHT - SimpleGLDrawer.SMALL_SIZE, 
				FONTSIZE.SMALL, 
				Color.white);
		SimpleGLDrawer.DrawText("THIS IS AN EARLY BUILD", 
				WindowManager.SCREEN_WIDTH - SimpleGLDrawer.GetFontWidth(FONTSIZE.SMALL, "THIS IS AN EARLY BUILD"),
						WindowManager.SCREEN_HEIGHT - SimpleGLDrawer.SMALL_SIZE,
						FONTSIZE.SMALL,
						Color.red);
		
		for(Button b: buttons)
		{
			b.Draw();
			if(Mouse.isButtonDown(0) && buttons[0].isMouseInside())
			{
				StateManager.SetGameState(GameState.GAME);
			}
			else if(Mouse.isButtonDown(0) && buttons[1].isMouseInside())
			{
				StateManager.SetGameState(GameState.CREDITS);
			}
			else if(Mouse.isButtonDown(0) && buttons[2].isMouseInside())
			{
				System.exit(0);
			}
		}
		
	}
}
