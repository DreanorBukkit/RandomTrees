package com.gmail.mikeundead;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigHandler 
{
	private RandomTrees randomTrees;
	public List<Material> blockList;
	
	public ConfigHandler(RandomTrees randomTrees) 
	{
		this.randomTrees = randomTrees;
		this.blockList = new ArrayList<Material>();
		this.FirstRun();
		this.CreateConfig();
	}
	
	public List<Material> GetBlockList()
	{
		return this.blockList;
	}
	
	public void CreateConfig()
	{
		File file = new File(this.randomTrees.getDataFolder(), "config.yml");
		
		if(!file.exists())
		{
			this.randomTrees.getDataFolder().mkdirs();
	        
			FileConfiguration config = new YamlConfiguration();

			config.set("GrowTreesOnRightClick.Enable", false);
			config.set("GrowTreesOnRightClick.Item", 289);
			config.set("GrowTreesOnRightClick.ItemAmount", 1);
			
			try 
			{
				config.save(file);
			}
	  	  	catch (IOException e) 
	  	  	{
	  	  		e.printStackTrace();
	  	  	}
		}
	}
	
	public boolean GetGrowEnable()
	{
		File file = new File(this.randomTrees.getDataFolder(), "config.yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getBoolean("GrowTreesOnRightClick.Enable");
	}
	
	public int GetGrowItem()
	{
		File file = new File(this.randomTrees.getDataFolder(), "config.yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("GrowTreesOnRightClick.Item");
	}
	
	public int GetGrowItemAmount()
	{
		File file = new File(this.randomTrees.getDataFolder(), "config.yml");
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		return config.getInt("GrowTreesOnRightClick.ItemAmount");
	}
	
	
	public void FirstRun()
	{
		File file = new File(this.randomTrees.getDataFolder(), "blocks.yml");

		if(!file.exists())
		{
			this.randomTrees.getDataFolder().mkdirs();
				        
			FileConfiguration config = new YamlConfiguration();
			
			Integer[] blocks = 
			{
					0,
					1,
					2,
					3,
					4,
					5,
					7,
					8,
					9,
					10,
					11,
					12,
					13,
					14,
					15,
					16,
					17,
					18,
					19,
					20,
					21,
					22,
					23,
					24,
					25,
					29,
					30,
					33,
					35,
					41,
					42,
					43,
					45,
					46,
					47,
					48,
					49,
					52,
					56,
					57,
					58,
					61,
					73,
					79,
					80,
					82,
					84,
					85,
					86,
					87,
					88,
					89,
					91,
					97,
					98,
					101,
					102,
					103,
					110,
					112,
					113,
					116,
					118,
					119,
					120,
					121,
					123,
					125,
					126,
					129,
					130,
					133					
			};

			config.set("Blocks", Arrays.asList(blocks));

			try 
			{
				config.save(file);
			}
	  	  	catch (IOException e) 
	  	  	{
	  	  		e.printStackTrace();
	  	  	}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void LoadConfig()
	{
		File file = new File(this.randomTrees.getDataFolder(), "blocks.yml");

		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		for(Integer block : (List<Integer>) config.getList("Blocks"))
		{			
			this.blockList.add(Material.getMaterial(block));
		}
	}
}
