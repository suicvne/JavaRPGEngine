package com.mikesantiago.javatextengine.Core;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SimpleGLDrawer
{
	private static UnicodeFont font;
	private static DecimalFormat formatter = new DecimalFormat("#.##");
	private static FloatBuffer perspectiveProjectMatrix = BufferTools.reserveData(16);
	private static FloatBuffer orthographicProjectMatrix = BufferTools.reserveData(16);
	
	private static boolean fontsSetup = false;
	
	public static String texturePackFolder = "placeholder";
	private static Font mainGameFont = new Font("Comic Sans MS", Font.PLAIN, 24);
	
	private SimpleGLDrawer(){}
	
	/**
	 * A simple method to draw an OpenGL Square on the screen
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void DrawRectangle(float x, float y, float width, float height)
	{
		glBegin(GL_QUADS);
		glVertex2f(x, y); //Top left
		glVertex2f(x + width, y); //Top right
		glVertex2f(x + width, y + height); //Bottom right
		glVertex2f(x, y + height); //Bottom left
		glEnd();
	}
	
	public static void DrawRectangle(Texture texture, float x, float y, float width, float height)
	{
		texture.bind();
		glTranslatef(x, y, 0);
		glColor4f(1f, 1f, 1f, 1f);//dead white
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); //Top left
		glVertex2f(0, 0);
		glTexCoord2f(1, 0); //Top right
		glVertex2f(width, 0);
		glTexCoord2f(1, 1); //Bottom left
		glVertex2f(width, height);
		glTexCoord2f(0, 1); //Bottom right
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	private static void setupFonts()
	{
		java.awt.Font awtFont = new java.awt.Font("Times New Roman", java.awt.Font.BOLD, 18);
		font = new UnicodeFont(awtFont);
		font.getEffects().add(new ColorEffect(java.awt.Color.white));
		font.addAsciiGlyphs();
		try
		{
			font.loadGlyphs();
			fontsSetup = true;
		}
		catch(SlickException ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	public static void DrawText(String text, float x, float y)
	{
		if(!fontsSetup)
			setupFonts();
		font.drawString(x, y, text);
	}
	
	/**
	 * Useful for darker tiles, like "background" (floor) tiles
	 * @param texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void DrawRectangleDarkened(Texture texture, float x, float y, float width, float height)
	{	
		texture.bind();
		glTranslatef(x, y, 0);
		glColor4f(.5f, .5f, .5f, .5f);
		
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0); //Top left
			glVertex2f(0, 0);
		
			glTexCoord2f(1, 0); //Top right
			glVertex2f(width, 0);
		
			glTexCoord2f(1, 1); //Bottom left
			glVertex2f(width, height);
		
			glTexCoord2f(0, 1); //Bottom right
			glVertex2f(0, height);
		}
		//
		glEnd();
		
		glBegin(GL_QUADS);
		glDisable(GL_TEXTURE_2D);
		glColor4f(.5f, .5f, .5f, .5f); //a slightly darker shade
		glVertex2f(0, 0); //top left
		glVertex2f(1, 0); //top right
		glVertex2f(0, 1); //bottom left
		glVertex2f(1, 1); //bottom right
		glEnable(GL_TEXTURE_2D);
		glEnd();
		//
		glEnd();
		glLoadIdentity();
	}
	
	public static void DrawTile(Tile tile)
	{
		DrawRectangle(tile.getTexture(), tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
	}
	
	public static void DrawFloorTile(Tile tile)
	{
		DrawRectangleDarkened(tile.getTexture(),
				tile.getX(),
				tile.getY(),
				tile.getWidth(),
				tile.getHeight());
	}
	
	public static void DrawLine(Point startPoint, Point endPoint)
	{
		glBegin(GL_LINES);
		glVertex2f(startPoint.getX(), startPoint.getY());
		glVertex2f(endPoint.getX(), endPoint.getY());
		glEnd();
	}
	
	public static Texture LoadTexture(String path, String fileType)
	{
		Texture tex = null;
		try 
		{
			InputStream in = ResourceLoader.getResourceAsStream(path);
			tex = TextureLoader.getTexture(fileType, in);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, 
						"The resource '" + path + "' or 'res' folder could not be found!",
						"Resources (res) Not Found",
						JOptionPane.ERROR_MESSAGE);
		}
		return tex;
	}
	
	public static void DrawText()
	{
		
	}
	
	public static Texture QuickLoad(String name)
	{
		Texture tex = null;
		try 
		{
			if(name.equals("dead-texture"))
			{
				InputStream in = ResourceLoader.getResourceAsStream("res/dead-texture.png");
				tex = TextureLoader.getTexture("PNG", in);
			}
			else
			{
				InputStream in = ResourceLoader.getResourceAsStream("res/" + texturePackFolder + "/" + name + ".png");
				tex = TextureLoader.getTexture("PNG", in);
			}
		} 
		catch (Exception e) 
		{
			//System.out.println("couldn't find texture named " + name + ", loading null texture");
				InputStream in = ResourceLoader.getResourceAsStream("res/dead-texture.png");
				try {
					tex = TextureLoader.getTexture("PNG", in);
				} catch (IOException e1) {
					Display.destroy();
					JOptionPane.showMessageDialog(null, 
								"The resource 'res/" + texturePackFolder + "/" + name + ".png' or 'res' folder could not be found!\nAdditionally, the placeholder texture couldn't be found!",
								"Resources (res) Not Found",
								JOptionPane.ERROR_MESSAGE);
					System.exit(2);
				}
		}
		return tex;
	}
}