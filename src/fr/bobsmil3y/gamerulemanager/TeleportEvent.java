package fr.bobsmil3y.gamerulemanager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportEvent implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) return false;
		
		Player player = (Player)sender;
		
		//if()
		System.out.println(player.getLocation());
		
		
		return true;
	}

}
