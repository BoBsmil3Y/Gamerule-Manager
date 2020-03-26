package fr.bobsmil3y.gamerulemanager.listeners;

import fr.bobsmil3y.gamerulemanager.GameruleManager;
import fr.bobsmil3y.gamerulemanager.Main;
import fr.bobsmil3y.gamerulemanager.listeners.ClickEvent;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * 
 * Event
 * Trigger when a player has wrote on chat.
 * 
 * @author BoBsmil3Y
 * @version 1.0
 */

public class ChatEvent implements Listener{
	
	
	/**
	 * Instance of the plugin.
	 * */
	private final Main plugin;
	
	
	/**
	 * Set the instance of the plugin.
	 * 
	 * @param plugin
	 * */
	public ChatEvent(Main plugin) {
        this.plugin = plugin;
    }
	
	
	/**
	 * Called functions depends on the message sended.
	 * Used to get the player input to modify integer gamerule value. 
	 * 
	 * @param event
	 * */
	@EventHandler
	public void onChatEvent (AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(! player.hasPermission("gamerulemanager.admin")) return;
				
		Player editor = (Player) ClickEvent.getEditor();
		
		if(editor == null) return;
		
		if(player.getName().equals(editor.getName())) {
			
			event.setCancelled(true);
			
			try {
				
				String message = event.getMessage();
				if(message.equals("exit")) {
					player.sendMessage("§a§lGM §7| §7Edit mode §acancel§7.");
					ClickEvent.removeEditor();
					ClickEvent.removeGamerule();
					new BukkitRunnable() {
				        
			            @Override
			            public void run() {
			            	player.openInventory(GameruleManager.createMenu(player));
			            }
			            
			        }.runTaskLater(this.plugin, 5);
					return;
				}
				
				int value = Integer.parseInt(message);

				GameRule<Integer> gamerule = ClickEvent.getGamerule();
				World world = player.getWorld();
				world.setGameRule(gamerule, value);
				ClickEvent.removeEditor();
				ClickEvent.removeGamerule();
				player.sendMessage("§a§lGM §7| §7Value §achanged §7with succes !");
				
				new BukkitRunnable() {
			        
		            @Override
		            public void run() {
		            	player.openInventory(GameruleManager.createMenu(player));
		            }
		            
		        }.runTaskLater(this.plugin, 5);
				
		        
			} catch (NumberFormatException e) {
				
				System.out.println(e);
				player.sendMessage("§c§lGM §7| §7You have to put a §ccorrect number§7 ! Only §cinteger§7 is allow.");
				
			}
			
		}
		
	}
	
}
