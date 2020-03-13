package fr.bobsmil3y.gamerulemanager;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bobsmil3y.gamerulemanager.listeners.ClickEvent;


public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		//getServer().getPluginManager().registerEvents(new KillMobListener(this), this);
        getCommand("gmanager").setExecutor(new GameruleManager());
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
		
	
	}
	
}
