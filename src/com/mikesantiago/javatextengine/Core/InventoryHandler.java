package com.mikesantiago.javatextengine.Core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mikesantiago.javatextengine.Items.ItemMapping;
import com.mikesantiago.javatextengine.Items.BaseClasses.Item;

public final class InventoryHandler
{
	//this list needs to STRICTLY contain Item
	private List<Item> _inventory = new ArrayList<Item>();
	
	public InventoryHandler()
	{
		_inventory.clear();
	}
	
	public void AddItem(Item add)
	{
		_inventory.add(add);
	}
	
	public void AddItems(Item[] add)
	{
		for(int i = 0; i < add.length; i++)
		{
			_inventory.add(add[i]);
		}
	}
	
	public boolean ItemExists(Item item)
	{
		for(int i = 0; i < _inventory.size(); i++)
		{
			if(_inventory.get(i) == item)
				return true;
			else
				return false;
		}
		return false;
	}
	
	public Item RetrieveItem(Item item)
	{
		for(int i = 0; i < _inventory.size(); i++)
		{
			if(_inventory.get(i) == item)
				return _inventory.get(i);
		}
		return new com.mikesantiago.javatextengine.Items.ItemNull();
	}
	public Item RetrieveItem(int index)
	{
		return _inventory.get(index);
	}
	
	public void RemoveItem(Item item)
	{
		for(int i = 0; i < _inventory.size(); i++)
		{
			if(_inventory.get(i) == item)
				_inventory.remove(i);
		}
	}
	public void RemoveItem(int index)
	{
		_inventory.remove(index);
	}
	
	public void ReadFromFile(String filePath) throws Exception
	{
		FileReader reader = new FileReader(filePath);
		BufferedReader br = new BufferedReader(reader);
		
		String curLine = br.readLine();
		int curLineIndex = 0;
		while(curLine != null)
		{
			if(curLineIndex == 0 && !curLine.startsWith("#64#"))
			{
				//throw error, break, whatever
				break;
			}
			if(curLine.startsWith("//") || curLine.startsWith("#64#") || curLine.toLowerCase().contains("#INV2#".toLowerCase()) || curLine.toLowerCase().contains("#CHEST".toLowerCase()))
			{
				if(curLine.startsWith("#CHEST"))
					break;
			}
			else
			{
				if(curLine.contains(","))
				{
					//This line contains a comma, used to split for custom item names. So,
					//0:2,"Custom Name"
					//0=inv index
					//2=id
					//"Custom Name"=Custom name
					String[] parts = curLine.split(":|,");
					try
					{
						int invSlot = Integer.parseInt(parts[0]);
						int itemID = Integer.parseInt(parts[1]);
						String customItemName = parts[2].replace("\"", "");
						Item toAdd = ItemMapping.GetItemByID(itemID);
						toAdd.setCustomName(customItemName);
						toAdd.setInvIndex(invSlot);
						_inventory.add(toAdd);
					}
					catch(Exception ex)
					{
						System.out.println("Exception while parsing: " + ex.getMessage());
					}
				}
				else
				{
					//almost same, just with the first two parts instead of all 3
					String[] parts = curLine.split(":");
					try
					{
						int invSlot = Integer.parseInt(parts[0]);
						int itemID = Integer.parseInt(parts[1]);
						Item toAdd = ItemMapping.GetItemByID(itemID);
						toAdd.setInvIndex(invSlot);
						_inventory.add(toAdd);
					}
					catch(Exception ex)
					{
						System.out.println("Exception while parsing: " + ex.getMessage());
					}
				}
			}
			curLine = br.readLine();
			curLineIndex++;
			//to read next line: curLine = br.readLine(); curLineIndex++;
		}
		
		reader.close();
	}
	
	public void WriteToFile(String filePath) throws IOException
	{
		FileWriter writer = new FileWriter(filePath);
		writer.write("#64#\n");
		writer.write("#INV2#");
		for(int i = 0; i < _inventory.size(); i++)
		{
			Item retrieved = _inventory.get(i);
			if(retrieved.getCustomName() != null)
			{
				writer.write(String.format("%s:%s,\"%s\"", i, retrieved.getID(), retrieved.getCustomName()));
			}
			else
			{
				writer.write(String.format("%s:%s", i, retrieved.getID()));
			}
		}
		//TODO: implement chest writing, already done on C# variant but chests don't even EXIST in this early version of the Java port
		writer.flush();
		writer.close();
	}
}