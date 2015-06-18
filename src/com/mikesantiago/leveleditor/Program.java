package com.mikesantiago.leveleditor;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;

public class Program 
{
	public static OSType CurrentOS = OSDetection.GetCurrentOSName();
	public static String systemLookAndFeel;
	
	
	public static void main(String[] args)
	{
		LinkLWJGLLibraries();
		
		SetLookAndFeel();
		
		//MainWindow win = new MainWindow();
		//win.setVisible(true);
		MDIParent p = new MDIParent();
		p.setVisible(true);
	}
	
	private static void SetLookAndFeel()
	{
		switch(CurrentOS)
		{
		case windows:
			try
            {
                systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
            }
            catch(Exception ex)
            {
                systemLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
			break;
		case macosx:
			try
            {
                systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
            }
            catch(Exception ex)
            {
                systemLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
			break;
		case linux:
			try
            {
                systemLookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            }
            catch(Exception ex)
            {
                systemLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
			break;
		case other:
			JOptionPane.showMessageDialog(null, "Not supported", "Not supported on your current operaing system.", JOptionPane.ERROR_MESSAGE);
			break;
		}
		try {
			UIManager.setLookAndFeel(systemLookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
