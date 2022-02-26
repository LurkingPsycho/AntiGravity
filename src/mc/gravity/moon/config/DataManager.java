package com.panda.craft.files;

import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.panda.craft.Info;

public class DataManager {
	
	private Info plugin;
	private FileConfiguration dataConfig = null;
	private File ConfigFile = null;
	
	public DataManager(Info plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if(ConfigFile == null) ConfigFile = new File(plugin.getDataFolder(), "config.yml");
		dataConfig = YamlConfiguration.loadConfiguration(ConfigFile);
		
		/*InputStream defaultStream = this.plugin.getResource("config.yml");
		if(defaultStream != null) {
			YamlConfiguration defauConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.dataConfig.setDefaults(defauConfig);
		}*/
	}
	
	public FileConfiguration getConfig() {
		if(dataConfig == null) {
			reloadConfig();
		}
		return dataConfig;
	}
	
	public void saveConfig() {
		if(dataConfig == null || ConfigFile == null) return;
		try {
			getConfig().save(ConfigFile);
		} catch(IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + ConfigFile, e);
		}
		
	}
	
	public void saveDefaultConfig() {
		if(ConfigFile == null) ConfigFile = new File(plugin.getDataFolder(), "config.yml");
		if(!this.ConfigFile.exists()) {
			plugin.saveResource("config.yml", false);
		}
	}
}
