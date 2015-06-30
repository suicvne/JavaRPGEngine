package core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mikesantiago.gdx2.GDX2;

public class FullscreenDialog 
{
	private String msg;
	private SpriteBatch sb;
	private ShapeRenderer sr;
	
	public FullscreenDialog(String msg, SpriteBatch sb)
	{
		this.msg = msg;
		this.sb = sb;
		this.sr = new ShapeRenderer();
	}

	public void render()
	{
		boolean hadToStart = false;
		if(!sb.isDrawing())
		{
			sb.begin();
			hadToStart = true;
		}	
		sr.begin(ShapeType.Filled);
		sr.setColor(0.3f, 0.3f, 0.3f, 0.5f);
		sr.rect(0, 0, 640, 480);
		sr.end();
		
		GDX2.bmp.draw(sb, msg, 0, 320);
		if(hadToStart)
			sb.end();
	}
}
