package com.mikesantiago.javatextengine;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;

import static org.lwjgl.opengl.GL11.*;

import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;

public class DisplayTest
{
	static OSType CurrentOS = OSDetection.GetCurrentOSName();
	private static TrueTypeFont comicSansMS;
	private static boolean AntiAliasFonts = true;
	
	public static void main(String[] args)
	{
		//For testing lol
		//System.setProperty("os.name", "macosx");
		System.setProperty("java.library.path", "lib/jars");
		System.out.println("current OS is " + CurrentOS + ", trying for native libs in\n" + new File("lib/natives/" + CurrentOS).getAbsolutePath());
		switch(CurrentOS)
		{
		//to be fair, i don't even know if this will work
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
		}
		
		try
		{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setTitle("Java Text Engine");
			Display.create();
		}
		catch(LWJGLException e)
		{
			System.err.println("Display wasn't initialised properly");
			System.exit(1);
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); // Resets any previous projection matrices
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		
		Font awtFont = new Font("Comic Sans MS", Font.BOLD, 24);
		comicSansMS = new TrueTypeFont(awtFont, true);
		
		
		while(!Display.isCloseRequested())
		{
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		System.exit(0);
	}
}