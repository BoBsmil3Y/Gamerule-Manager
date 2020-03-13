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
		
		if(!player.hasPermission(command.getPermission())) {
			player.sendMessage(ChatColor.RED + "You don't have permission");
			return false;
		}
		
		
		Inventory inv = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Gamerule Manager");
		
		GameRule<?> gamerule = null;
		String gamerulesNames[] = player.getWorld().getGameRules();
		
		
		for(String name : gamerulesNames) {			
			
			ItemStack item = null;
			ArrayList<String> lore = new ArrayList<String>();
			
			gamerule = gamerule.getByName(name);
			String type = gamerule.getType().toString();
			
			
			// Boolean
			if(type.equals("class java.lang.Boolean")) {
				
				// Check if value is true or false
				boolean bool = (boolean) player.getWorld().getGameRuleValue(gamerule);
				
				List<String> loreListBool = null;
				
				item = new ItemStack(Material.TRIPWIRE_HOOK, 1);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
				if (bool) {
					loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §a§ltrue", "§r ", "§7Click to change the value", "§7to §c" + (!bool), "§r ");
				}else {
					loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §c§lfalse", "§r ", "§7Click to change the value", "§7to §a"  + (!bool),  "§r ");
				}
				lore.addAll(loreListBool);
				meta.setLore(lore);
				item.setItemMeta(meta);

				
			// Integer	
			} else if (type.equals("class java.lang.Integer")) {
				
				int integer = (int) player.getWorld().getGameRuleValue(gamerule);
				
				List<String> loreListInt = Arrays.asList("§r ", "§7§lCurrent value : §b§l" + integer, "§r ", "§7Click to change the value", "§7to a §anumber§7.", "§r ");
				lore.addAll(loreListInt);
				
				item = new ItemStack(Material.PAPER, 1);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
				meta.setLore(lore);
				item.setItemMeta(meta);
				
			}
			
			inv.addItem(item);
			
		}
		
		
		player.openInventory(inv);
		
		return true;
	}

}
