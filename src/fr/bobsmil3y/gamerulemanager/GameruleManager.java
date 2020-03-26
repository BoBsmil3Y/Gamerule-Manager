package fr.bobsmil3y.gamerulemanager;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * 
 * Class called when /gmanager command is executed.
 * Used to manage items and main menu.
 * 
 * @author BoBsmil3Y
 * @version 1.0
 */

public class GameruleManager implements CommandExecutor {
	
	/**
	 * Checks the player and open him an inventory with items.
	 * 
	 * @param sender
	 * @param command
	 * @param label
	 * @param args
	 * 
	 * @return ItemStack
	 * */
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
	
	
	/**
	 * Create the menu with all gamerule items.
	 * 
	 * @param player
	 * 
	 * @return ItemStack
	 * */
	public static Inventory createMenu(Player player) {
		
		Inventory inventory = Bukkit.createInventory(null, 45, ChatColor.DARK_GRAY + "Gamerule Manager");
		
		GameRule<?> gamerule = null;
		String gamerulesNames[] = player.getWorld().getGameRules();		
		
		for(String name : gamerulesNames) {			
			
			gamerule = GameRule.getByName(name);
			String type = gamerule.getType().toString();
			
			if(type.equals("class java.lang.Boolean")) {
				
				Boolean defaultValue = (Boolean) player.getWorld().getGameRuleDefault(gamerule);
				inventory.addItem(createItemBoolean(player, gamerule, name, defaultValue));
				
			} else if (type.equals("class java.lang.Integer")) {
				
				int defaultValue = (int) player.getWorld().getGameRuleDefault(gamerule);
				inventory.addItem(createItemInteger(player, gamerule, name, defaultValue));
				
			}
						
		}
		
		inventory.setItem((inventory.getSize() - 1), createItemExit());
		inventory.setItem((inventory.getSize() - 2), createItemReset());
		
		return inventory;
	}
	
	
	/**
	 * Create the item for Boolean type gamerule.
	 * 
	 * @param player
	 * @param gamerule
	 * @param name
	 * @param defaultValue
	 * 
	 * @return ItemStack
	 * */
	public static ItemStack createItemBoolean(Player player, GameRule<?> gamerule, String name, Boolean defaultValue) {
		
		boolean bool = (boolean) player.getWorld().getGameRuleValue(gamerule);
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreListBool = null;
		ItemStack item = new ItemStack(Material.LEVER, 1);
		ItemMeta meta = item.getItemMeta();
		
		if(bool) {
			loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §a§ltrue", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
			meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		} else {
			loreListBool = Arrays.asList("§r ", "§7§lCurrent value : §c§lfalse", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
		}
		
		lore.addAll(loreListBool);
		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		
		return item;
	}
	
	
	/**
	 * Create the item for integer type gamerule.
	 * 
	 * @param player
	 * @param gamerule
	 * @param name
	 * @param defaultValue
	 * 
	 * @return ItemStack
	 * */
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
	
	
	/**
	 * Change the lore of an item with a boolean gamerule value assigned.
	 * 
	 * @param item
	 * @param value
	 * @param defaultValue
	 * 
	 * @return ItemStack
	 * */
	public static ItemStack changeLoreBoolean(ItemStack item, Boolean value, Boolean defaultValue) {
		ItemMeta meta = item.getItemMeta();
		
		ArrayList<String> lore = new ArrayList<String>();
		List<String> loreList = null;

		if(!value) {
			loreList = Arrays.asList("§r ", "§7§lCurrent value : §a§ltrue", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
			meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		} else {
			loreList = Arrays.asList("§r ", "§7§lCurrent value : §c§lfalse", "§r ", "§7§lDefault value : §7" + defaultValue, "§r ", "§7Click to change the value to", "§7true or false.", "§r ");
			if (meta.hasEnchant(Enchantment.SILK_TOUCH)) meta.removeEnchant(Enchantment.SILK_TOUCH);
		}
		
		lore.addAll(loreList);
		meta.setLore(lore);
		item.setItemMeta(meta);

		return item;
	}
	
	
	/**
	 * Create the exit item.
	 * 
	 * @return item
	 * */
	public static ItemStack createItemExit() {
		
		ArrayList<String> lore = new ArrayList<String>();
		ItemStack item = new ItemStack(Material.SPRUCE_DOOR, 1);
		ItemMeta meta = item.getItemMeta();
		
		List<String> loreList = Arrays.asList("§r ", "§7Click to close the menu", "§r ");

		lore.addAll(loreList);
		meta.setDisplayName("§c§lExit");
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	/**
	 * Create the reset item.
	 * 
	 * @return item
	 * */
	public static ItemStack createItemReset() {
		
		ArrayList<String> lore = new ArrayList<String>();
		ItemStack item = new ItemStack(Material.REDSTONE, 1);
		ItemMeta meta = item.getItemMeta();
		
		List<String> loreList = Arrays.asList("§r ", "§7Allow you to reset all the", "§7gamerule value to the default.", "§r ");

		lore.addAll(loreList);
		meta.setDisplayName("§c§lReset all");
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	
	/**
	 * Reset all the gamerule value to default.
	 * 
	 * @param world
	 * */
	public static void resetAllGamerule(World world) {
		
		String gamerulesNames[] = world.getGameRules();
		
		for(String name : gamerulesNames) {			
			
			String type = GameRule.getByName(name).getType().toString();
			
			if(type.equals("class java.lang.Boolean")) {
				
				GameRule<Boolean> gamerule = (GameRule<Boolean>) GameRule.getByName(name);
				Boolean defaultValue = (Boolean) world.getGameRuleDefault(gamerule);
				
				world.setGameRule(gamerule, defaultValue);
				
			} else if (type.equals("class java.lang.Integer")) {
				
				GameRule<Integer> gamerule = (GameRule<Integer>) GameRule.getByName(name);
				int defaultValue = (int) world.getGameRuleDefault(gamerule);
				
				world.setGameRule(gamerule, defaultValue);
				
			}
			
		}
		
	}
	
	
}
