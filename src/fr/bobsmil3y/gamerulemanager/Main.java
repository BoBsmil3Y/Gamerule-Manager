package fr.bobsmil3y.gamerulemanager;

import org.bukkit.plugin.java.JavaPlugin;

import fr.bobsmil3y.gamerulemanager.listeners.ChatEvent;
import fr.bobsmil3y.gamerulemanager.listeners.ClickEvent;

/**
 * 
 * Main class of the plugin.
 * Used to register events and command.
 * 
 * @author BoBsmil3Y
 * @version 1.0
 */

public class Main extends JavaPlugin {
	
	
	/**
	 * Trigger when the server start.
	 * */
	@Override
	public void onEnable() {
		
        getCommand("gamerulemanager").setExecutor(new GameruleManager());
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
	
	}
	
}
