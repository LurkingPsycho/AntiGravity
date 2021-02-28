package mc.gravity.moon.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import mc.gravity.moon.AntiGravity;
import mc.gravity.moon.utils.ChatUtilities;

public class agReload implements CommandExecutor {
	
	public AntiGravity ag;
	
	public agReload(AntiGravity ag) {
		this.ag = ag;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			ag.reload();
			sender.sendMessage(ChatUtilities.msg("&aAntiGravity has been reloaded!"));
		}
		
		if(sender instanceof Player) {
			if(cmd.getName().equalsIgnoreCase("ag")) {
				if(args.length == 0) {
					return false;
				}
				
				if(args[0].equalsIgnoreCase("reload")) {
					Player p = (Player) sender;
					if(!p.hasPermission("antigravity.reload")) {
						p.sendMessage(ChatUtilities.msg("&cSorry, you do not have permission"));
					} else {
						ag.reload();
						p.sendMessage(ChatUtilities.msg("&aAntiGravity has been reloaded!"));
						return false;
					}
				
				}
			}
				
			
		}
		
		
		return false;
	}
	
	
}
