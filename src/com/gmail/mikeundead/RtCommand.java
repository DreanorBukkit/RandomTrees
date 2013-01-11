package com.gmail.mikeundead;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RtCommand implements CommandExecutor 
{
	Map<String, Boolean> map;
	
	public RtCommand()
	{
		this.map = new HashMap<String, Boolean>();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) 
	{
		Player player = null;
		if (sender instanceof Player) 
		{
			player = (Player) sender;
	    }
		if (args.length == 1)
		{
			this.HandleRtCmd(player, args);
		}
        if (args.length == 0) 
        {
        	player.sendMessage(ChatColor.RED + "Not enough arguments! Usage /rt on or /rt off");
        }
        if (args.length > 1) 
        {
        	player.sendMessage(ChatColor.RED + "Too many arguments! Usage /rt on or /rt off");
        }
		
		return true;
	}
	
	private void HandleRtCmd(Player player, String[] args)
	{
		if(args[0].equalsIgnoreCase("on"))
		{
			this.map.put(player.getName(), true);
			player.sendMessage(ChatColor.GREEN + "RandomTrees enabled.");
		}
		if(args[0].equals("off"))
		{
			this.map.put(player.getName(), false);
			player.sendMessage(ChatColor.GREEN + "RandomTrees disabled.");
		}
	}
	
	public Map<String, Boolean> GetAllPlayerSettings()
	{
		return this.map;
	}
	
	public boolean GetPlayerSetting(String playername)
	{
		if(this.map.containsKey(playername))
		{
			return this.map.get(playername);
		}
		return true;		
	}
}
