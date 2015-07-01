package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mikesantiago.gdx2.GDX2;
import com.neet.blockbunny.handlers.Animation;

public class Player 
{
	private Animation animation;
	private TextureRegion[] upFrames;
	private TextureRegion[] downFrames;
	private TextureRegion[] leftFrames;
	private TextureRegion[] rightFrames;
	
	private Animation upAnim;
	private Animation leftAnim;
	private Animation downAnim;
	private Animation rightAnim;
	
	private TileGrid map;
	
	private float x, y = 5 * 32;
	
	private CurDir curDir = CurDir.DOWN;
	
	private enum CurDir{UP, DOWN, LEFT, RIGHT}
	
	public Player()
	{
		Texture totalSheet = GDX2.content.getTexture("player");
		
		this.upFrames = TextureRegion.split(totalSheet, 16, 16)[1];
		this.downFrames = TextureRegion.split(totalSheet, 16, 16)[0];
		this.leftFrames = TextureRegion.split(totalSheet, 16, 16)[3];
		this.rightFrames = TextureRegion.split(totalSheet, 16, 16)[2];
		upAnim = new Animation(upFrames, 1/4f); //update 4 times a second
		downAnim = new Animation(downFrames, 1/4f); //update 4 times a second
		leftAnim = new Animation(leftFrames, 1/4f); //update 4 times a second
		rightAnim = new Animation(rightFrames, 1/4f); //update 4 times a second
	}
	
	public Player(TileGrid map)
	{
		Texture totalSheet = GDX2.content.getTexture("player");
		
		this.upFrames = TextureRegion.split(totalSheet, 16, 16)[1];
		this.downFrames = TextureRegion.split(totalSheet, 16, 16)[0];
		this.leftFrames = TextureRegion.split(totalSheet, 16, 16)[3];
		this.rightFrames = TextureRegion.split(totalSheet, 16, 16)[2];
		upAnim = new Animation(upFrames, 1/4f); //update 4 times a second
		downAnim = new Animation(downFrames, 1/4f); //update 4 times a second
		leftAnim = new Animation(leftFrames, 1/4f); //update 4 times a second
		rightAnim = new Animation(rightFrames, 1/4f); //update 4 times a second
		
		this.map = map;
	}

	public void update()
	{
		if(map != null)
		{
			//TODO: check you're not off the bounds of the map
		}
		float dt = Gdx.graphics.getDeltaTime();
		
		if(Gdx.input.isKeyPressed(Keys.S))
		{
			curDir = CurDir.DOWN;
			//animation = new Animation(downFrames, 1/4f);
			y -= 64 * dt;
		}
		if(Gdx.input.isKeyPressed(Keys.W))
		{
			curDir = CurDir.UP;
			//animation = new Animation(upFrames, 1/4f);
			y += 64 * dt;
		}
		if(Gdx.input.isKeyPressed(Keys.A))
		{
			curDir = CurDir.LEFT;
			x -= 64 * dt;
		}
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			curDir = CurDir.RIGHT;
			x += 64 * dt;
		}
		
		upAnim.update(dt); //i don't think updating them all hurts that much
		downAnim.update(dt);
		leftAnim.update(dt);
		rightAnim.update(dt);
		
		GDX2.maincamera.position.set(x + 16, y, 0);
		GDX2.maincamera.update();
	}
	
	public void render(SpriteBatch sb)
	{
		update();
		
		sb.setProjectionMatrix(GDX2.maincamera.combined);
		
		boolean hadToStart = false;
		if(!sb.isDrawing())
		{
			hadToStart = true;
			sb.begin();
		}
		
		switch(curDir)
		{
		case DOWN:
			sb.draw(downAnim.getFrame(), x, y, 32, 32);
			break;
		case UP:
			sb.draw(upAnim.getFrame(), x, y, 32, 32);
			break;
		case LEFT:
			sb.draw(leftAnim.getFrame(), x, y, 32, 32);
			break;
		case RIGHT:
			sb.draw(rightAnim.getFrame(), x, y, 32, 32);
			break;
		}
		
		if(hadToStart)
		{
			sb.end();
		}
	}
}
