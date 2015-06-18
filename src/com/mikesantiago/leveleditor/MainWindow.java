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
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;

public class MainWindow extends JFrame
{
	public Canvas cavas = new Canvas();
	private JPanel leftSideToolPanel = new JPanel();
	public static OSType CurrentOS = OSDetection.GetCurrentOSName();
	
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
				this.setSize(leftSideToolPanel.getWidth() + this.getWidth(), 480);
				
				
	}
	
	private void OpenFile()
	{
		
	}
	
	public static void LinkLWJGLLibraries()
	{
		try
		{
			System.setProperty("java.library.path", "lib/jars");
		}
		catch(UnsatisfiedLinkError e)
		{
			System.out.println("just a standard error, no need to relink paths");
		}
		System.out.println("current OS is " + CurrentOS + ", trying for native libs in\n" + new File("lib/natives/" + CurrentOS).getAbsolutePath());
		switch(CurrentOS)
		{
		//to be fair, i don't even know if this will work
		//scratch that, it actually seems to work!!
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
		case other:
			JOptionPane.showMessageDialog(null, 
					"Java Text Engine is not currently supported on your OS: " + System.getProperty("os.name"), 
					"Operating System Not Supported", 
					JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	
	Thread t = new Thread("Run Display Thread")
	{
		public void run()
		{
			try
			{
				cavas.setSize(WindowManager.SCREEN_WIDTH, WindowManager.SCREEN_HEIGHT);
		         Display.setParent(cavas);                           
		         WindowManager.BeginWindow();
			}
			catch(LWJGLException e)
			{
				e.printStackTrace();
			}
			
			while(!Display.isCloseRequested())
			{
				SimpleGLDrawer.DrawRectangle(40, 40, 32, 32);
				
				Display.update();
				Display.sync(60);
			}
		}
	};
	
}
