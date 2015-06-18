package com.mikesantiago.javatextengine.Items.BaseClasses;

public class HealingItem extends Item
{
	private int HitpointsHealed;
	
	/**
	 * Getters and setters
	 */
	public int getHitpointsHealed(){return HitpointsHealed;}
	
	public void setHitpointsHealed(int newHPHeal){HitpointsHealed = newHPHeal;}
}