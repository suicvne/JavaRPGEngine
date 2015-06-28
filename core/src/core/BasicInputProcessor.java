package core;

import com.badlogic.gdx.InputProcessor;
import com.mikesantiago.gdx2.GDX2;

public class BasicInputProcessor implements InputProcessor 
{

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
			GDX2.god.ChangeTileIndex(1);
			return true;
		}
		else if(amount >= 1)
		{
			GDX2.god.ChangeTileIndex(-1);
			return true;
		}
		else
			return false;
	}

}
