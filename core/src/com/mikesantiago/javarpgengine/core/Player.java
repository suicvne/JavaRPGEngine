package com.mikesantiago.javarpgengine.core;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mikesantiago.javarpgengine.GDX2;
import com.mikesantiago.javarpgengine.core.GameStateManager.GameState;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;
import com.neet.blockbunny.handlers.Animation;

public class Player 
{
	private TextureRegion[] upFrames;
	private TextureRegion[] downFrames;
	private TextureRegion[] leftFrames;
	private TextureRegion[] rightFrames;
	
	private Animation upAnim;
	private Animation leftAnim;
	private Animation downAnim;
	private Animation rightAnim;
	
	private TileGrid map;
	
	private float y = 5 * 32;
	private float x = 5 * 32;
	
	private CurDir curDir = CurDir.DOWN;
	
	private enum CurDir{UP, DOWN, LEFT, RIGHT}
	private Random random;
	
	@Deprecated
	public Player()
	{
		Texture totalSheet = GlobalVariables.content.getTexture("player");
		
		this.upFrames = TextureRegion.split(totalSheet, 16, 16)[1];
		this.downFrames = TextureRegion.split(totalSheet, 16, 16)[0];
		this.leftFrames = TextureRegion.split(totalSheet, 16, 16)[3];
		this.rightFrames = TextureRegion.split(totalSheet, 16, 16)[2];
		upAnim = new Animation(upFrames, 1/4f); //update 4 times a second
		downAnim = new Animation(downFrames, 1/4f); //update 4 times a second
		leftAnim = new Animation(leftFrames, 1/4f); //update 4 times a second
		rightAnim = new Animation(rightFrames, 1/4f); //update 4 times a second
		
		random = new Random(System.nanoTime());
	}
	
	public Player(TileGrid map)
	{
		Texture totalSheet = GlobalVariables.content.getTexture("player");
		
		this.upFrames = TextureRegion.split(totalSheet, 16, 16)[1];
		this.downFrames = TextureRegion.split(totalSheet, 16, 16)[0];
		this.leftFrames = TextureRegion.split(totalSheet, 16, 16)[3];
		this.rightFrames = TextureRegion.split(totalSheet, 16, 16)[2];
		upAnim = new Animation(upFrames, 1/4f); //update 4 times a second
		downAnim = new Animation(downFrames, 1/4f); //update 4 times a second
		leftAnim = new Animation(leftFrames, 1/4f); //update 4 times a second
		rightAnim = new Animation(rightFrames, 1/4f); //update 4 times a second
		
		this.map = map;
		
		random = new Random(System.nanoTime());
	}

	public void update()
	{
		float dt = Gdx.graphics.getDeltaTime();
		boolean moved = false;
		
		if(Gdx.input.isKeyPressed(Keys.D))
		{
			curDir = CurDir.RIGHT;
			
			Tile targetTile = map.getTileAt(x + 32, y);
			if(targetTile.getType().canPass || targetTile.isFloorTile())
			{
				x += 64 * dt;
				if(x >= targetTile.getX())
				{
					x = targetTile.getX();
				}
				moved = true;
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.A))
		{
			curDir = CurDir.LEFT;
			
			Tile targetTile = map.getTileAt((int)Math.ceil(x / 32) - 1, (int)Math.floor(y / 32));
			if(targetTile.getType().canPass || targetTile.isFloorTile())
			{
				x -= 64 * dt;
				if(x <= targetTile.getX() - 1)
					x = targetTile.getX() + 1;
				moved = true;
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.S))
		{
			curDir = CurDir.DOWN;
			
			Tile targetTile = map.getTileAt((int)Math.floor(x / 32), (int)Math.ceil(y / 32) - 1);
			if(targetTile.getType().canPass || targetTile.isFloorTile())
			{
				y -= 64 * dt;
				if(y <= targetTile.getY() - 1)
					y = targetTile.getY() + 1;
				moved = true;
			}
		}
		else if(Gdx.input.isKeyPressed(Keys.W))
		{
			curDir = CurDir.UP;
			
			Tile targetTile = map.getTileAt(x, y + 32);
			if(targetTile.getType().canPass || targetTile.isFloorTile())
			{
				y += 64 * dt;
				if(y >= targetTile.getY())
					y = targetTile.getY();
				moved = true;
			}
		}
		//
		upAnim.update(dt); //i don't think updating them all hurts that much
		downAnim.update(dt);
		leftAnim.update(dt);
		rightAnim.update(dt);
		//last
		GlobalVariables.maincamera.position.set(x + 16, y, 0);
		GlobalVariables.maincamera.update();
		
		//Check to see if an encounter is necessary
		
		if(moved)
		{
			int one, two;
			one = random.nextInt(255);
			two = random.nextInt(255);
			if(one == two)
			{
				GlobalVariables.gsm.ChangeState(GameState.BATTLE);
				moved = false;
			}
		}
		//
	}
	
	public Vector2 GetTiledPositions()
	{
		int tx = (int)Math.round((double)x/32);
		int ty = (int)Math.round((double)y/32);
		return new Vector2(tx, ty);
	}
	
	public void render(SpriteBatch sb)
	{
		update();
		
		sb.setProjectionMatrix(GlobalVariables.maincamera.combined);
		
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
