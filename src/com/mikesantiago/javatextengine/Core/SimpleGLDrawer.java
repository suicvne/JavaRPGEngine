package com.mikesantiago.javatextengine.Core;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.mikesantiago.javatextengine.Utils.AbsoluteLocation;
import com.mikesantiago.javatextengine.Utils.TextureLocation;

public class SimpleGLDrawer
{
	
	public static String texturePackFolder = "default";
	private static AngelCodeFont fontSmall;
	private static AngelCodeFont fontLarge;
	private static boolean fontsInitialised = false;
	
	public static final int SMALL_SIZE = 16;
	public static final int LARGE_SIZE = 32;
	
	public SimpleGLDrawer()
	{
	}
	
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
	
	public static void DrawRectangleRepeating(Texture texture, float x, float y, float width, float height)
	{
		texture.bind();
		glTranslatef(x, y, 0);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
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
	
	public static enum FONTSIZE
	{
		SMALL, LARGE
	}
	
	public static void DrawText(String text, float x, float y, FONTSIZE size, org.newdawn.slick.Color slickColor)
	{
		if(!fontsInitialised)
		{
			try 
			{
				fontSmall = new AngelCodeFont("res/ingame-font-small.fnt", new Image("res/ingame-font-small.png"));
				fontLarge = new AngelCodeFont("res/ingame-font-large.fnt", new Image("res/ingame-font-large.png"));
				fontsInitialised = true;
			} 
			catch (SlickException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage() + "\n\n" + e.getStackTrace(), "Error While Loading Fonts", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				Display.destroy();
			}
		}
		
		if(size == FONTSIZE.SMALL)
		{
			fontSmall.drawString(x, y, text, slickColor);
		}
		else if(size == FONTSIZE.LARGE)
		{
			fontLarge.drawString(x, y, text, slickColor);
		}
	}
	
	
	public static int GetFontWidth(FONTSIZE size, String stringToCheck)
	{
		if(!fontsInitialised)
		{
			try 
			{
				fontSmall = new AngelCodeFont("res/ingame-font-small.fnt", new Image("res/ingame-font-small.png"));
				fontLarge = new AngelCodeFont("res/ingame-font-large.fnt", new Image("res/ingame-font-large.png"));
				fontsInitialised = true;
			} 
			catch (SlickException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage() + "\n\n" + e.getStackTrace(), "Error While Loading Fonts", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				Display.destroy();
			}
		}
		
		if(size == FONTSIZE.SMALL)
		{
			return fontSmall.getWidth(stringToCheck);
		}
		else if(size == FONTSIZE.LARGE)
		{
			return fontLarge.getWidth(stringToCheck);
		}
		else
			return 0;
	}
	
	public static int GetFontHeight(FONTSIZE size, String stringToCheck)
	{
		if(!fontsInitialised)
		{
			try 
			{
				fontSmall = new AngelCodeFont("res/ingame-font-small.fnt", new Image("res/ingame-font-small.png"));
				fontLarge = new AngelCodeFont("res/ingame-font-large.fnt", new Image("res/ingame-font-large.png"));
				fontsInitialised = true;
			} 
			catch (SlickException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage() + "\n\n" + e.getStackTrace(), "Error While Loading Fonts", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				Display.destroy();
			}
		}
		
		if(size == FONTSIZE.SMALL)
		{
			return fontSmall.getHeight(stringToCheck);
		}
		else if(size == FONTSIZE.LARGE)
		{
			return fontLarge.getHeight(stringToCheck);
		}
		else
			return 0;
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
	
	public static void DrawLine(AbsoluteLocation startPoint, AbsoluteLocation endPoint)
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
	
	/**
	 * This method is here for internal reference only, it does absolutely nothing of use and should NOT be used.
	 * @param name
	 */
	private static void LoadFromSpriteSheet(String name)
	{
		Texture tex = null;
		InputStream in;
		try {
			BufferedImage b = ImageIO.read(ResourceLoader.getResourceAsStream("res/dead-texture.png"));
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(b, "png", os);
			in = new ByteArrayInputStream(os.toByteArray());
			tex = TextureLoader.getTexture("PNG", in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Texture LoadFromTextureSheet(TileType type)
	{
		Texture tex = null;
		InputStream in;
		try {
			TextureLocation loc = TextureLocation.values()[type.ordinal()];
			BufferedImage b = ImageIO.read(ResourceLoader.getResourceAsStream("res/default/textures.png")).getSubimage(
					(int)loc.absLocation.getX(), 
					(int)loc.absLocation.getY(), 
					32, 
					32);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(b, "png", os);
			in = new ByteArrayInputStream(os.toByteArray());
			tex = TextureLoader.getTexture("PNG", in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
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
