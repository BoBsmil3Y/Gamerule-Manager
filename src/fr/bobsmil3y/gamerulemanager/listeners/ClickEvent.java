package fr.bobsmil3y.gamerulemanager.listeners;

import fr.bobsmil3y.gamerulemanager.GameruleManager;


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

	private static Player editor;
	private static ItemStack item;
	private static GameRule<Integer> gamerule;
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClickEvent (InventoryClickEvent event) {
		System.out.println("Listener fired");
		
		Player player = (Player) event.getWhoClicked();
		Inventory inv = event.getInventory();
		InventoryView view = event.getView();
		ItemStack currentItem = event.getCurrentItem();
		World world = player.getWorld();
		
		if(currentItem == null) return;
		
		if(view.getTitle().contentEquals(ChatColor.DARK_GRAY + "Gamerule Manager")) {
			
			event.setCancelled(true);
			
			if(! currentItem.hasItemMeta()) return;
			
			String name = currentItem.getItemMeta().getDisplayName().substring(4);
			
			
			if(currentItem.getType() == Material.TRIPWIRE_HOOK ) {
				// Boolean
				GameRule<Boolean> gamerule = null;
				gamerule = (GameRule<Boolean>) gamerule.getByName(name);

				Boolean value = world.getGameRuleValue(gamerule);
				Boolean defaultValue = world.getGameRuleDefault(gamerule);
				
				world.setGameRule(gamerule, !value);
				ItemStack itemChanged = GameruleManager.changeLoreBoolean(currentItem, value, defaultValue);

				inv.setItem(event.getSlot(), itemChanged);
			} else if(currentItem.getType() == Material.PAPER ) {
				// Integer
				
				GameRule<Integer> gamerule = null;
				gamerule = (GameRule<Integer>) gamerule.getByName(name);
				this.editor = player;
				this.item = item;
				this.gamerule = gamerule;
				
				player.closeInventory();
				
				player.sendMessage(ChatColor.GRAY + "Default value : " + ChatColor.AQUA + world.getGameRuleDefault(gamerule));
				player.sendMessage(ChatColor.GRAY + "Actual value : " + ChatColor.AQUA + world.getGameRuleValue(gamerule));
				player.sendMessage(ChatColor.GRAY + "Write the value you want to put. Type 'exit' if you want to cancel.");
				
				
				
			}
		}
		
	}
	
	public static Player getEditor() {	
		return editor;	
	}
	
	public static void removeEditor() {
		editor = null;
	}
	
	public static GameRule<Integer> getGamerule(){
		return gamerule;
	}
	
	public static void removeGamerule() {
		gamerule = null;
	}
	
}
