package fr.bobsmil3y.gamerulemanager.listeners;

import fr.bobsmil3y.gamerulemanager.GameruleManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.type.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ClickEvent implements Listener{

	private static Player editor;
	
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
			
			String name = item.getItemMeta().getDisplayName().substring(4);
			
			if(item.getType() == Material.TRIPWIRE_HOOK ) {
				// Boolean
				
				GameRule<Boolean> gamerule = null;
				gamerule = (GameRule<Boolean>) gamerule.getByName(name);
				
				Boolean value = world.getGameRuleValue(gamerule);
				
				world.setGameRule(gamerule, !value);
				ItemStack itemChanged = GameruleManager.changeLoreBoolean(item, value);
				inv.setItem(event.getSlot(), itemChanged);
				
			} else if(item.getType() == Material.PAPER ) {
				// Integer
				
				GameRule<Integer> gamerule = null;
				gamerule = (GameRule<Integer>) gamerule.getByName(name);
				
				
				player.sendMessage(ChatColor.GRAY + "Default value : " + ChatColor.AQUA + world.getGameRuleDefault(gamerule));
				player.sendMessage(ChatColor.GRAY + "Actual value : " + ChatColor.AQUA + world.getGameRuleValue(gamerule));
				player.sendMessage(ChatColor.GRAY + "Write the value you want to put");
				
				this.editor = player;
				
				player.closeInventory();
								
			}
		}
		
	}
	
	public static Player getEditor() {
		
		return editor;
				
	}
	
	public void removeEditor() {
		
		ClickEvent.editor = null;
		
	}
	
}
