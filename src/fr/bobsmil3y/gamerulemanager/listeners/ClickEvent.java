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
	@SuppressWarnings("unused")
	private static ItemStack item;
	private static GameRule<Integer> gamerule;
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClickEvent (InventoryClickEvent event) {
		
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
			
			
			if(currentItem.getType() == Material.LEVER ) {
				
				GameRule<Boolean> gamerule = (GameRule<Boolean>) GameRule.getByName(name);
				Boolean value = world.getGameRuleValue(gamerule);
				Boolean defaultValue = world.getGameRuleDefault(gamerule);
				
				world.setGameRule(gamerule, !value);
				ItemStack itemChanged = GameruleManager.changeLoreBoolean(currentItem, value, defaultValue);

				inv.setItem(event.getSlot(), itemChanged);
				
			} else if(currentItem.getType() == Material.PAPER ) {

				GameRule<Integer> gamerule = (GameRule<Integer>) GameRule.getByName(name);
				this.editor = player;
				this.item = currentItem;
				this.gamerule = gamerule;
				
				player.closeInventory();
				
				player.sendMessage("§7Default value : §b" + world.getGameRuleDefault(gamerule) + "§r   §8|   §7Actual value : §b" + world.getGameRuleValue(gamerule));
				player.sendMessage("§aWrite §7the §avalue §7you want to set. Type '§cexit§7' to cancel.");

			} else if(currentItem.getType() == Material.SPRUCE_DOOR) {
				
				if(currentItem.getItemMeta().getDisplayName().equals("§c§lExit")) player.closeInventory();
				
			}
			
			else if(currentItem.getType() == Material.REDSTONE) {
				
				if(! currentItem.getItemMeta().getDisplayName().equals("§c§lReset all")) return;

				GameruleManager.resetAllGamerule(player);
				player.sendMessage("§a§lGM §7| §7All gamerules has their §adefault values§7.");
				player.closeInventory();
				player.openInventory(GameruleManager.createMenu(player));
				
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
