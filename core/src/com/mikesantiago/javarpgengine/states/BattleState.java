package com.mikesantiago.javarpgengine.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mikesantiago.javarpgengine.core.GameStateManager.GameState;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;

public class BattleState 
{
	private Texture background, player, enemy;
	private ShapeRenderer sr;
	
	public BattleState()
	{
		background = GlobalVariables.content.getTexture("background");
		player = GlobalVariables.content.getTexture("player-battle");
		enemy = GlobalVariables.content.getTexture("enemy");
		sr = new ShapeRenderer();
	}
	public void update()
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			GlobalVariables.gsm.ChangeState(GameState.EDITOR);
	}
	public void render(SpriteBatch sb)
	{
		boolean hadToBegin = false;
		if(!sb.isDrawing())
		{
			sb.begin();
			hadToBegin = true;
		}
		sr.begin(ShapeType.Filled);
		//
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//GlobalVariables.bmpFnt.draw(sb, "This is a battle scene!!!", 0, 240);
		//Background
		sb.draw(background, 0, 0, 640, 480);
		
		//
		if(hadToBegin)
			sb.end();
	}
}
