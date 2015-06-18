package com.mikesantiago.javatextengine.Core;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import com.mikesantiago.javatextengine.Donger;



public class WindowManager
{
	public static final int SCREEN_WIDTH = 640, SCREEN_HEIGHT = 480;
	public static boolean DEBUG = false;
	
	private static String[] splash = 
		{
			"Never going anywhere!", "Not quite an RPG!", "MINECRAFT ALPHA 1.0.1_25", "Not Terraria..or Minecraft", "Not a Tower Defense Either",
			"There will never be building!", "Joey is fat ;)", "SMBXXX!", "If you've seen these all, you have no life!", "Macs > all",
			"At least we have splash screens..", "Scott's a hipster but Folsom's a mole rat!", "There's %s of these!", "public Enemy(boolean numberOne){ this.numberOne = true }",
			"\"What the hell is a footcandle?!\"", "MLG Yearbook", "Not sure if obfuscated", "DADDY!!!!!", Donger.donger
		};
	
	private static Random ran = new Random();
	
	public static void BeginWindow()
	{
		ran.setSeed(System.currentTimeMillis());
		
		try
		{
			Display.setDisplayMode(new DisplayMode(640, 480));
			if(!DEBUG)
				Display.setTitle("Java RPG Engine - v0.0.0.1 - " + String.format(splash[ran.nextInt(splash.length)], splash.length));
			/*DisplayMode displayMode = null;
	        DisplayMode[] modes = Display.getAvailableDisplayModes();

	         for (int i = 0; i < modes.length; i++)
	         {
	             if (modes[i].getWidth() == SCREEN_WIDTH
	             && modes[i].getHeight() == SCREEN_HEIGHT
	             && modes[i].isFullscreenCapable())
	               {
	                    displayMode = modes[i];
	               }
	         }
			Display.setDisplayModeAndFullscreen(displayMode);*/ //Disabled because it's broken on Linux, I use Linux :P
			Display.create();
			
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Error trying to initialize the display: " + e.getMessage(),
					"Display Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		//Initiates OpenGL 
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 1, -1); //camera
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//
		try {
			Display.setIcon(new ByteBuffer[] {
			       new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/" + SimpleGLDrawer.texturePackFolder + "/placeholder-icon.png")), false, false, null),
			       new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/" + SimpleGLDrawer.texturePackFolder + "/placeholder-icon.png")), false, false, null)
			       });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}