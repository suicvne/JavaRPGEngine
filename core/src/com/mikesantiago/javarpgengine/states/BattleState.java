package com.mikesantiago.javarpgengine.states;

import java.util.ArrayList;
import java.util.List;

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
	private boolean menuOpen = false;
	
	//Shoutout to CaptainMadbeard from Twitch stream for the idea of only doing one of these
	private List<String> battleChoices = new ArrayList<String>();
	
	private int choiceIndex = 0;
	private int curPlayer = 0; //0-3 value regarding to the current player
	
	public BattleState()
	{
		background = GlobalVariables.content.getTexture("background");
		player = GlobalVariables.content.getTexture("player-battle");
		enemy = GlobalVariables.content.getTexture("enemy");
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);
		
		battleChoices.add("Sword");
		battleChoices.add("Shoot");
	}
	//TODO: constructor involving party
	
	public void update()
	{
		if(Gdx.input.isKeyPressed(Keys.ESCAPE))
			GlobalVariables.gsm.ChangeState(GameState.EDITOR);
		if(Gdx.input.isKeyPressed(Keys.Z))
		{	
			if(!menuOpen)
				menuOpen = true;
			else
			{
				//TODO: commit a choice
			}
		}
		
		if(menuOpen)
		{
			if(Gdx.input.isKeyJustPressed(Keys.UP))
			{
				choiceIndex--;
				if(choiceIndex < battleChoices.size())
					choiceIndex = 0;
			}
			else if(Gdx.input.isKeyJustPressed(Keys.DOWN))
			{
				choiceIndex++;
				if(choiceIndex > battleChoices.size())
					choiceIndex = 0;
			}
		}
	}
	public void render(SpriteBatch sb)
	{
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		boolean hadToBegin = false;
		if(!sb.isDrawing())
		{
			sb.begin();
			hadToBegin = true;
		}
		//
		RenderBackground(sb);
		RenderCharacters(sb);
		//
		if(hadToBegin)
			sb.end();
		
		RenderShapes();
		RenderText(sb, "Test");
	}
	
	private void RenderBackground(SpriteBatch sb)
	{
		sb.setProjectionMatrix(GlobalVariables.hudcam.combined);
		sb.draw(background, 0, 0, 640, 480);
	}
	private void RenderCharacters(SpriteBatch sb)
	{
		//Enemy on left side
		sb.draw(enemy, 120, 360, enemy.getWidth() * 2, enemy.getHeight() * 2);
		//Player on right side
		sb.draw(player, 480, 360, 16 * 2, 24 * 2);
	}
	private void RenderShapes()
	{
		sr.begin(ShapeType.Filled);
		
		//Background
		sr.setColor(0f,0f,0f,0f);
		sr.setProjectionMatrix(GlobalVariables.hudcam.combined);
		sr.rect(0, 0, 640, 125);
		//Player status
		sr.setColor(0f, 0f, .5f, 0f);
		sr.rect(640 - (640 * .35f), 0, 640 * .50f, 125);
		//Enemy status
		sr.setColor(.5f, 0f, 0f, 0f);
		sr.rect(0, 0, 640 * .65f, 125);
		
		if(menuOpen)
		{
			sr.setColor(0f, .5f, .5f, 1f);
			sr.rect(640 - (640 * .35f), 170, 
					120, 170);
		}
		
		sr.end();
	}
	private void RenderText(SpriteBatch sb, String text)
	{
		sb.begin();
		//TODO: render player status on right
		//y - 110 - player one
		//y - 16 - last member
		GlobalVariables.bmpFnt.draw(sb, "Kain - 60/60", 640 - (640 * .35f) + 8, 110);
		//TODO: render enemy status on left
		GlobalVariables.bmpFnt.draw(sb, "Cachodemon - 125/125", 8, 110);
		
		if(menuOpen)
		{
			int startingY = 350 - 16;
			switch(curPlayer)
			{
			case 0:
				for(int i = 0; i < battleChoices.size(); i++)
				{
					String choice = battleChoices.get(i);
					
					if(i == choiceIndex)
						GlobalVariables.bmpFnt.draw(sb, ">" + choice, 640 - (640 * .35f) + 2, startingY - (i * 16));
					else
						GlobalVariables.bmpFnt.draw(sb, choice, 640 - (640 * .35f) + 2, startingY - (i * 16));
				}
				break;
			}
		}
		sb.end();
	}
	
}
