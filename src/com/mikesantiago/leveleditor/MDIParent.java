package com.mikesantiago.leveleditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MDIParent extends JFrame 
{
	JDesktopPane mainPane = new JDesktopPane();
	//JBorderLayout mainLayout = new JBorderLayout();
	PreviewWindow onlyPreviewWindow = new PreviewWindow();
	
	public MDIParent()
	{
		SetupComponents();
	}
	
	private void SetupComponents()
	{
		mainPane.setBackground(Color.GRAY);
		this.setSize(800, 600);
		this.setTitle("Java RPG Engine - Level Editor");
		this.add(mainPane);
		this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JMenuBar mainMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem fileOpenMenu = new JMenuItem("Open");
		JMenuItem fileSaveMenu = new JMenuItem("Save Map");
		JMenuItem beginRender = new JMenuItem("New Map (Debug)");
		fileOpenMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				OpenFile();
			}
		});
		fileSaveMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e)
			{
				SaveFile();
			}
		});
		beginRender.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!onlyPreviewWindow.IsCurrentlyRendering)
					renderThread.start();
				else
					onlyPreviewWindow.NewMap(true);
			}
		});
		
		//Add ALL Components
		mainMenuBar.add(fileMenu);
		fileMenu.add(fileOpenMenu);
		fileMenu.add(fileSaveMenu);
		fileMenu.add(beginRender);
		
		
		this.setJMenuBar(mainMenuBar);
		
		
		this.mainPane.add(onlyPreviewWindow);
		
		int centerWidth = (int)Math.floor((this.getWidth() / 2) - onlyPreviewWindow.getWidth() / 4);
		int centerHeight = (int)Math.floor((this.getHeight() / 2) - onlyPreviewWindow.getHeight() / 4);
		onlyPreviewWindow.setLocation(new Point(centerWidth, centerHeight));
		onlyPreviewWindow.setVisible(true);
		//onlyPreviewWindow.StartRender();
	}
	
	private void SaveFile()
	{
		if(onlyPreviewWindow.IsCurrentlyRendering)
		{
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Save Level File");
			fc.setFileFilter(new FileNameExtensionFilter("Level Files", "jte"));
			int returnval = fc.showSaveDialog(this);
			if(returnval == 0)
			{
				onlyPreviewWindow.SaveMapFile(fc.getSelectedFile().getAbsolutePath());
				JOptionPane.showMessageDialog(this, "Level saved successfully!", "Level Saved", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private void OpenFile()
	{
		if(!onlyPreviewWindow.IsCurrentlyRendering)
			renderThread.start();
		JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open Level File");
        fc.setFileFilter(new FileNameExtensionFilter("Level Files", "jte"));
        int returnval = fc.showOpenDialog(this);
        if(returnval == 0)
        {
        	onlyPreviewWindow.OpenMapFile(fc.getSelectedFile().getAbsolutePath());
        }
	}
	
	Thread renderThread = new Thread("Rendering")
	{
		public void run()
		{
			onlyPreviewWindow.StartRender();
		}
	};
	
	private void addInternalFrame(JInternalFrame frameToAdd)
	{
		mainPane.add(frameToAdd);
	}
}
