package com.gmail.understable02.HelpCommand;




import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class HelpCommandExecutor implements CommandExecutor {
	private final HelpCommand plugin;
	
	
	public HelpCommandExecutor(HelpCommand plugin) {
		this.plugin = plugin;
		
	}

	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		List<String> help = plugin.getConfig().getStringList("message");
		if(cmd.getName().equalsIgnoreCase("hcommands")) 
		{	
			if(sender.hasPermission("helpcommand.use")) {
				for (String message: help) 
					{
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));										
					}
		}
		return true;
	    }
		if(cmd.getName().equalsIgnoreCase("hreload")) 
		{
			if(sender.hasPermission("helpcommand.reload")) 
			{
			plugin.reloadConfig();
			plugin.config = plugin.getConfig();
			help = plugin.getConfig().getStringList("message"); 
			sender.sendMessage("reloaded");
			
			}
			return true;
		}

	return false;
	}
	
}
