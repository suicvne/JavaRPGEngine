package core;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mikesantiago.gdx2.GDX2;

public class Player 
{
	
	
	public Player()
	{
		Texture totalSheet = GDX2.content.getTexture("player");
		TextureRegion[] upFrames = TextureRegion.split(totalSheet, 16, 16)[0];
	}

}
