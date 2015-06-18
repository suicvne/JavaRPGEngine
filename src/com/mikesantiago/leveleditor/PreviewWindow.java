package com.mikesantiago.leveleditor;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JInternalFrame;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mikesantiago.javatextengine.Core.CorePlayer;
import com.mikesantiago.javatextengine.Core.TileGrid;
import com.mikesantiago.javatextengine.Core.WindowManager;

public class PreviewWindow extends JInternalFrame
{
	private TileGrid grid;
	private String curFile;
	private boolean pleaseOpen = false;
	
	public boolean IsCurrentlyRendering = false;
	public Canvas cavas = new Canvas();
	
	public PreviewWindow()
	{
		SetupComponents();
	}
	
	private void SetupComponents()
	{
		this.setTitle("Level Preview Window");
		//this.setSize(640, 480);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		
		
		cavas.setSize(640, 480);
		cavas.setFocusable(true);
		cavas.setIgnoreRepaint(true);
		this.add(cavas, BorderLayout.CENTER);
		this.pack();
	}
	
	public void StartRender()
	{
		BeginRendering();
	}
	
	public void NewMap(boolean GenerateRandom)
	{
		curFile = null;
		if(GenerateRandom)
			GenerateRandomMap();
		else
			grid = new TileGrid();
	}
	
	private void GenerateRandomMap()
	{
		grid = new TileGrid();
		grid.GenerateRandomMap();
	}
	
	public void OpenMapFile(String fileToOpen)
	{
		curFile = fileToOpen;
		pleaseOpen = true;
	}
	
	public void SaveMapFile(String fileToSave)
	{
		curFile = fileToSave;
		grid.WriteMapToFile(fileToSave);
	}
	
	private void BeginRendering()
	{
		IsCurrentlyRendering = true;
		try
		{
			cavas.setSize(WindowManager.SCREEN_WIDTH, WindowManager.SCREEN_HEIGHT);
	         Display.setParent(cavas); 
	         Display.create();
	         //WindowManager.BeginWindow();
		}
		catch(LWJGLException e)
		{
			e.printStackTrace();
		}
		
		//Initiates OpenGL 
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WindowManager.SCREEN_WIDTH, WindowManager.SCREEN_HEIGHT, 0, 1, -1); //camera
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		//
		
		grid = new TileGrid();
		CorePlayerEditor p = new CorePlayerEditor(grid);
		
		while(!Display.isCloseRequested())
		{
			if(grid != null)
				grid.Draw();
			p.Update();
			
			String debugTitle = String.format("MX:%s MY:%s; TGX:%s TGY: %s; CurTile: %s; %s x %s", 
					Mouse.getX(), Mouse.getY(),
					((int)Math.floor(Mouse.getX() / 32)), 
					((int)Math.floor((WindowManager.SCREEN_HEIGHT - Mouse.getY() - 1) / 32)),
					p.getCurrentTile(),
					this.getWidth(),
					this.getHeight()
			);
			this.setTitle(debugTitle);
			
			if(pleaseOpen)
			{
				grid = new TileGrid();
				grid.ReadFromFile(curFile);
				p = new CorePlayerEditor(grid);
				pleaseOpen = false;
			}
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
}
