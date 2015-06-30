package com.mikesantiago.gdx2.desktop;

import java.io.File;
import java.net.URLDecoder;
import java.util.Random;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mikesantiago.gdx2.GDX2;

import core.OSDetection;
import core.OSDetection.OSType;

public class DesktopLauncher 
{
	private static String[] splash = 
		{
			"Never going anywhere!", "Not quite an RPG!", "MINECRAFT ALPHA 1.0.1_25", "Not Terraria..or Minecraft", "Not a Tower Defense Either",
			"There will never be building!", "Joey is fat ;)", "SMBXXX!", "If you've seen these all, you have no life!", "Macs > all",
			"At least we have splash screens..", "Scott's a hipster but Folsom's a mole rat!", "There's %s of these!", "public Enemy(boolean numberOne){ this.numberOne = true }",
			"\"What the hell is a footcandle?!\"", "MLG Yearbook", "Not sure if obfuscated", "DADDY!!!!!", Donger.donger, "Tiles by aclyn jen!",
			"(all they have is just bam nam pado dodi banano nino no)", "Tested on Fake Macs!"
		};
	
	private static Random ran = new Random();
	
	public static void main (String[] arg) 
	{
		/**
		 * This should NEVER be kept on. This is simply for debugging purposes
		 */
		//System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "true");
		
		ran.setSeed(System.currentTimeMillis() * ran.nextInt(957823));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		GDX2 gameInstance = new GDX2();
		config.width = 640;
		config.height = 480;
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		config.title = "Java RPG Engine - v0.0.0.4 - " + splash[ran.nextInt(splash.length - 1)];
		if(config.title.contains("%s"))
		{
			String format = String.format(config.title, splash.length);
			config.title = format;
		}
		try
		{
			String path = GDX2.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(new File(path).getParentFile().getPath(), "UTF-8");
				config.addIcon(new File(decodedPath + "/res/Icon-128.png").getAbsolutePath(), FileType.Absolute);
				config.addIcon(new File(decodedPath + "/res/Icon-32.png").getAbsolutePath(), FileType.Absolute);
		}
		catch(Exception ex)
		{
			System.out.println("icon not found, probably not needed though");
		}
		new LwjglApplication(gameInstance, config);
		
	}
}
