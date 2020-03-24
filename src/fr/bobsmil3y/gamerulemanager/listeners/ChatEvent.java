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


public class ChatEvent implements Listener{
	
	private final Main plugin;
	
	public ChatEvent(Main plugin) {
        this.plugin = plugin;
    }
	
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
					player.sendMessage("§cEdit cancel");
					ClickEvent.removeEditor();
					ClickEvent.removeGamerule();
					return;
				}
				int value = Integer.parseInt(message);

				GameRule<Integer> gamerule = ClickEvent.getGamerule();
				World world = player.getWorld();
				world.setGameRule(gamerule, value);
				ClickEvent.removeEditor();
				ClickEvent.removeGamerule();
				player.sendMessage("§aValue changed with succes !");
				
				new BukkitRunnable() {
			        
		            @Override
		            public void run() {
		            	player.openInventory(GameruleManager.createMenu(player));
		            }
		            
		        }.runTaskLater(this.plugin, 5);
				
		        
			} catch (NumberFormatException e) {
				
				System.out.println(e);
				player.sendMessage("§cYou have to put a correct number ! Only integer is allow.");
				
			}

			
		}
		
		
	}
	
}
