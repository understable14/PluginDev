package com.gmail.understable02.HelpCommand;

import java.util.Timer;
import java.util.TimerTask;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;




public final class HelpCommand extends JavaPlugin {
	FileConfiguration config = this.getConfig();
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		config.addDefault("message", "hi");
		config.addDefault("timer", 1000 );
		config.options().copyDefaults(true);
		saveConfig();
		this.getCommand("hcommands").setExecutor(new HelpCommandExecutor(this));
		this.getCommand("hreload").setExecutor(new HelpCommandExecutor(this));
		TimerTask repeatedTask = new AnnounceSchedule();
		Timer timer = new Timer("Timer");
		timer.schedule(repeatedTask, 0, config.getLong("timer"));
		
	}


	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
}
