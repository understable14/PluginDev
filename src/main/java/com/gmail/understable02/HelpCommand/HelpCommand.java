package com.gmail.understable02.HelpCommand;

import java.awt.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;




public final class HelpCommand extends JavaPlugin {
	FileConfiguration config = this.getConfig();
	Timer timer = new Timer("Timer");
	
	
	TimerTask repeatedTask = new TimerTask() 
	{
		java.util.List<String> announcer = config.getStringList("announcer");
		public void run() 
		{
			Random rand = new Random();
			int StringIndex = rand.nextInt(announcer.size());
			String msg = announcer.get(StringIndex);					
			Bukkit.broadcastMessage(msg);
		}
	};
	
	public void StartTimer() 
	{
		long delay = config.getLong("timer") * 1000;
		timer.schedule(repeatedTask, 0, delay);
	}
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		config.addDefault("message", "hi");
		config.addDefault("timer", 1000 );
		config.addDefault("announcer", "Hi");
		config.options().copyDefaults(true);
		saveConfig();
		this.getCommand("hcommands").setExecutor(new HelpCommandExecutor(this));
		this.getCommand("hreload").setExecutor(new HelpCommandExecutor(this));		
		StartTimer();
		
	}


	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
}
