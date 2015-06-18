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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import com.mikesantiago.javatextengine.Core.CorePlayer;
import com.mikesantiago.javatextengine.Core.TileGrid;
import com.mikesantiago.javatextengine.Core.WindowManager;

public class MainWindow extends JFrame
{
	private JPanel leftSideToolPanel = new JPanel();
	private TileGrid grid;
	private String curFile;
	private boolean pleaseOpen = false;
	
	public boolean IsCurrentlyRendering = false;
	public Canvas cavas = new Canvas();
	
	public MainWindow()
	{
		SetupComponents();
	}
	
	private void SetupComponents()
	{
		//Setup window
				this.setTitle("Simple Level Editor");
				this.setSize(640, 480);
				this.setResizable(false);
				this.setDefaultCloseOperation(EXIT_ON_CLOSE);
				
				//Setup Menu Bar
				JMenuBar mainMenuBar = new JMenuBar();
				JMenu fileMenu = new JMenu("File");
				JMenuItem fileOpenMenu = new JMenuItem("Open");
				JMenuItem beginRender = new JMenuItem("Start Rendering");
				fileOpenMenu.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e)
					{
						OpenFile();
					}
				});
				beginRender.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						t.start();
					}
				});
				
				//Setup toolPanel
				leftSideToolPanel.setSize(300, 480);
				
				//Add ALL Components
				mainMenuBar.add(fileMenu);
				fileMenu.add(fileOpenMenu);
				fileMenu.add(beginRender);
				
				cavas.setSize(640, 480);
				cavas.setFocusable(true);
				cavas.setIgnoreRepaint(true);
				
				this.add(cavas, BorderLayout.CENTER);
				
				this.setJMenuBar(mainMenuBar);
				this.add(leftSideToolPanel, BorderLayout.EAST);
				this.setSize(leftSideToolPanel.getWidth() + this.getWidth(), mainMenuBar.getHeight() + this.getHeight() + 80);
	}
	
	private void OpenFile()
	{
		if(!IsCurrentlyRendering)
			t.start();
		JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open Level File");
        fc.setFileFilter(new FileNameExtensionFilter("Level Files", "jte"));
        int returnval = fc.showOpenDialog(this);
        if(returnval == 0)
        {
        	curFile = fc.getSelectedFile().getAbsolutePath();
        	pleaseOpen = true;
        }
        
        
	}
	
	Thread t = new Thread("Run Display Thread")
	{
		public void run()
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
			CorePlayer p = new CorePlayer(grid);
			
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
						GetRealWidth(),
						GetRealHeight()
				);
				SetNewTitle(debugTitle);
				
				if(pleaseOpen)
				{
					grid = new TileGrid();
					grid.ReadFromFile(curFile);
					p = new CorePlayer(grid);
					pleaseOpen = false;
				}
				Display.update();
				Display.sync(60);
			}
			
			Display.destroy();
			AbandonShip();
		}
	};
	
	private void AbandonShip()
	{
		this.dispose();
	}
	private int GetRealHeight()
	{
		return this.getHeight();
	}
	private int GetRealWidth()
	{
		return this.getWidth();
	}
	private void SetNewTitle(String tit)
	{
		this.setTitle(tit);
	}
	
}
