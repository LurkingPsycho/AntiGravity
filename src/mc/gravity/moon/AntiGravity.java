package mc.gravity.moon;

import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mc.gravity.moon.commands.AntiGravityToggle;
import mc.gravity.moon.commands.agReload;
import mc.gravity.moon.listeners.GravityToggle;
import mc.gravity.moon.listeners.WorldChange;
import mc.gravity.moon.utils.ChatUtilities;

public class AntiGravity extends JavaPlugin implements CommandExecutor {
		
	public void onEnable() {
		Bukkit.broadcastMessage(ChatUtilities.msg("&aAntiGravity &64.5 &ahas now been enabled!"));
		onConfig(getConfig());
		onCommand();
		onLoad(Bukkit.getServer().getPluginManager());
		reloadConfig();
	}
	
	
	public void onConfig(FileConfiguration config) {
		saveResource("config.yml", false);
		config = YamlConfiguration.loadConfiguration(new InputStreamReader(getResource("config.yml")));
	}
	
	public void onCommand() {
		getCommand("ag").setExecutor(new AntiGravityToggle(this));
		
	}
	
	public void onLoad(PluginManager pm) {
		pm.registerEvents(new GravityToggle(this), this);
		pm.registerEvents(new WorldChange(this), this);
	}
	
	public void reload() {
		reloadConfig();
		
	}

}
