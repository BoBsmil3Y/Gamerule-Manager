package fr.bobsmil3y.gamerulemanager.listeners;

import fr.bobsmil3y.gamerulemanager.listeners.ClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatEvent implements Listener{

	@EventHandler
	public void onChatEvent (AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		
		if(! player.hasPermission("gamerulemanager.admin")) return;
		
		System.out.println("Il a la permission");
		
		Player editor = (Player) ClickEvent.getEditor();
		
		if(player.getName().equals(editor.getName())) {
			
			System.out.println("même pseudo");
			
		} else System.out.println("pas le même pseudo");
		
		
	}
	
}
