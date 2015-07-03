package com.mikesantiago.javarpgengine.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mikesantiago.javarpgengine.states.Editor;

public class GameStateManager 
{
	private GameState curGameState = GameState.MAINMENU;
	//public static MainMenu mainMenu;
	//public static Game game;
	private Editor editor;
	//public static Credits credits;
	private SpriteBatch sb;
	
	public enum GameState
	{
		MAINMENU, GAME, EDITOR, CREDITS
	}
	
	public GameStateManager(SpriteBatch sb)
	{
		this.sb = sb;
		this.curGameState = GameState.EDITOR;
		editor = new Editor();
	}
	
	public void update()
	{
		switch(curGameState)
		{
		case EDITOR:
			if(editor == null)
				editor = new Editor();
			editor.update();
			editor.render(sb);
			break;
		}
	}
	
	public Editor getEditor(){return editor;}
	
	public void Load()
	{
		if(editor != null)
		{
			editor.LoadMap();
		}
	}
	
	public void Save()
	{
		if(editor != null)
		{
			editor.TryWritingSaves();
		}
	}
}
