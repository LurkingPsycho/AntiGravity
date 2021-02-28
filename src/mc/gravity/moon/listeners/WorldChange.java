package mc.gravity.moon.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.potion.PotionEffectType;

import mc.gravity.moon.AntiGravity;

public class WorldChange implements Listener {
	
	FileConfiguration worlds;
	
	public WorldChange(AntiGravity ag) {
		this.worlds = ag.getConfig();
		
	}
	
	
	
	@EventHandler
	public void ChangeWorldEvent(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		if (worlds.getStringList("worlds").contains(p.getWorld().getName())) {
			p.removePotionEffect(PotionEffectType.JUMP);
			p.removePotionEffect(PotionEffectType.SLOW_FALLING);
		} 
	   
	}

}
