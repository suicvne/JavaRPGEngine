package com.mikesantiago.leveleditor;

public class Program 
{
	public static void main(String[] args)
	{
		MainWindow.LinkLWJGLLibraries();
		MainWindow win = new MainWindow();
		win.setVisible(true);
	}
}
