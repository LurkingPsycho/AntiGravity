package mc.gravity.moon;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mc.gravity.moon.commands.AntiGravityToggle;
import mc.gravity.moon.config.DataManager;
import mc.gravity.moon.listeners.GravityToggle;
import mc.gravity.moon.utils.ChatUtilities;

public class AntiGravity extends JavaPlugin implements CommandExecutor {
	
	public DataManager data;
	
	public void onEnable() {
		Bukkit.broadcastMessage(ChatUtilities.msg("&aAntiGravity &65.0 &ahas now been enabled!"));
		data = new DataManager(this);
		onCommand();
		onLoad(Bukkit.getServer().getPluginManager());
	}

	public void onCommand() {
		getCommand("ag").setExecutor(new AntiGravityToggle(data));
		
	}
	
	public void onLoad(PluginManager pm) {
		pm.registerEvents(new GravityToggle(data), this);
	
	}
	

}
