package com.mikesantiago.javarpgengine.BattleStateEnemies;

public enum EnemyAttacks 
{
	Tackle(2, 100, true), Lick(5, 60, true), SpacialRend(100, 100, false);
	
	public int damage, accuracy;
	public boolean physical = true;
	
	/**
	 * 
	 * @param damage amount of damage done to the player
	 * @param accuracy 0-100 (100 being perfect)
	 * @param physical if true, the attack is a physical one which collides with the player
	 */
	EnemyAttacks(int damage, int accuracy, boolean physical)
	{
		this.damage = damage;
		this.accuracy = accuracy;
		this.physical = physical;
	}
}
