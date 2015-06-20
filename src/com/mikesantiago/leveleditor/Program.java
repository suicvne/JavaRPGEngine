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
		//LinkLWJGLLibraries();
		
		//SetLookAndFeel();
		
		//MainWindow win = new MainWindow();
		//win.setVisible(true);
		MDIParent p = new MDIParent();
		p.setVisible(true);
	}
	
}
