package com.mikesantiago.javatextengine.Items.BaseClasses;

public class Weapon extends Item
{
	private int Damage;
	private WeaponType TypeOfWeapon;
	
	//thank god i found the eclipse function to auto generate these
	
	public int getDamage() {
		return Damage;
	}



	public void setDamage(int damage) {
		Damage = damage;
	}



	public WeaponType getTypeOfWeapon() {
		return TypeOfWeapon;
	}



	public void setTypeOfWeapon(WeaponType typeOfWeapon) {
		TypeOfWeapon = typeOfWeapon;
	}



	public enum WeaponType
	{
		Melee, Ranged, NA
	}
}