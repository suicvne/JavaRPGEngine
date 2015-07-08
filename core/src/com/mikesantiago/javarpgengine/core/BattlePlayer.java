package com.mikesantiago.javarpgengine.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class BattlePlayer 
{
	private int maxHealth, curHealth;
	private List<String> attacks;
	private String name;
	private Texture texture;
	
	public BattlePlayer()
	{
	}
	
	public BattlePlayer(int maxHealth, String[] attacks, String name, Texture texture)
	{
		this.maxHealth = maxHealth;
		this.curHealth = maxHealth;
		
		this.attacks = new ArrayList<String>();
		for(int i = 0; i < attacks.length; i++)
		{
			this.attacks.add(attacks[i]);
		}
		
		this.name = name;
		this.texture = texture;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurHealth() {
		return curHealth;
	}

	public void setCurHealth(int curHealth) {
		this.curHealth = curHealth;
	}

	public List<String> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<String> attacks) {
		this.attacks = attacks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	
}
