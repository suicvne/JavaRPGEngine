package com.mikesantiago.launcher;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.mikesantiago.javatextengine.Boot;
import com.mikesantiago.javatextengine.Core.WindowManager;
import com.mikesantiago.leveleditor.MDIParent;

public class Launcher extends JFrame 
{	
	
	
	public Launcher()
	{
		SetupComponents();
	}
	
	private void SetupComponents()
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		this.setSize(640, 480);
		this.setTitle("Java RPG Engine Launcher");
		
		JPanel panel = new JPanel();
		JButton runEngine = new JButton("Launch Engine");
		JCheckBox runEngineInDebug = new JCheckBox("Run Engine in Debug Mode");
		JButton runEditor = new JButton("Launch Editor");
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		runEngine.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(runEngineInDebug.isSelected())
					runEngine(true);
				else
					runEngine(false);
			}
		});
		
		runEditor.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e)
			{
				runEditor();
			}
		});
		
		Container contentPane = getContentPane();
		contentPane.add(runEngine, BorderLayout.NORTH);
		contentPane.add(runEditor, BorderLayout.CENTER);
		contentPane.add(runEngineInDebug, BorderLayout.SOUTH);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(("res/placeholder/placeholder-icon.png")));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        System.out.println("centering window");
	}
	
	private void runEngine(boolean debug)
	{
		this.setVisible(false);
		WindowManager.DEBUG = debug;
		System.out.println("running engine; debug mode: " + debug);
		try
		{
			new Boot();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage());
		}
	}
	
	private void runEditor()
	{
		this.setVisible(false);
		MDIParent editor = new MDIParent();
		System.out.println("running editor");
		editor.setVisible(true);
	}
}
