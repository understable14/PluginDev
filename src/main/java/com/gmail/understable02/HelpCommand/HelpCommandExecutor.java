package com.gmail.understable02.HelpCommand;




import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class HelpCommandExecutor implements CommandExecutor {
	private final HelpCommand plugin;
	boolean TimerRunning = false;
	Timer timer;
	
	
	HelpCommandExecutor(HelpCommand plugin) 
	{
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
			if (!TimerRunning) {plugin.timer.cancel();}
			plugin.reloadConfig();
			plugin.config = plugin.getConfig();
			help = plugin.getConfig().getStringList("message"); 
			
			
			final TimerTask repeatedTask = new TimerTask() 
			
			{
				java.util.List<String> announcer = plugin.config.getStringList("announcer");
				public void run() 
				{
					Random rand = new Random();
					int StringIndex = rand.nextInt(announcer.size());
					String msg = announcer.get(StringIndex);					
					Bukkit.broadcastMessage(msg);
															
				}
			};
			
			long delay = plugin.config.getLong("timer") * 1000;
			if(TimerRunning) {timer.cancel();}
			timer = new Timer();
			timer.schedule(repeatedTask, delay, delay);
			TimerRunning = true;
			sender.sendMessage("reloaded");
			}
			return true;
		}

	return false;
	}
	
}
