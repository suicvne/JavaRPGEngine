package com.mikesantiago.javatextengine.NPCs;

import org.newdawn.slick.opengl.Texture;

import com.mikesantiago.javatextengine.Core.Entity;
import com.mikesantiago.javatextengine.Core.SimpleGLDrawer;
import com.mikesantiago.javatextengine.Core.TileGrid;

public class EntityUFO extends Entity
{

	public EntityUFO(TileGrid grid, float x, float y, Texture[] frames, int ID) 
	{
		super(grid, x, y, frames, ID);
		this.setEntityFrames(new Texture[]{SimpleGLDrawer.QuickLoad("entity-ufo")});
	}

}