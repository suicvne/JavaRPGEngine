package com.mikesantiago.leveleditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.mikesantiago.javarpgengine.GDX2;
import com.mikesantiago.javarpgengine.core.TileType;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;
import java.awt.CardLayout;
import java.awt.GridLayout;

public class GameContainer extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public GameContainer() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonPane = new JPanel();
		for(TileType t: TileType.values())
		{
			JButton b = new JButton(t.toString());
			b.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent arg0) 
				{
					changeTile(t.toString());
				}	
			});
			buttonPane.add(b);
		}
		buttonPane.setSize(5, this.getHeight());
		getContentPane().add(buttonPane, BorderLayout.EAST);
		buttonPane.setLayout(new GridLayout(8, 8, 0, 0));
		
		LwjglAWTCanvas canvas = new LwjglAWTCanvas(new GDX2());
		canvas.getCanvas().setSize(640, 480);
		getContentPane().add(canvas.getCanvas(), BorderLayout.WEST);
	}

	@Override
	public void dispose()
	{
		GlobalVariables.gsm.getEditor().TryWritingSaves();
		System.exit(0); //normal most of the time
	}
	
	private void changeTile(TileType tile)
	{
		
	}
	private void changeTile(String tileType)
	{
		TileType t = TileType.valueOf(tileType);
		GlobalVariables.gsm.getEditor().getGod().setPlacingTile(t);
	}
	
}
