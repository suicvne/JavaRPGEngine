package com.mikesantiago.javatextengine.NPCs;

import com.mikesantiago.javatextengine.NPCs.BaseClasses.*;
import com.mikesantiago.javatextengine.NPCs.BaseClasses.GenderHandler.Gender;

public final class Player extends LivingEntity
{
	private double PlayerHeight;
	private double PlayerWeight;
	private Gender PlayerGender;
	//private Weapon EquippedWeapon;
	private PlayerStatus CurrentPlayerStatus;
	
	public Player()
	{
		this.setID(0);
		this.setName("Fido");
		this.setCurHP(10);
		this.setMaxHP(10);
		//this.setEquippedWeapon
		
	}
	
	public enum PlayerStatus
	{
		OK, Dead
	}
	
	/**
	 * Getters and setters
	 */
	public double getPlayerHeight(){return PlayerHeight;}
	public double getPlayerWeight(){return PlayerWeight;}
	public Gender getPlayerGender(){return PlayerGender;}
	public PlayerStatus getPlayerStatus(){return CurrentPlayerStatus;}
	
	public void setPlayerHeight(double plrHeight){PlayerHeight = plrHeight;}
	public void setPlayerWeight(double plrWeight){PlayerWeight = plrWeight;}
	public void setPlayerGender(Gender plrGender){PlayerGender = plrGender;}
	public void setPlayerStatus(PlayerStatus plrStatus){CurrentPlayerStatus = plrStatus;}
}