package us.shirecraft.easteregghunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.IOException;
import java.util.*;


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
            new EggListener(this);
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
                                // Check type of hunt
                                String huntType = config.getString("hunts." + worldName + "." + regionName + ".type");
                                if(null == huntType || huntType.equals("")) {
                                    String defaultHuntType = config.getString("defaultHuntType");
                                    huntType = defaultHuntType;
                                }
                                if(!Arrays.stream(VALID_HUNT_TYPES).anyMatch(huntType::equals)) {
                                    huntType = VALID_HUNT_TYPES[0];
                                }
                                ProtectedRegion region = regionManager.getRegion(regionName);
                                Hunt hunt = new Hunt(this, world, regionManager, region, huntType);
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

    /**
     * This is done badly
     */
    private void registerEggs() {
        eggs = new HashMap<>();
        eggs.put(PolkaDotEgg.class, 50);
        eggs.put(RainbowEgg.class, 20);
        eggs.put(SunflowerEgg.class, 15);
        eggs.put(VioletEgg.class, 30);
        eggs.put(RegularEgg.class, 70);
        eggs.put(DragonEgg.class, 5);
        eggs.put(BadEgg.class, 12);

        float sum = (float) eggs.values().stream().mapToDouble(i->i).sum();
        data         = new HashMap<>();
        balancedData = new HashMap<>();

        data.put(PolkaDotEgg.class,  50f/sum);
        data.put(RainbowEgg.class,   20f/sum);
        data.put(SunflowerEgg.class, 15f/sum);
        data.put(BadEgg.class,       12f/sum);
        data.put(VioletEgg.class,    30f/sum);
        data.put(RegularEgg.class,   70f/sum);
        data.put(DragonEgg.class,     5f/sum);

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

    public boolean sendToWebServer(Player player, final String eggType, final String regionName) {
        if(!getConfig().getString("apiKey").equals("")) {
            final String playerUuid = player.getUniqueId().toString();
            final String playerName = player.getName();
            Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
                @Override
                public void run() {
                    String payload
                            = "data={"
                            + "\"uuid\":\"" + playerUuid + "\","
                            + "\"name\":\""+ playerName +"\","
                            + "\"egg\":\"" + eggType + "\","
                            + "\"region\":\"" + regionName + "\""
                            + "}";

                    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_FORM_URLENCODED);

                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost request = new HttpPost(getConfig().getString("apiEndpoint") + getConfig().getString("apiKey"));
                    request.setEntity(entity);

                    try {
                        httpClient.execute(request);
                    } catch (ClientProtocolException e) {
                        getLogger().warning(" ! Encountered a ClientProtocolException when attempting to transmit data");
                    } catch (IOException e) {
                        getLogger().warning(" ! Encountered an IOException when attempting to transmit data");
                        e.printStackTrace();
                    }
                }
            });
        }
        return false;
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
    private final long TASK_INTERVAL_TICKS = (long) (TICKS_PER_SECOND * 9);
    private final String[] VALID_HUNT_TYPES = new String[] {"easter", "halloween", "christmas"};
}
