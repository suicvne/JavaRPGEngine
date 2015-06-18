package com.mikesantiago.javatextengine.Items;

import com.mikesantiago.javatextengine.Items.BaseClasses.Item;

public final class ItemNull extends Item
{
	public ItemNull()
	{
		this.setName("Null Item");
		this.setPluralName("Null Items");
		this.setID(-1);
		this.setDescription("This item is STRICTLY for testing/error prevention.");
		this.setItemType(ItemType.Glitch);
	}
}