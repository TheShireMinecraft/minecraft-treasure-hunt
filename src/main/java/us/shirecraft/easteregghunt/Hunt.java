package us.shirecraft.easteregghunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Hunt {
    public Hunt(EasterEggHunt plugin, World world, RegionManager regionManager, ProtectedRegion region, String huntType) {
        this.plugin = plugin;
        this.world = world;
        this.region = region;
        this.enabled = true;
        this.random = new Random();
        this.regionManager = regionManager;
        this.huntType = huntType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return region.getId() + " (" + world.getName() + ")";
    }

    /*
     * Called asynchronously
     */
    public void doTick() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(!enabled) return;
        randomPointAttempts = 0;
        Egg egg = (Egg) chooseEgg().getDeclaredConstructor().newInstance();
        egg.setRegionName(region.getId());
        BlockVector3 randomPoint = randomPoint(region);

        if(null != randomPoint) {
            Location dropLocation = BukkitAdapter.adapt(world,randomPoint);
            if(dropLocation.getChunk().isLoaded()) {
                ItemStack eggItem = egg.getItem();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.dropItemNaturally(dropLocation, eggItem);
                    }
                }.runTask(plugin);
            }
        }
    }

    public Class chooseEgg() {
        float rand = new Random().nextFloat();
        for(Class eggClass : plugin.getBalancedData().keySet()) {
            if(rand <= plugin.getBalancedData().get(eggClass)) {
                return eggClass;
            }
        }
        return null;
    }

    private BlockVector3 randomPoint(ProtectedRegion mRegion) {
        int xMin = mRegion.getMinimumPoint().getBlockX();
        int xMax = mRegion.getMaximumPoint().getBlockX();
        int zMin = mRegion.getMinimumPoint().getBlockZ();
        int zMax = mRegion.getMaximumPoint().getBlockZ();

        int randX = getRandom(xMin, xMax);
        int randZ = getRandom(zMin, zMax);

        BlockVector3 randPoint = BlockVector3.at(randX, (world.getMaxHeight()-1), randZ);
        boolean isInRegion = regionManager.getApplicableRegionsIDs(randPoint).contains(mRegion.getId());

        if(isInRegion && world.getHighestBlockAt(randX,randZ).getType() != Material.WATER) {
            return randPoint;
        }

        randomPointAttempts++;
        if(randomPointAttempts == MAX_RANDOM_POINT_ATTEMPTS) {
            return null;
        }

        return randomPoint(mRegion);
    }

    private int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private World world;
    private ProtectedRegion region;
    private RegionManager regionManager;
    private boolean enabled;
    private EasterEggHunt plugin;
    private Random random;
    private int randomPointAttempts = 0;
    private final int MAX_RANDOM_POINT_ATTEMPTS = 20;
    private String huntType;
}
