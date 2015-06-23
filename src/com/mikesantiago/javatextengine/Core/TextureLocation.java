package com.mikesantiago.javatextengine.Core;


public enum TextureLocation
{
	Grass(new GridLocation(0,0)), Stone(new GridLocation(1,0)),
	Water(new GridLocation(2,0)), Dirt(new GridLocation(3,0)),
	Air(new GridLocation(4,0));
	
	GridLocation location;
	AbsoluteLocation absLocation;
	
	TextureLocation(GridLocation loc)
	{
		this.location = loc;
		
		absLocation = new AbsoluteLocation(location.getX(), location.getY());
	}
}
