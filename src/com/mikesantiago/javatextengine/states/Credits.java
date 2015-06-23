package com.mikesantiago.javatextengine.states;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer.FONTSIZE;
import com.mikesantiago.javatextengine.Core.StateManager.GameState;
import com.mikesantiago.javatextengine.Core.StateManager;
import com.mikesantiago.javatextengine.Core.TileType;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.javatextengine.UI.Button;

public class Credits 
{
	private static Texture bg;
	private Button back = new Button("back", "Back", WindowManager.SCREEN_WIDTH - (4*32), WindowManager.SCREEN_HEIGHT - 32, 4*32, 1*32);
	
	public Credits(){bg = SimpleGLDrawer.LoadFromTextureSheet(TileType.Air);}
	
	public void Update()
	{
		for(int tiledX = 0; tiledX < 20; tiledX++)
		{	
			for(int tiledY = 0; tiledY < 15; tiledY++)
			{
				SimpleGLDrawer.DrawRectangleDarkened(bg, tiledX * 32, tiledY * 32, 32, 32);
			}
		}
		
		String msg = "Java RPG Engine";
		SimpleGLDrawer.DrawText(msg, 
				(float)(WindowManager.SCREEN_HEIGHT - (32 * 10.5)), 
				0, 
				FONTSIZE.LARGE, 
				Color.white);
		
		SimpleGLDrawer.DrawText("Code by Mike Santiago", 0, 4 * 32, FONTSIZE.LARGE, Color.white);
		SimpleGLDrawer.DrawText("With help from Bryan from the Indie Programmer", 0, 5 * 32, FONTSIZE.SMALL, Color.white);
		SimpleGLDrawer.DrawText("Tiles by Jaclyn Jenson", 0, 8 * 32, FONTSIZE.LARGE, Color.white);
		SimpleGLDrawer.DrawText("Font \"Arcadepix\" by Reekee", 0, 9 * 32, FONTSIZE.LARGE, Color.white);
		
		SimpleGLDrawer.DrawText("Copyright (c) Mike Santiago, 2015\nhttp://www.github.com/Luigifan/JavaRPGEngine", 
				0, 
				WindowManager.SCREEN_HEIGHT - SimpleGLDrawer.LARGE_SIZE, 
				FONTSIZE.SMALL, Color.white);
		
		back.Draw();
		
		if(Mouse.isButtonDown(0) && back.isMouseInside())
			StateManager.SetGameState(GameState.MAINMENU);
	}
}
