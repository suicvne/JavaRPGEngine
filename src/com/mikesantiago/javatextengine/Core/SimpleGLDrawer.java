package com.mikesantiago.javatextengine.Core;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class SimpleGLDrawer
{
	
	public static String texturePackFolder = "placeholder";
	
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
	
	public static void DrawTile(Tile tile)
	{
		tile.getTexture().bind();
		glTranslatef(tile.getX(), tile.getY(), 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); //Top left
		glVertex2f(0, 0);
		glTexCoord2f(1, 0); //Top right
		glVertex2f(tile.getWidth(), 0);
		glTexCoord2f(1, 1); //Bottom left
		glVertex2f(tile.getWidth(), tile.getHeight());
		glTexCoord2f(0, 1); //Bottom right
		glVertex2f(0, tile.getHeight());
		glEnd();
		glLoadIdentity();
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
	
	public static Texture QuickLoad(String name)
	{
		Texture tex = null;
		try 
		{
			InputStream in = ResourceLoader.getResourceAsStream("res/" + texturePackFolder + "/" + name + ".png");
			tex = TextureLoader.getTexture("PNG", in);
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