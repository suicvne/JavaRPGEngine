package com.mikesantiago.javatextengine.Core;

public class EntityMapping
{
	public static Entity GetEntityFromID(int ID, float x, float y, TileGrid grid)
	{
		switch(ID)
		{
		case -1:
			return new CorePlayer(grid, x, y, null, ID);
		}
		
		return null;
	}
}
