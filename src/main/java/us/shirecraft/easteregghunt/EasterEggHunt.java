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
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;


public class EasterEggHunt extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        // Create empty lists
        hunts = new ArrayList<>();
        tasks = new ArrayList<>();

        // Egg hunt enabled?
        if(!config.getBoolean("eggHuntEnabled")) {
            getLogger().info("Egg hunts globally disabled in config.yml. Plugin will disable itself.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            // Initialise egg hunts
            initialiseHunts();
        }
    }

    @Override
    public void onDisable() {
        stopHunts();
        saveConfig();
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

        if(!worlds.isEmpty()) {
            for (String worldName : worlds) {
                // For each world, get the region(s) under it
                LinkedHashSet<String> regions = (LinkedHashSet) config.getConfigurationSection("hunts." + worldName).getKeys(false);

                // Find object for this world
                World world = getServer().getWorld(worldName);

                if (null != world) {
                    // Get WG region manager for this world
                    RegionManager regionManager = getRegionContainer().get(BukkitAdapter.adapt(world));

                    if(null!=regions && !regions.isEmpty()) {
                        // Get regions
                        LinkedHashSet<String> regions_tmp = (LinkedHashSet) regions.clone();
                        for (String regionName : regions_tmp) {
                            // Check if region is enabled in config file
                            boolean regionEnabled = config.getBoolean("hunts." + worldName + "." + regionName + ".enabled");
                            // If enabled and region is defined in WorldGuard, then create hunt object and store hunt reference in ArrayList
                            if (regionEnabled && regionManager.hasRegion(regionName)) {
                                ProtectedRegion region = regionManager.getRegion(regionName);
                                Hunt hunt = new Hunt(this, world, regionManager, region);
                                getHunts().add(hunt);
                            }
                        }
                    }
                }
            }
        }
        if(!getHunts().isEmpty()) {
            registerEggs();
            startEggHunts();
        } else {
            getLogger().info("No egg hunts are enabled in the plugin's config.yml file, or it contains spelling mistakes.");
        }
    }

    private void registerEggs() {
        eggs = new HashMap<>();
        eggs.put(PolkaDotEgg.class, 50);
        eggs.put(RainbowEgg.class, 20);
        eggs.put(SunflowerEgg.class, 15);
        eggs.put(VioletEgg.class, 30);
        float sum = (float) eggs.values().stream().mapToDouble(i->i).sum();
        data         = new HashMap<>();
        balancedData = new HashMap<>();
        data.put(PolkaDotEgg.class,  50f/sum);
        data.put(RainbowEgg.class,   20f/sum);
        data.put(SunflowerEgg.class, 15f/sum);
        data.put(VioletEgg.class,    30f/sum);
        float balancedSum = 0f;
        for(Class eggClass : eggs.keySet()) {
            balancedSum += data.get(eggClass);
            balancedData.put(eggClass, balancedSum);
        }
    }

    private void startEggHunts() {
        for (Hunt hunt : getHunts()) {
            Runnable huntTask = new HuntTask(this, hunt);
            tasks.add(this.getServer().getScheduler()
                    .runTaskTimerAsynchronously(this, huntTask, TASK_DELAY_TICKS, TASK_INTERVAL_TICKS));
            getLogger().info("Hunt started: " + hunt);
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

    public HashMap<Class<?>, Integer> getEggs() {
        return eggs;
    }

    public HashMap<Class<?>, Float> getBalancedData() {
        return balancedData;
    }

    private void stopHunts() {
        if(!tasks.isEmpty()) {
            for(BukkitTask task : tasks) {
                task.cancel();
            }
        }
    }

    private FileConfiguration config;
    private WorldGuard worldGuard;
    private RegionContainer regionContainer;
    private ArrayList<Hunt> hunts;
    private ArrayList<BukkitTask> tasks;
    private HashMap<Class<?>, Integer> eggs;
    private HashMap<Class<?>, Float> data;
    private HashMap<Class<?>, Float> balancedData;
    private final int  TICKS_PER_SECOND = 20; // in an ideal situation
    private final long TASK_DELAY_TICKS = (long) (TICKS_PER_SECOND * 3);
    private final long TASK_INTERVAL_TICKS = (long) (TICKS_PER_SECOND * 10);
}
