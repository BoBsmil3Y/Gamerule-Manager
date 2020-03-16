package fr.bobsmil3y.gamerulemanager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class GameruleManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		// If sender is console -> error messages
		if(!(sender instanceof Player)) {
			ConsoleCommandSender console = sender.getServer().getConsoleSender();
			console.sendMessage(ChatColor.RED + "Command has to be perform by a player");
			return false;
		} 
		
		Player player = (Player) sender;
		
		// If sender is console  -> error messages
		if(!player.hasPermission(command.getPermission())) {
			return false;
		}
		
		player.openInventory(createMenu(player));
		
		return true;
	}
	
	public Inventory createMenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Gamerule Manager");
		
		GameRule<?> gamerule = null;
		String gamerulesNames[] = player.getWorld().getGameRules();
		
		
		for(String name : gamerulesNames) {			
			
			gamerule = gamerule.getByName(name);
			String type = gamerule.getType().toString();
			
			// If the GameRule is a Boolean type
			if(type.equals("class java.lang.Boolean")) {
				inv.addItem(createItemBoolean(player, gamerule, name));
			// If the GameRule is a Integer type
			} else if (type.equals("class java.lang.Integer")) {
				inv.addItem(createItemInteger(player, gamerule, name));
			}
			
		}
		
		return inv;
	}
	
	public ItemStack createItemBoolean(Player player, GameRule<?> gamerule, String name) {
		
		boolean bool = (boolean) player.getWorld().getGameRuleValue(gamerule);
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreListBool = null;
		
		if (bool) {
			loreListBool = Arrays.asList("�r ", "�7�lCurrent value : �a�l" + bool , "�r ", "�7Click to change the value", "�7to �c" + (!bool), "�r ");
		}else {
			loreListBool = Arrays.asList("�r ", "�7�lCurrent value : �c�l" + bool, "�r ", "�7Click to change the value", "�7to �a"  + (!bool),  "�r ");
		}
		lore.addAll(loreListBool);
		
		ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack createItemInteger(Player player, GameRule<?> gamerule, String name) {
		
		int integer = (int) player.getWorld().getGameRuleValue(gamerule);
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreListInt = Arrays.asList("�r ", "�7�lCurrent value : �b�l" + integer, "�r ", "�7Click to change the value", "�7to a �anumber�7.", "�r ");
		lore.addAll(loreListInt);
		
		ItemStack item = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack changeLoreBoolean(ItemStack item, Boolean bool) {
		
		ItemMeta meta = item.getItemMeta();
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreList = null;
		
		if(bool) {
			loreList = Arrays.asList("�r ", "�7�lCurrent value : �c�lfalse", "�r ", "�7Click to change the value", "�7to �atrue�7.", "�r ");
		} else {
			loreList = Arrays.asList("�r ", "�7�lCurrent value : �a�ltrue", "�r ", "�7Click to change the value", "�7to �cfalse�7.", "�r ");
		}
		
		lore.addAll(loreList);
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

}
