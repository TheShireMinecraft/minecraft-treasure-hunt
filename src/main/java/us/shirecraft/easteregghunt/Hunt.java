package us.shirecraft.easteregghunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
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
        TreasureItem treasure = (TreasureItem) chooseEgg().getDeclaredConstructor().newInstance();
        treasure.setRegionName(region.getId());
        treasure.setHuntType(huntType);
        BlockVector3 randomPoint = randomPoint(region);

        if(null != randomPoint) {
            Location dropLocation = BukkitAdapter.adapt(world, randomPoint);
            int chunkX = dropLocation.getBlockX() >> 4;
            int chunkZ = dropLocation.getBlockZ() >> 4;

            if(world.isChunkLoaded(chunkX, chunkZ)) {
                ItemStack eggItem = treasure.getItem();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Collection<Entity> entities = world.getNearbyEntities(dropLocation, 16, 100,16, (e) -> e.getType() == EntityType.DROPPED_ITEM);
                        if(entities.size() <= 8) {
                            world.dropItemNaturally(dropLocation, eggItem);
                        }
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
    private final int MAX_RANDOM_POINT_ATTEMPTS = 25;
    private String huntType;
}
