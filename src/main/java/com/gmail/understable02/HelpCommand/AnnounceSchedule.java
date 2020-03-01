package com.gmail.understable02.HelpCommand;

import java.util.TimerTask;

import org.bukkit.Bukkit;

public class AnnounceSchedule extends TimerTask {


	public void run() 
	{
		Bukkit.broadcastMessage("announced");
	}
}
