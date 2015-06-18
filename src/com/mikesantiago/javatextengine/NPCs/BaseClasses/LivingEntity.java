package com.mikesantiago.javatextengine.NPCs.BaseClasses;

public class LivingEntity
{
	private int ID;
	private String Name;
	private int CurrentHP;
	private int MaxHP;
	//private item DroppedItem;
	private boolean Aggressive;
	
	/*
	 * public Item DropItem()
	 * {
	 * 	return DroppedItem;
	 * }
	 */
	
	/**
	 * Now for lots of getters/setters
	 */
	public int getID() {return ID;}
	public String getName() {return Name;}
	public int getCurHP() {return CurrentHP;}
	public int getMaxHP() {return MaxHP;}
	public boolean isAggressive(){return Aggressive;}
	
	public void setID(int newID){ID = newID;}
	public void setName(String newName){Name = newName;}
	public void setCurHP(int newHP){MaxHP = newHP;}
	public void setMaxHP(int newHP){MaxHP = newHP;}
	public void setAggressive(boolean newAgg){Aggressive = newAgg;}
}