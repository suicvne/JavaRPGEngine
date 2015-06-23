package com.mikesantiago.javatextengine.Utils;

import java.util.ArrayList;

import com.mikesantiago.javatextengine.Core.Clock;
import com.mikesantiago.javatextengine.Core.Enemy;

public class WaveManager 
{
	private float timeSinceLastSpawn, spawnTime;
	private Enemy enemyType;
	private ArrayList<Enemy> enemyList;
	private int maxEnemies = 10;
	
	public WaveManager(float spawnTime, Enemy enemyType)
	{
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		enemyList = new ArrayList<Enemy>();
	}
	public WaveManager(float spawnTime, Enemy enemyType, int maxEnemies)
	{
		this.enemyType = enemyType;
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		enemyList = new ArrayList<Enemy>();
		this.maxEnemies = maxEnemies;
	}
	
	
	public void Update()
	{
		timeSinceLastSpawn += Clock.Delta();
		if(timeSinceLastSpawn > spawnTime)
		{
			Spawn();
			timeSinceLastSpawn = 0;
		}
		
		for (Enemy e : enemyList)
		{
			e.Update();
			e.Draw();
		}
	}
	
	public int EnemyCount()
	{
		return enemyList.size();
	}
	
	private void Spawn()
	{
		if(this.maxEnemies != -1)
		{
			if(EnemyCount() >= this.maxEnemies)
				enemyList.remove(0);
		}
		enemyList.add(new Enemy(enemyType.getStartTile(), 
				32, 
				32, 
				enemyType.getCurrentHealth(), 
				enemyType.getMaximumHealth(), 
				enemyType.getSpeed(), 
				enemyType.getTexture(),
				enemyType.getGrid()));
		
	}
}
