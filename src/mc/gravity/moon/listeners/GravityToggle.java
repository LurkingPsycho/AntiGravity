package mc.gravity.moon.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mc.gravity.moon.AntiGravity;
import mc.gravity.moon.config.DataManager;
import mc.gravity.moon.utils.ChatUtilities;

public class GravityToggle implements Listener {
	
	public DataManager data;

	public static ArrayList<String> toggle = new ArrayList<String>();
	
	public GravityToggle(DataManager data) {
		this.data = data;
	}
	
	public void giveItem(Player p) {
		
		int slot = data.getConfig().getInt("item-slot");
		
		ItemStack item = new ItemStack(Material.matchMaterial(data.getConfig().getString("antigravity-item")), 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatUtilities.msg("&a&lAntiGravity Toggle"));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		p.getInventory().setItem(slot, item);
		
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
	}
	
	@EventHandler
	public void onSpawnItem(PlayerJoinEvent e) {
		if(!e.getPlayer().hasPermission("antigravity.use")) {
			
		} else {
			Player p = e.getPlayer();
			giveItem(p);
		}
	}
	
	
	@EventHandler
	public void onGravToggle(PlayerInteractEvent e) {

		try {
			if(e.getItem().getType() == Material.matchMaterial(data.getConfig().getString("antigravity-item"))) {
				
				if(!toggle.contains(e.getPlayer().getName())) {
					toggle.add(e.getPlayer().getName());
					e.getPlayer().sendMessage(ChatUtilities.msg("&bAntiGravity &aEnabled!"));
				    e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, data.getConfig().getInt("JumpTime"), data.getConfig().getInt("JumpHeight"), false, false));
					e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, data.getConfig().getInt("SlowFallingTime"), data.getConfig().getInt("SlowFalling"), false, false));
				} else {
					toggle.remove(e.getPlayer().getName());
					e.getPlayer().sendMessage(ChatUtilities.msg("&bAntiGravity &cDisabled!"));
					e.getPlayer().removePotionEffect(PotionEffectType.JUMP);
					e.getPlayer().removePotionEffect(PotionEffectType.SLOW_FALLING);
				}
			}
		}catch(Exception er) {
			er.getSuppressed();
		}
		
	}

}
