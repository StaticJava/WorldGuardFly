package me.staticjava;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Jains on 4/22/2014.
 */
public class WorldGuardFly extends JavaPlugin {
    WorldGuardPlugin worldGuardPlugin;

    @Override
    public void onEnable() {
        getLogger().info("WorldGuardFly has been enabled!");

        saveDefaultConfig();

        getCommand("flydisable").setExecutor(new FlyDisable(this));
        getCommand("flyenable").setExecutor(new FlyEnable(this));
        getCommand("flyreload").setExecutor(new FlyReload(this));

        getServer().getPluginManager().registerEvents(new FlyListener(this), this);

        worldGuardPlugin = getWorldGuard();
    }

    @Override
    public void onDisable() {
        getLogger().info("WorldGuardFly has been disabled!");

        saveConfig();
    }

    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }
}
