package me.staticjava;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

/**
 * Created by Jains on 4/22/2014.
 */
public class FlyListener implements Listener {

    private WorldGuardFly plugin;

    public FlyListener(WorldGuardFly plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("wgfly.bypass")) {
            Location loc = event.getTo();

            ApplicableRegionSet set = WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(loc);

            for (ProtectedRegion region : set) {
                String id = region.getId();
                String worldName = player.getWorld().getName();
                if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + worldName)) {
                    if (player.isFlying()) {
                        player.setFlying(false);
                        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.GREEN + "You cannot fly in this region!");
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerToggleFly(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("wgfly.bypass")) {
            Location loc = player.getLocation();

            ApplicableRegionSet set = WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(loc);

            for (ProtectedRegion region : set) {
                String id = region.getId();
                String worldName = player.getWorld().getName();
                if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + worldName)) {
                    if (player.isFlying()) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}
