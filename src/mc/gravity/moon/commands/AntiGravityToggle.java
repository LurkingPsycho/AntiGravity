package mc.gravity.moon.commands;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mc.gravity.moon.AntiGravity;
import mc.gravity.moon.config.DataManager;
import mc.gravity.moon.utils.ChatUtilities;

public class AntiGravityToggle implements CommandExecutor {
	
	public DataManager data;
	
	public static ArrayList<String> toggle = new ArrayList<String>();
	
	public AntiGravityToggle(DataManager data) {
		this.data = data;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			data.reloadConfig();
			sender.sendMessage(ChatUtilities.msg("&aAntiGravity has been reloaded!"));
		}
		
		if(sender instanceof Player) {
			if(cmd.getName().equals("ag")) {
				Player p = (Player) sender;
				if(args.length == 0) {
					p.sendMessage(ChatUtilities.msg("&a/ag toggle"));
					return false;
				}
				if(args[0].equalsIgnoreCase("toggle")) {
					if(!p.hasPermission("antigravity.use")) {
						p.sendMessage(ChatUtilities.msg("&cSorry, you do not have permission"));
					} else {
						try {
							if(!toggle.contains(p.getName())) {
								toggle.add(p.getName());
								p.sendMessage(ChatUtilities.msg("&bAntiGravity &aEnabled!"));
							    p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, data.getConfig().getInt("JumpTime"), data.getConfig().getInt("JumpHeight"), false, false));
								p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, data.getConfig().getInt("SlowFallingTime"), data.getConfig().getInt("SlowFalling"), false, false));
							} else {
								toggle.remove(p.getName());
								p.sendMessage(ChatUtilities.msg("&bAntiGravity &cDisabled!"));
								p.removePotionEffect(PotionEffectType.JUMP);
								p.removePotionEffect(PotionEffectType.SLOW_FALLING);
							}
						}catch(Exception er) {
							er.getSuppressed();
						}
						
					}
				}
				if(args[0].equalsIgnoreCase("reload")) {
					if(!p.hasPermission("antigravity.reload")) {
						p.sendMessage(ChatUtilities.msg("&cSorry, you do not have permission"));
					} else {
						data.reloadConfig();
						p.sendMessage(ChatUtilities.msg("&aAntiGravity has been reloaded! will take affect when 'ReEnabling AntiGravity toggle'!"));
						return false;
					}
				
				}
				
			}
		}
		
		return false;
	}

}
