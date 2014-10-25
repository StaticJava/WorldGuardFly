package me.staticjava;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Jains on 4/21/2014.
 */
public class FlyEnable implements CommandExecutor {

    private WorldGuardFly plugin;

    public FlyEnable(WorldGuardFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "Only Players can use this command!");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("wgfly.enable")) {
            if (args.length == 1) {
                String id = args[0];
                RegionManager regionManager = plugin.worldGuardPlugin.getRegionManager(player.getWorld());
                ProtectedRegion protectedRegion = regionManager.getRegion(id);
                String world = player.getWorld().getName();
                if (protectedRegion != null) {
                    if (plugin.getConfig().getStringList("disabledRegions").contains(id + ";" + world)) {
                        List<String> list = plugin.getConfig().getStringList("disabledRegions");
                        list.remove(id + ";" + world);
                        plugin.getConfig().set("disabledRegions", list);
                        plugin.saveConfig();

                        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Successfully enabled fly in the region \"" + id + "\"!");
                        return true;
                    } else {
                        player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "Fly in this region was already enabled!");
                        return true;
                    }
                } else {
                    player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "The region \"" + id + "\" does not exist in this world!");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "Invalid usage! Type /flyenable <regionName>.");
                return true;
            }
        } else {
            player.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }
    }
}
