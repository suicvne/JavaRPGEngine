package com.mikesantiago.javatextengine.Utils;



public enum TextureLocation
{
	Grass(new GridLocation(0,0)), Stone(new GridLocation(1,0)),
	Water(new GridLocation(2,0)), Dirt(new GridLocation(3,0)),
	Air(new GridLocation(4,0));
	
	public GridLocation location;
	public AbsoluteLocation absLocation;
	
	TextureLocation(GridLocation loc)
	{
		this.location = loc;
		
		absLocation = new AbsoluteLocation(location.getX(), location.getY());
	}
}
