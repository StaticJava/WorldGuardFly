package me.staticjava;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Jains on 4/22/2014.
 */
public class FlyReload implements CommandExecutor {

    private WorldGuardFly plugin;

    public FlyReload(WorldGuardFly plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender.hasPermission("wgfly.reload")) {
            plugin.reloadConfig();

            sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.GREEN + "Successfully reloaded the WGFly Config!");
            return true;
        } else {
            sender.sendMessage(ChatColor.GOLD + "[" + ChatColor.AQUA + "WGFly" + ChatColor.GOLD + "] " + ChatColor.RED + "You do not have permission to execute this command!");
            return true;
        }
    }
}