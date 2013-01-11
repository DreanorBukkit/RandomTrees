package com.gmail.mikeundead;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomTrees extends JavaPlugin
{
	public Logger log;
	private TreeGrow treeGrow;
	private RtCommand rtCommand;
	private ConfigHandler configHandler;
	
	public void onEnable()
	{			
		this.log = getLogger();
		
		this.configHandler = new ConfigHandler(this);
		this.rtCommand = new RtCommand();
		this.treeGrow = new TreeGrow(this, this.configHandler, this.rtCommand);
	
		getServer().getPluginManager().registerEvents(this.treeGrow, this);
		getCommand("rt").setExecutor(this.rtCommand);
		
		this.log.info("Random Trees has been enabled!");
	}
	 
	public void onDisable()
	{ 
		this.log.info("Random Trees has been disabled.");
	}
}
