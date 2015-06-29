package com.mikesantiago.gdx2.desktop;

import java.io.File;
import java.util.Random;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mikesantiago.gdx2.GDX2;

public class DesktopLauncher 
{
	private static String[] splash = 
		{
			"Never going anywhere!", "Not quite an RPG!", "MINECRAFT ALPHA 1.0.1_25", "Not Terraria..or Minecraft", "Not a Tower Defense Either",
			"There will never be building!", "Joey is fat ;)", "SMBXXX!", "If you've seen these all, you have no life!", "Macs > all",
			"At least we have splash screens..", "Scott's a hipster but Folsom's a mole rat!", "There's %s of these!", "public Enemy(boolean numberOne){ this.numberOne = true }",
			"\"What the hell is a footcandle?!\"", "MLG Yearbook", "Not sure if obfuscated", "DADDY!!!!!", Donger.donger, "Tiles by aclyn jen!",
			"(all they have is just bam nam pado dodi banano nino no)"
		};
	
	private static Random ran = new Random();
	
	public static void main (String[] arg) 
	{
		ran.setSeed(System.currentTimeMillis() * ran.nextInt(957823));
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		GDX2 gameInstance = new GDX2();
		config.width = 640;
		config.height = 480;
		config.foregroundFPS = 60;
		config.vSyncEnabled = true;
		config.title = "Java RPG Engine - v0.0.0.1 - " + splash[ran.nextInt(splash.length - 1)];
		if(config.title.contains("%s"))
		{
			String format = String.format(config.title, splash.length);
			config.title = format;
		}
		config.addIcon(new File("res/Icon-128.png").getAbsolutePath(), FileType.Absolute);
		config.addIcon(new File("res/Icon-32.png").getAbsolutePath(), FileType.Absolute);
		new LwjglApplication(gameInstance, config);
		
	}
}
