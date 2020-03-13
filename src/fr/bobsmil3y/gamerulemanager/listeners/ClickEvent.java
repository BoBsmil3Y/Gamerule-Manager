package fr.bobsmil3y.gamerulemanager.listeners;

import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener{

	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClickEvent (InventoryClickEvent event) {
		System.out.println("Listener fired");
		
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		InventoryView view = event.getView();
		ItemStack item = event.getCurrentItem();
		World world = player.getWorld();
		
		
		if(item == null) return;
		
		if(view.getTitle().contentEquals(ChatColor.DARK_GRAY + "Gamerule Manager")) {
			
			event.setCancelled(true);
			
			if(item.getType() == Material.TRIPWIRE_HOOK ) {
				
				// Boolean
				String name = item.getItemMeta().getDisplayName().substring(4);
				GameRule<Boolean> gamerule = null;
				gamerule = (GameRule<Boolean>) gamerule.getByName(name);
				
				Boolean value = world.getGameRuleValue(gamerule);
				
				world.setGameRule(gamerule, !value);
				
			} else if(item.getType() == Material.PAPER ) {
				
				// Integer
				
			}
		}
		
	}
	
}
