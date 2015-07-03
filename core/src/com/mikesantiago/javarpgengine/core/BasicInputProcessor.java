package com.mikesantiago.javarpgengine.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mikesantiago.javarpgengine.GDX2;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;

public class BasicInputProcessor implements InputProcessor 
{
	@Override
	public boolean keyDown(int keycode) 
	{
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			Vector3 newPos = GlobalVariables.maincamera.position;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
					|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				newPos.y += 16;
			} else {
				newPos.y += 8;
			}
			GlobalVariables.maincamera.position.set(newPos);
			GlobalVariables.maincamera.update();
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			Vector3 newPos = GlobalVariables.maincamera.position;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
					|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				newPos.y -= 16;
			} else {
				newPos.y -= 8;
			}
			GlobalVariables.maincamera.position.set(newPos);
			GlobalVariables.maincamera.update();
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			Vector3 newPos = GlobalVariables.maincamera.position;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
					|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				newPos.x -= 16;
			} else {
				newPos.x -= 8;
			}
			GlobalVariables.maincamera.position.set(newPos);
			GlobalVariables.maincamera.update();
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			Vector3 newPos = GlobalVariables.maincamera.position;
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)
					|| Gdx.input.isKeyPressed(Keys.SHIFT_RIGHT)) {
				newPos.x += 16;
			} else {
				newPos.x += 8;
			}
			GlobalVariables.maincamera.position.set(newPos);
			GlobalVariables.maincamera.update();
		}
		else if(Gdx.input.isKeyPressed(Keys.SPACE))
		{
			GlobalVariables.maincamera.position.set(GDX2.GetBottomLeft()); //reset
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) 
	{
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) 
	{
		if(amount <= -1)
		{
			GlobalVariables.gsm.getEditor().getGod().ChangeTileIndex(1);
			return true;
		}
		else if(amount >= 1)
		{
			GlobalVariables.gsm.getEditor().getGod().ChangeTileIndex(-1);
			return true;
		}
		else
			return false;
	}

}
