package com.gmail.mikeundead;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;

public class TreeGrow implements Listener
{
	@SuppressWarnings("unused")
	private RandomTrees randomTrees;
	private ConfigHandler configHandler;
	private RtCommand rtCommand;
	List<Material> blocks;
	
	public TreeGrow(RandomTrees randomTrees, ConfigHandler configHandler, RtCommand rtCommand)
	{
		this.randomTrees = randomTrees;
		this.configHandler = configHandler;
		this.rtCommand = rtCommand;
		
		this.configHandler.LoadConfig();
		this.blocks = this.configHandler.GetBlockList();
	}
	
	@EventHandler
	public void onTreeGrow(StructureGrowEvent event)
	{
		if(this.rtCommand.GetPlayerSetting(event.getPlayer().getName()))
		{
			Material material = this.GetRndMaterial();
			byte color = this.HandleWoolColor();
			
			if(event.isFromBonemeal())
			{			
				for(BlockState blockState : event.getBlocks())
				{
					if(blockState.getType() == Material.LEAVES)
					{
						this.HandleSpecialBlock(material, blockState, color);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEvent event)
	{
		if(this.configHandler.GetGrowEnable())
		{
		    Action action = event.getAction();
		    Player player = event.getPlayer();
		    ItemStack itemStack = player.getItemInHand();
		    
			if(action == Action.RIGHT_CLICK_BLOCK)
			{
				if(event.getClickedBlock().getType() == Material.SAPLING)
				{					
					if(itemStack.getTypeId() == this.configHandler.GetGrowItem() && itemStack.getAmount() == this.configHandler.GetGrowItemAmount())
					{	
						
					}
				}
			}
		}
	}
	
	private void HandleSpecialBlock(Material material, BlockState blockState, byte color) 
	{
		if(material == Material.WOOL)
		{
			blockState.setType(material);
			blockState.setRawData(color);
			return;
		}
		
		blockState.setType(material);
	}

	private byte HandleWoolColor()
	{
		Random rnd = new Random();
		int colorID = rnd.nextInt(16);
		
		return (byte)colorID;
	}
	
	private Material GetRndMaterial()
	{				
		Random rnd = new Random();
		
		int blockID = rnd.nextInt(this.blocks.size());
				
		return this.blocks.get(blockID);
	}
}
