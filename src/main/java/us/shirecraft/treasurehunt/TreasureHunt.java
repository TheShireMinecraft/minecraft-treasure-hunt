package us.shirecraft.treasurehunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import us.shirecraft.treasurehunt.christmas.*;
import us.shirecraft.treasurehunt.easter.*;
import us.shirecraft.treasurehunt.halloween.*;
import us.shirecraft.treasurehunt.thanksgiving.*;

import java.io.IOException;
import java.util.*;

public class TreasureHunt extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        _config = getConfig();

        // Create empty lists
        _hunts = new ArrayList<>();
        _tasks = new ArrayList<>();

        // Check if hunts are globally disabled
        if(!_config.getBoolean("treasureHuntEnabled")) {
            getLogger().info("Treasure hunts globally disabled in config.yml. Plugin will disable itself.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            // Initialise treasure hunts
            new TreasureListener(this);
            new SnowTrailListener(this);
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
        _worldGuard = WorldGuard.getInstance();

        // Get region container
        _regionContainer = getWg().getPlatform().getRegionContainer();

        // Get region(s) of egg hunt(s)
        ConfigurationSection hunts = _config.getConfigurationSection("hunts");

        // Get names of worlds in config file
        Set<String> worlds;
        try {
            assert hunts != null;
            worlds = hunts.getKeys(false);
        }
        catch(NullPointerException ex) {
            worlds = new HashSet<>();
        }

        if(!worlds.isEmpty()) {
            for (String worldName : worlds) {
                // For each world, get the region(s) under it
                LinkedHashSet<String> regions =
                    (LinkedHashSet<String>) Objects.requireNonNull(
                        _config.getConfigurationSection("hunts." + worldName)
                    )
                    .getKeys(false);

                // Find object for this world
                World world = getServer().getWorld(worldName);

                if (null != world) {
                    // Get WG region manager for this world
                    RegionManager regionManager = getRegionContainer().get(BukkitAdapter.adapt(world));

                    if(!regions.isEmpty()) {
                        // Get regions
                        LinkedHashSet<String> regions_tmp = new LinkedHashSet<>(regions);
                        for (String regionName : regions_tmp) {
                            // Check if region is enabled in config file
                            boolean regionEnabled = _config.getBoolean("hunts." + worldName + "." + regionName + ".enabled");

                            // If enabled and region is defined in WorldGuard, then create hunt object and store hunt reference in ArrayList
                            if (regionEnabled) {
                                assert regionManager != null;
                                if (regionManager.hasRegion(regionName)) {
                                    // Check type of hunt
                                    String huntType = ""; //_config.getString("hunts." + worldName + "." + regionName + ".type");
                                    huntType = validateHuntType(huntType);

                                    ProtectedRegion region = regionManager.getRegion(regionName);
                                    Hunt hunt = new Hunt(this, world, regionManager, region, huntType);
                                    getHunts().add(hunt);
                                }
                            }
                        }
                    }
                }
            }
        }
        if(!getHunts().isEmpty()) {
            registerTreasureItems();
            startTreasureHunts();
        } else {
            getLogger().info("No treasure hunts are enabled in the plugin's config.yml file, or it contains spelling mistakes.");
        }
    }

    /**
     * This is done badly
     */
    private void registerTreasureItems() {
        getLogger().info("The default hunt type is " + getDefaultHuntType());

        _treasure     = new HashMap<>();
        _balancedData = new HashMap<>();
        _data         = new HashMap<>();

        if(getDefaultHuntType().equals("easter")) {
            _treasure.put(PolkaDotEgg.class, 50);
            _treasure.put(RainbowEgg.class, 20);
            _treasure.put(SunflowerEgg.class, 15);
            _treasure.put(VioletEgg.class, 30);
            _treasure.put(RegularEgg.class, 70);
            _treasure.put(DragonEgg.class, 5);
            _treasure.put(BadEgg.class, 12);
        } else if(getDefaultHuntType().equals("halloween")) {
            _treasure.put(BloodSpider.class, 20);
            _treasure.put(CarvedPumpkin.class, 30);
            _treasure.put(Cupcake.class, 30);
            _treasure.put(CursedMummy.class, 15);
            _treasure.put(Gumballs.class, 30);
            _treasure.put(SpookyPenguin.class, 2);
            _treasure.put(TrickTreatBasket.class, 60);
        } else if(getDefaultHuntType().equals("thanksgiving")) {
            _treasure.put(Turkey.class, 10);
            _treasure.put(RoastDinner.class, 20);
            _treasure.put(NutRoast.class, 20);
            _treasure.put(HotCocoa.class, 50);
        } else if(getDefaultHuntType().equals("christmas")) {
            _treasure.put(FestivePenguin.class, 2);
            _treasure.put(Gift.class, 90);
            _treasure.put(GingerbreadHouse.class, 20);
            _treasure.put(GingerbreadMan.class, 4);
            _treasure.put(PlateOfCookies.class, 40);
            _treasure.put(ReindeerPlushy.class, 12);
            _treasure.put(Snowman.class, 40);
        }

        float sum = (float) _treasure.values().stream().mapToDouble(i->i).sum();

        if(getDefaultHuntType().equals("easter")) {
            _data.put(PolkaDotEgg.class, 50f / sum);
            _data.put(RainbowEgg.class, 20f / sum);
            _data.put(SunflowerEgg.class, 15f / sum);
            _data.put(BadEgg.class, 12f / sum);
            _data.put(VioletEgg.class, 30f / sum);
            _data.put(RegularEgg.class, 70f / sum);
            _data.put(DragonEgg.class, 5f / sum);
        } else if(getDefaultHuntType().equals("halloween")) {
            _data.put(BloodSpider.class, 20f / sum);
            _data.put(CarvedPumpkin.class, 30f / sum);
            _data.put(Cupcake.class, 30f / sum);
            _data.put(CursedMummy.class, 15f / sum);
            _data.put(Gumballs.class, 30f / sum);
            _data.put(SpookyPenguin.class, 2f / sum);
            _data.put(TrickTreatBasket.class, 60f / sum);
        } else if(getDefaultHuntType().equals("thanksgiving")) {
            _data.put(Turkey.class, 10f / sum);
            _data.put(RoastDinner.class, 20f / sum);
            _data.put(NutRoast.class, 20f / sum);
            _data.put(HotCocoa.class, 50f / sum);
        } else if(getDefaultHuntType().equals("christmas")) {
            _data.put(FestivePenguin.class, 2f / sum);
            _data.put(Gift.class, 90f / sum);
            _data.put(GingerbreadHouse.class, 20f / sum);
            _data.put(GingerbreadMan.class, 4f / sum);
            _data.put(PlateOfCookies.class, 40f / sum);
            _data.put(ReindeerPlushy.class, 12f / sum);
            _data.put(Snowman.class, 40f / sum);
        }

        float balancedSum = 0f;
        for(Class<? extends TreasureItem> eggClass : _treasure.keySet()) {
            balancedSum += _data.get(eggClass);
            _balancedData.put(eggClass, balancedSum);
        }
    }

    private void startTreasureHunts() {
        for (Hunt hunt : getHunts()) {
            Runnable huntTask = new HuntTask(hunt);
            _tasks.add(
                this.getServer()
                    .getScheduler()
                    .runTaskTimerAsynchronously(this, huntTask, TASK_DELAY_TICKS, TASK_INTERVAL_TICKS)
            );
            getLogger().info("Hunt started: " + hunt);
        }
    }

    public WorldGuard getWg() {
        return this._worldGuard;
    }

    public RegionContainer getRegionContainer() {
        return this._regionContainer;
    }

    public ArrayList<Hunt> getHunts() {
        return this._hunts;
    }

    public HashMap<Class<? extends TreasureItem>, Float> getBalancedData() {
        return _balancedData;
    }

    private void stopHunts() {
        if(!_tasks.isEmpty()) {
            for(BukkitTask task : _tasks) {
                task.cancel();
            }
        }
    }

    public String getDefaultHuntType() {
        return _config.getString("defaultHuntType");
    }

    public String validateHuntType(String huntType) {
        if(null == huntType || huntType.equals("")) {
            huntType = getDefaultHuntType();
        }
        if(Arrays.stream(VALID_HUNT_TYPES).noneMatch(huntType::equals)) {
            huntType = VALID_HUNT_TYPES[0];
        }
        return huntType;
    }

    public void sendToWebServer(Player player, final String eggType, final String regionName, final String worldName) {
        var apiKey = getConfig().getString("apiKey");
        if(apiKey != null && !apiKey.equals("")) {
            final String playerUuid = player.getUniqueId().toString();
            final String playerName = player.getName();
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
                String payload
                        = "data={"
                        + "\"uuid\":\"" + playerUuid + "\","
                        + "\"name\":\""+ playerName +"\","
                        + "\"egg\":\"" + eggType + "\","
                        + "\"region\":\"" + regionName + "\""
                        + "\"world\":\"" + worldName + "\""
                        + "}";

                String endpoint = getConfig().getString("apiEndpoint");
                String key = getConfig().getString("apiKey");

                StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_FORM_URLENCODED);

                try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
                    HttpPost request = new HttpPost(endpoint + key);
                    request.setEntity(entity);
                    httpClient.execute(request);
                } catch (ClientProtocolException e) {
                    getLogger().warning(" ! Encountered ClientProtocolException when attempting to transmit data");
                } catch (IOException e) {
                    getLogger().warning(" ! Encountered IOException when attempting to transmit data");
                }
            });
        }
    }

    private FileConfiguration _config;

    private WorldGuard _worldGuard;

    private RegionContainer _regionContainer;

    private ArrayList<Hunt> _hunts;

    private ArrayList<BukkitTask> _tasks;

    @SuppressWarnings("FieldCanBeLocal")
    private HashMap<Class<? extends TreasureItem>, Integer> _treasure;

    @SuppressWarnings("FieldCanBeLocal")
    private HashMap<Class<? extends TreasureItem>, Float> _data;

    private HashMap<Class<? extends TreasureItem>, Float> _balancedData;

    private final int  TICKS_PER_SECOND = 20; // in an ideal situation

    @SuppressWarnings("FieldCanBeLocal")
    private final long TASK_DELAY_TICKS = TICKS_PER_SECOND * 3;

    @SuppressWarnings("FieldCanBeLocal")
    private final long TASK_INTERVAL_TICKS = TICKS_PER_SECOND * 15;

    private final String[] VALID_HUNT_TYPES = new String[] {"easter", "halloween", "thanksgiving", "christmas"};
}
