package com.mikesantiago.javatextengine.Items.BaseClasses;

public class Item
{
	private int ID;
	private String Name;
	private String PluralName;
	private String Description;
	private ItemType TypeOfItem;
	private String CustomItemName;
	private int InventoryIndex;
	
	public enum ItemType
	{
		BasicItem, Weapon, Glitch, Healing
	}
	
	/**
	 * Getters and setters....again...
	 */
	
	public int getID(){return ID;}
	public String getName(){return Name;}
	public String getPluralName(){return PluralName;}
	public String getDescription(){return Description;}
	public ItemType getItemType(){return TypeOfItem;}
	public String getCustomName(){return CustomItemName;}
	public int getInvIndex(){return InventoryIndex;}
	
	public void setID(int newID){ID = newID;}
	public void setName(String newName){Name = newName;}
	public void setPluralName(String newPlName){PluralName = newPlName;}
	public void setDescription(String newDesc){Description = newDesc;}
	public void setItemType(ItemType newType){TypeOfItem = newType;}
	public void setCustomName(String newName){CustomItemName = newName;}
	public void setInvIndex(int newIndex){InventoryIndex = newIndex;}
}