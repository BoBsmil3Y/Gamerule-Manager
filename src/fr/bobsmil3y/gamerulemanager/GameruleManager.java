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
		
		if(!(sender instanceof Player)) {
			ConsoleCommandSender console = sender.getServer().getConsoleSender();
			console.sendMessage(ChatColor.RED + "Command has to be perform by a player");
			return false;
		} 
		
		Player player = (Player) sender;
		
		if(!player.hasPermission(command.getPermission())) {
			return false;
		}
		
		player.openInventory(createMenu(player));
		
		return true;
	}
	
	
	
	public static Inventory createMenu(Player player) {
		
		Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Gamerule Manager");
		
		GameRule<?> gamerule = null;
		String gamerulesNames[] = player.getWorld().getGameRules();		
		
		for(String name : gamerulesNames) {			
			
			gamerule = GameRule.getByName(name);
			String type = gamerule.getType().toString();
			
			
			// If the GameRule is a Boolean type
			if(type.equals("class java.lang.Boolean")) {
				
				Boolean defaultValue = (Boolean) player.getWorld().getGameRuleDefault(gamerule);
				inventory.addItem(createItemBoolean(player, gamerule, name, defaultValue));
				
			// If the GameRule is a Integer type
			} else if (type.equals("class java.lang.Integer")) {
				
				int defaultValue = (int) player.getWorld().getGameRuleDefault(gamerule);
				inventory.addItem(createItemInteger(player, gamerule, name, defaultValue));
				
			}
						
		}
		
		return inventory;
	}
	
	
	public static ItemStack createItemBoolean(Player player, GameRule<?> gamerule, String name, Boolean defaultValue) {
		
		boolean bool = (boolean) player.getWorld().getGameRuleValue(gamerule);
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreListBool = null;
		
		if(bool) {
			loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §a§ltrue", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
		} else loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §c§lfalse", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
		
		lore.addAll(loreListBool);
		
		ItemStack item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	public static ItemStack createItemInteger(Player player, GameRule<?> gamerule, String name, int defaultValue) {
		
		int integer = (int) player.getWorld().getGameRuleValue(gamerule);
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreListInt = Arrays.asList("§r ", "§7§lCurrent value : §b§l" + integer, "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value", "§7to an other number§7.", "§r ");
		lore.addAll(loreListInt);
		
		ItemStack item = new ItemStack(Material.PAPER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	public static ItemStack changeLoreBoolean(ItemStack item, Boolean bool, Boolean defaultValue) {
		ItemMeta meta = item.getItemMeta();
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreList = null;
		
		if(!bool) {
			loreList = Arrays.asList("§r ", "§7§lCurrent value : §a§ltrue", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
		} else loreList = Arrays.asList("§r ", "§7§lCurrent value : §c§lfalse", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
		
		lore.addAll(loreList);
		
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}
	
}
