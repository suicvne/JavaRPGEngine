package com.mikesantiago.javarpgengine.core;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Content 
{
	private HashMap<String, Texture> textures;
	
	public Content()
	{
		textures = new HashMap<String, Texture>();
	}

	public void loadTexture(String path, String key)
	{
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);
	}
	public Texture getTexture(String key)
	{
		return textures.get(key);
	}
	public void disposeTexture(String key)
	{
		Texture tex = textures.get(key);
		if(tex != null)
			tex.dispose();
	}
}
