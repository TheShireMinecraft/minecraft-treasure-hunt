package us.shirecraft.treasurehunt;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Hunt {
    public Hunt(TreasureHunt plugin, World world, RegionManager regionManager, ProtectedRegion region, String huntType) {
        this.plugin = plugin;
        this.world = world;
        this.region = region;
        this.enabled = true;
        this.random = new Random();
        this.regionManager = regionManager;
        this.huntType = huntType;
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
        TreasureItem treasure = chooseEgg().getDeclaredConstructor().newInstance();
        treasure.setRegionName(region.getId());
        treasure.setHuntType(huntType);
        BlockVector3 randomPoint = randomPoint(region);

        if(null != randomPoint) {
            Location dropLocation = BukkitAdapter.adapt(world, randomPoint);
            var maxPlayerDistance = 360d;
            // Only drop treasure if a player is nearby
            BoundingBox boundingBox = new BoundingBox(
                dropLocation.getX() - maxPlayerDistance,
                48d, // min height
                dropLocation.getZ() - maxPlayerDistance,
                dropLocation.getX() + maxPlayerDistance,
                288d, // max height
                dropLocation.getZ() + maxPlayerDistance
            );

            boolean anyPlayerIsNearDropLocation;
            try {
                anyPlayerIsNearDropLocation = Bukkit.getServer().getScheduler().callSyncMethod(
                    plugin,
                    () -> world
                        .getNearbyEntities(boundingBox)
                        .stream()
                        .anyMatch(x -> x instanceof Player player && !player.hasMetadata("NPC"))
                ).get();
            } catch (InterruptedException | ExecutionException e) {
                anyPlayerIsNearDropLocation = true;
            }

            if(anyPlayerIsNearDropLocation) {
                ItemStack eggItem = treasure.getItem();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Collection<Entity> entities = world.getNearbyEntities(
                            dropLocation,
                            64,
                            world.getMaxHeight(),
                            64,
                            (e) -> e.getType() == EntityType.DROPPED_ITEM
                        );
                        if(entities.size() <= 8) {
                            var droppedItem = world.dropItem(dropLocation.add(.5, .5, .5), eggItem);
                            droppedItem.setVelocity(droppedItem.getVelocity().zero());
                        }
                    }
                }.runTask(plugin);
            }
        }
    }

    public Class<? extends TreasureItem> chooseEgg() {
        float rand = new Random().nextFloat();
        for(Class<? extends TreasureItem> eggClass : plugin.getBalancedData().keySet()) {
            if(rand <= plugin.getBalancedData().get(eggClass)) {
                return eggClass;
            }
        }
        return null;
    }

    private BlockVector3 randomPoint(ProtectedRegion mRegion) {
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE,
            zMin = Integer.MAX_VALUE, zMax = Integer.MIN_VALUE;
        if(mRegion instanceof ProtectedPolygonalRegion polyRegion) {
            if(polyRegion.getPoints().isEmpty()) {
                return null;
            }
            for(var point : polyRegion.getPoints()) {
                int x = point.getBlockX();
                int z = point.getBlockZ();
                if(x < xMin) { xMin = x; }
                if(x > xMax) { xMax = x; }
                if(z < zMin) { zMin = z; }
                if(z > zMax) { zMax = z; }
            }
        }
        else {
            xMin = mRegion.getMinimumPoint().getBlockX();
            xMax = mRegion.getMaximumPoint().getBlockX();
            zMin = mRegion.getMinimumPoint().getBlockZ();
            zMax = mRegion.getMaximumPoint().getBlockZ();
        }

        // Swap values if required
        if (xMin > xMax) {
            xMin = xMin ^ xMax ^ (xMax = xMin);
        }
        if (zMin > zMax) {
            zMin = zMin ^ zMax ^ (zMax = zMin);
        }

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
        if (min == max) {
            return min;
        }
        return random.nextInt(max - min) + min;
    }

    private final World world;
    private final ProtectedRegion region;
    private final RegionManager regionManager;
    private final boolean enabled;
    private final TreasureHunt plugin;
    private final Random random;
    private int randomPointAttempts = 0;
    private final int MAX_RANDOM_POINT_ATTEMPTS = 25;
    private final String huntType;
}
