package com.mikesantiago.javatextengine.Core;

import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.mikesantiago.javatextengine.Core.StateManager.GameState;

public class Input 
{
	/**
	 * Inidicates a controller is initialized/is being used.
	 */
	private static boolean initialized = false;
	
	private static Controller controller;
	
	public Input(){}
	
	public static boolean isControllerEnabled()
	{
		if(initialized && controller != null)
			return true;
		else
			return false;
	}
	
	public int InitializeControllers()
	{
		try 
		{
			Controllers.create();
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
			return -1;
		}
		Controllers.poll();
		
		ArrayList<Integer> controllerList = new ArrayList<Integer>();
		for(int cIndex = 0; cIndex < Controllers.getControllerCount(); cIndex++)
		{
			controller = Controllers.getController(cIndex);
			String format = String.format("Found controller: %s @ index: %s", controller.getName(), cIndex);
			System.out.println(format);
			controllerList.add(cIndex);
		}
		
		for (Integer i: controllerList)
		{
			controller = Controllers.getController(i);
			if(controller.getName().toLowerCase().indexOf("xbox 360") != -1)
			{
				System.out.println(String.format("controller %s is an xbox controller!", controller.getName()));
				initialized = true;
				
				System.out.println("axises");
				for(int ix = 0; ix < controller.getAxisCount(); ix++)
				{
					System.out.println(ix + ": " + controller.getAxisName(ix));
				}
				System.out.println("buttons");
				for(int ix = 0; ix < controller.getButtonCount(); ix++)
				{
					System.out.println(ix + ": " + controller.getButtonName(ix));
				}
				return 0;
			}	
		}
		controller = null;
		return 0;
	}
	
	public void Update()
	{
		if(Keyboard.getEventKey() == Keyboard.KEY_F11 && Keyboard.getEventKeyState())
		{
			WindowManager.TryForFullscreen();
		}
		switch(StateManager.ReturnCurrentGameState())
		{
		case MAINMENU:
			HandleMenuInput();
			break;
		case GAME:
			HandlePlayerInput();
			break;
		case CREDITS:
			HandleCreditsInput();
			break;
		case EDITOR:
			break;
		}
	}
	
	private void HandleMenuInput()
	{
		if(initialized && controller != null)
		{
			if(controller.isButtonPressed(7)) //8 is start
			{
				StateManager.SetGameState(GameState.GAME);
			}
			else if(controller.isButtonPressed(3))
			{
				StateManager.SetGameState(GameState.CREDITS);
			}
		}
		else
		{
			while(Keyboard.next())
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_SPACE && Keyboard.getEventKeyState())
					StateManager.SetGameState(GameState.GAME);
				else if(Keyboard.getEventKey() == Keyboard.KEY_C && Keyboard.getEventKeyState())
					StateManager.SetGameState(GameState.CREDITS);
			}
		}
	}
	
	private void HandleCreditsInput()
	{
		if(initialized && controller != null)
		{
			if(controller.isButtonPressed(6) || controller.isButtonPressed(1))
			{
				StateManager.SetGameState(GameState.MAINMENU);
			}
		}
		else
		{
			while(Keyboard.next())
			{
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState())
					StateManager.SetGameState(GameState.MAINMENU);
			}
		}
	}
	
	private void HandlePlayerInput()
	{
		CorePlayer p = StateManager.GetCorePlayer();
		if(p != null)
		{
			if(controller != null) //indicates a controller is active
			{
				boolean acc = true;
				while(acc)
				{
					controller.poll();
					if(controller.getPovX() == 1)
					{
						p.MovePlayer(EntityDirection.Right);
						controller.poll();
					}
					else if(controller.getPovX() == -1)
					{
						p.MovePlayer(EntityDirection.Left);
					}
					else if(controller.getPovY() == -1)
					{
						p.MovePlayer(EntityDirection.Up);
					}
					else if(controller.getPovY() == 1)
					{	
						p.MovePlayer(EntityDirection.Down);
					}
					acc = false;
				}
			}
			else
			{
				if(Mouse.isButtonDown(0))
					p.PlaceTile();
				else if(Mouse.isButtonDown(1))
					p.PlaceBackgroundTile();
				
				int dWheel = Mouse.getDWheel();
			    if (dWheel < 0) 
			    	p.AdvanceTileIndex(-1);
			    else if(dWheel > 0)
			    	p.AdvanceTileIndex(1);
			    
				while(Keyboard.next())
				{
					if(Keyboard.getEventKey() == Keyboard.KEY_UP && Keyboard.getEventKeyState())
					{
						p.MovePlayer(EntityDirection.Up);
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_DOWN && Keyboard.getEventKeyState())
					{
						p.MovePlayer(EntityDirection.Down);
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_LEFT && Keyboard.getEventKeyState())
					{
						p.MovePlayer(EntityDirection.Left);
					}
					else if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState())
					{
						p.MovePlayer(EntityDirection.Right);
					}
				}
			}
		}
		
	}
		
	
	public static boolean getInitialized()
	{
		return initialized;
	}
}
