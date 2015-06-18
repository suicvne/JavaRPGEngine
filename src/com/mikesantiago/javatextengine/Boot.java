package com.mikesantiago.javatextengine;

import static com.mikesantiago.javatextengine.Core.WindowManager.BeginWindow;

import java.io.File;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;

import com.mikesantiago.javatextengine.Core.Clock;
import com.mikesantiago.javatextengine.Core.Enemy;
import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.TileGrid;

/**
 * Handles the main screen booting, etc
 * @author mike
 *
 */
public class Boot
{
	public static OSType CurrentOS = OSDetection.GetCurrentOSName();
	public static final int SCREEN_WIDTH = 640;
	public static final int SCREEN_HEIGHT = 480;
	
	public Boot()
	{
		BeginWindow();
		
		
		File tempSave = new File("save/test.jte");
		TileGrid grid = new TileGrid();
		
		if(tempSave.exists() == false)
		{
			grid.GenerateRandomMap();
		}
		else
		{
			grid.ReadFromFile();
		}
		
		Enemy e = new Enemy(grid.GetTile(3, 3), 32, 32, 3, 3, 2f, SimpleGLDrawer.QuickLoad("placeholder-enemy"));
		
		while(!Display.isCloseRequested())
		{
			//Update here
			Clock.Update();
			e.Update();
			
			//Draw here
			//0,0 128,128
			grid.Draw();
			e.Draw();
			//update screen, 60fps
			Display.update();
			Display.sync(60);
		}
		
		grid.WriteMapToFile();
	}
	
	/**
	 * Runs the game, also sets the proper directories for natives/libraries.
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			System.setProperty("java.library.path", "lib/jars");
		}
		catch(UnsatisfiedLinkError e)
		{
			System.out.println("just a standard error, no need to relink paths");
		}
		System.out.println("current OS is " + CurrentOS + ", trying for native libs in\n" + new File("lib/natives/" + CurrentOS).getAbsolutePath());
		switch(CurrentOS)
		{
		//to be fair, i don't even know if this will work
		//scratch that, it actually seems to work!!
		case windows:
			System.out.println("set property org.lwjgl.librarypath to " + new File("lib/natives/windows").getAbsolutePath());
			System.setProperty("org.lwjgl.librarypath", new File("lib/natives/windows").getAbsolutePath());
			break;
		case macosx:
			System.out.println("set property org.lwjgl.librarypath to " + new File("lib/natives/macosx").getAbsolutePath());
			System.setProperty("org.lwjgl.librarypath", new File("lib/natives/macosx").getAbsolutePath());
			break;
		case linux:
			System.out.println("set property org.lwjgl.librarypath to " + new File("lib/natives/linux").getAbsolutePath());
			System.setProperty("org.lwjgl.librarypath", new File("lib/natives/linux").getAbsolutePath());
			break;
		case other:
			JOptionPane.showMessageDialog(null, 
					"Java Text Engine is not currently supported on your OS: " + System.getProperty("os.name"), 
					"Operating System Not Supported", 
					JOptionPane.ERROR_MESSAGE);
			break;
		}
		
		if(args.length > 0)
		{
			if(args[0].contains("-texturePack"))
			{
				String[] splitArgs = args[0].split(" ");
				SimpleGLDrawer.texturePackFolder = args[1];
			}
			else
				SimpleGLDrawer.texturePackFolder = "placeholder";
		}
		//SimpleGLDrawer.texturePackFolder = "minecraft";
		new Boot();
	}
}