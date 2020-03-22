package fr.bobsmil3y.gamerulemanager.listeners;

import fr.bobsmil3y.gamerulemanager.GameruleManager;
import fr.bobsmil3y.gamerulemanager.Main;
import fr.bobsmil3y.gamerulemanager.listeners.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class ChatEvent implements Listener{
	
	private final Main plugin;
	
	public ChatEvent(Main plugin) {
        this.plugin = plugin;
    }
	
	@EventHandler
	public void onChatEvent (AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(! player.hasPermission("gamerulemanager.admin")) return;
		
		System.out.println("Il a la permission");
		
		Player editor = (Player) ClickEvent.getEditor();
		
		if(editor == null) return;
		
		if(player.getName().equals(editor.getName())) {
			
			event.setCancelled(true);
			
			try {
				
				String message = event.getMessage();
				int value = Integer.parseInt(message);
				
				ClickEvent.removeEditor();
				
				new BukkitRunnable() {
			        
		            @Override
		            public void run() {
		            	player.openInventory(GameruleManager.getInventory());
		            }
		            
		        }.runTaskLater(this.plugin, 10);
		        
				
			} catch (NumberFormatException e) {
				
				System.out.println(e);
				player.sendMessage("§cYou have to put a correct number !");
				
			}

		} else System.out.println("pas le même pseudo ou il n'est plus en train d'éditer");
		
		
	}
	
}
