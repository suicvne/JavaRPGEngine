package com.mikesantiago.javatextengine;

import static com.mikesantiago.javatextengine.Core.WindowManager.BeginWindow;

import java.io.File;

import javax.swing.JOptionPane;

import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mikesantiago.javatextengine.Core.Clock;
import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.StateManager;
import com.mikesantiago.javatextengine.Core.StateManager.GameState;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.launcher.MainProgram;

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
	
	long lastFrame;
	int fps;
	long lastFps;
	
	public Boot()
	{
		BeginWindow();
		
		//Game game = new Game();
		
		getDelta();
		lastFps = getTime();
		while(!Display.isCloseRequested())
		{
			//Update here
			Clock.Update();
			
			StateManager.Update();
			updateFPS();
			//update screen, 60fps
			if(WindowManager.DEBUG)
			{
				/*String debugTitle = String.format("%s enemy(ies); MX:%s MY:%s; TGX:%s TGY: %s; %s FPS", 
						1,
						Mouse.getX(), Mouse.getY(),
						((int)Math.floor(Mouse.getX() / 32)), 
						((int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32)),
						fps
						/*StateManager.GetCorePlayer().getCurrentTile()
				);
				Display.setTitle(debugTitle);*/
			}
			
			
			
			Display.update();
			Display.sync(75);
		}
		
		if(StateManager.curGameState == GameState.GAME)
			StateManager.WriteMapsToFile();
		
		System.out.println("\n\nend game\n");
		Display.destroy();
	}
	
	/** 
     * Calculate how many milliseconds have passed 
     * since last frame.
     * 
     * @return milliseconds passed since last frame 
     */
    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;
      
        return delta;
    }
     
    /**
     * Get the accurate system time
     * 
     * @return The system time in milliseconds
     */
    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }
     
    /**
     * Calculate the FPS and set it in the title bar
     */
    public void updateFPS() {
        if (getTime() - lastFps > 1000) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFps += 1000;
        }
        fps++;
    }
	
	/**
	 * Runs the game, also sets the proper directories for natives/libraries.
	 * @param args Command line arguments
	 */
	public static void main(String[] args)
	{
		MainProgram.LinkLWJGLLibs();
		
		if(args.length > 0)
		{
			if(args[0].contains("-texturePack"))
			{
				String[] splitArgs = args[0].split(" ");
				SimpleGLDrawer.texturePackFolder = args[1];
				if(args.length > 2)
					if(args[2].contains("-debug"))
						WindowManager.DEBUG = true;
			}
			else
				SimpleGLDrawer.texturePackFolder = "placeholder";
			
			if(args.length > 0)
			{
				if(args[0].contains("-debug"))
					WindowManager.DEBUG = true;
				else if(args[1].contains("-debug"))
					WindowManager.DEBUG = true;
			}
		}
		//SimpleGLDrawer.texturePackFolder = "minecraft";
		new Boot();
	}
}