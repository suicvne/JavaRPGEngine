package com.mikesantiago.javarpgengine.BattleStateEnemies;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Enemy 
{
	private String name;
	private int maxHealth, curHealth, id;
	private List<EnemyAttacks> attacks;
	private Texture enemyBattleTexture;
	
	public Enemy(){}
	
	public Enemy(String name, int health, int id, EnemyAttacks[] attacks, Texture texture)
	{
		this.name = name;
		this.maxHealth = health;
		this.curHealth = health;
		this.id = id;
		
		this.attacks = new ArrayList<EnemyAttacks>();
		for(int i = 0; i < attacks.length; i++)
		{
			this.attacks.add(attacks[i]);
		}
		
		this.enemyBattleTexture = texture;
	}
	
	/**
	 * 
	 */
	public void Attack()
	{
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxHealth() {return this.maxHealth;}
	public int getCurHealth() {return this.curHealth;}
	
	public void setMaxHealth(int health) {this.maxHealth = health;}
	public void setCurHealth(int health) {this.curHealth = health;}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<EnemyAttacks> getAttacks() {
		return attacks;
	}

	public void setAttacks(List<EnemyAttacks> attacks) {
		this.attacks = attacks;
	}
	public void setAttacks(EnemyAttacks[] attacks)
	{
		this.attacks = new ArrayList<EnemyAttacks>();
		for(int i = 0; i < attacks.length; i++)
		{
			this.attacks.add(attacks[i]);
		}
	}
	
	public Texture getEnemyBattleTexture() {
		return enemyBattleTexture;
	}

	public void setEnemyBattleTexture(Texture enemyBattleTexture) {
		this.enemyBattleTexture = enemyBattleTexture;
	}

	
}
