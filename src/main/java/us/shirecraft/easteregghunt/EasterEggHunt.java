package us.shirecraft.easteregghunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Set;

public class EasterEggHunt extends JavaPlugin {
    @Override
    public void onEnable() {
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        // Egg hunt enabled?
        if(!config.getBoolean("eggHuntEnabled")) {
            getLogger().info("Egg hunts globally disabled in config.yml. Plugin will disable itself.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            // Create a list of hunts
            hunts = new ArrayList<Hunt>();

            // Initialise egg hunts
            initialiseHunts();
        }
    }

    @Override
    public void onDisable() {

    }

    private void initialiseHunts() {
        // Get WG instance
        worldGuard = WorldGuard.getInstance();

        // Get region container
        regionContainer = getWg().getPlatform().getRegionContainer();

        // Get region(s) of egg hunt(s)
        ConfigurationSection hunts = config.getConfigurationSection("hunts");
        // Get names of worlds in config file
        Set<String> worlds = hunts.getKeys(false);
        for (String worldName : worlds) {
            // For each world, get the region(s) under it
            Set<String> regions = config.getConfigurationSection("hunts." + worldName).getKeys(false);

            // Find object for this world
            World world = getServer().getWorld(worldName);

            if(null != world) {
                // Get region manager for this world
                RegionManager regionManager = getRegionContainer().get(BukkitAdapter.adapt(world));

                // Get regions
                for (String regionName : regions) {
                    if(regionManager.hasRegion(regionName)) {
                        ProtectedRegion region = regionManager.getRegion(regionName);
                        Hunt hunt = new Hunt(world, region);
                        getLogger().info("Created a new hunt for region " + regionName + " in world " + worldName);
                        getHunts().add(hunt);
                    } else {
                        regions.remove(regionName);
                    }
                }

                // Show message in console
                if(!regions.isEmpty()) {
                    getLogger().info("Loaded " + regions.size() + " egg hunts in world \"" + world.getName() + "\"");
                }
            }
        }
    }

    public WorldGuard getWg() {
        return this.worldGuard;
    }

    public RegionContainer getRegionContainer() {
        return this.regionContainer;
    }

    public ArrayList<Hunt> getHunts() {
        return this.hunts;
    }

    private FileConfiguration config;
    private WorldGuard worldGuard;
    private RegionContainer regionContainer;
    private ArrayList<Hunt> hunts;
}
