package com.mikesantiago.launcher;

import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.mikesantiago.javatextengine.Core.OSDetection;
import com.mikesantiago.javatextengine.Core.OSDetection.OSType;

public class MainProgram 
{
	static OSType CurrentOS = OSDetection.GetCurrentOSName();
	static String systemLookAndFeel = null;
	
	public static void main(String[] args)
	{
		SetLookAndFeel();
		LinkLWJGLLibs();
		Launcher l = new Launcher();
		l.pack();
		System.out.println(String.format("sizing launcher to %s x %s", l.getWidth(), l.getHeight()));
		l.setVisible(true);
	}
	
	public static void SetLookAndFeel()
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
	
	public static void LinkLWJGLLibs()
	{
		try
		{
			System.out.println("setting java.library.path to " + new File("lib/natives/" + CurrentOS).getAbsolutePath());
			System.setProperty("java.library.path", new File("lib/natives/" + CurrentOS).getAbsolutePath());
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
