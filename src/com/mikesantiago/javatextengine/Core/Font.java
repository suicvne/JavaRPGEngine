package com.mikesantiago.javatextengine.Core;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Font 
{
	private static String chars = "" + //
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ      " + //
			"0123456789.,!?'\"-+=/\\%()<>:;     " + //
			"";
	
	public static void DrawText(float x, float y, String msg)
	{
		Texture tex = null;
		msg.toUpperCase();
		for(int index = 0; index < msg.length(); index++)
		{
			InputStream in = ResourceLoader.getResourceAsStream("res/font-test.png");
			try {
				tex = TextureLoader.getTexture("PNG", in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int charIndex = chars.indexOf(msg.charAt(index));
			SimpleGLDrawer.DrawRectangle(tex, x, y, 8, 8);
		}
	}
}
