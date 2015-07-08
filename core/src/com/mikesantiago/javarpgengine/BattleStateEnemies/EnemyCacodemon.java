package com.mikesantiago.javarpgengine.BattleStateEnemies;

import com.badlogic.gdx.graphics.Texture;
import com.mikesantiago.javarpgengine.handlers.GlobalVariables;

public class EnemyCacodemon extends Enemy 
{

	public EnemyCacodemon() 
	{
		this.setName("Cacodemon");
		this.setMaxHealth(125);
		this.setCurHealth(125);
		this.setId(0);
		this.setAttacks(new EnemyAttacks[]{EnemyAttacks.Tackle, EnemyAttacks.Lick});
		this.setEnemyBattleTexture(GlobalVariables.content.getTexture("enemy"));
	}

	
}
