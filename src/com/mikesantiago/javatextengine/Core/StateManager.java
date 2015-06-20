package com.mikesantiago.javatextengine.Core;

import com.mikesantiago.javatextengine.states.Credits;
import com.mikesantiago.javatextengine.states.Editor;
import com.mikesantiago.javatextengine.states.Game;
import com.mikesantiago.javatextengine.states.MainMenu;

public class StateManager 
{
	public static enum GameState 
	{
		MAINMENU, GAME, EDITOR, CREDITS
	}
	
	public static GameState curGameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;
	public static Credits credits;
	
	public static void Update()
	{
		switch(curGameState)
		{
		case MAINMENU:
			if(mainMenu == null)
				mainMenu = new MainMenu();
			mainMenu.Update();
			break;
		case GAME:
			if(game == null)
				game = new Game();
			game.Update();
			break;
		case EDITOR: //probably going to remove
			break;
		case CREDITS:
			break;
		}
	}
	
	public static void SetGameState(GameState newState)
	{
		curGameState = newState;
	}
	
	public static GameState ReturnCurrentGameState()
	{
		return curGameState;
	}
	
	public static void WriteMapsToFile()
	{
		game.WriteMapToFile();
	}
	
	public static CorePlayer GetCorePlayer()
	{
		if(game != null)
			return game.GetPlayer();
		else
			return null;
	}
}
