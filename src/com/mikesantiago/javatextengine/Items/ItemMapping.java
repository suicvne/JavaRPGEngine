package com.mikesantiago.javatextengine.Items;
import com.mikesantiago.javatextengine.Items.BaseClasses.*;

public class ItemMapping
{
	public static Item GetItemByID(int ID)
	{
		switch (ID)
		{
		case -1:
			return new ItemNull();
		}
		
		return new ItemNull();
	}
}