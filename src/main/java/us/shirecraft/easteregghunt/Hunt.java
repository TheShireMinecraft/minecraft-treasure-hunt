package us.shirecraft.easteregghunt;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Hunt {
    public Hunt(EasterEggHunt plugin, World world, RegionManager regionManager, ProtectedRegion region) {
        this.plugin = plugin;
        this.world = world;
        this.region = region;
        this.enabled = true;
        this.eggs = new ArrayList<Egg>();
        this.random = new Random();
        this.regionManager = regionManager;
    }

    public boolean isEnabled() { return enabled; }

    public ArrayList<Egg> getEggs() {
        return this.eggs;
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
        plugin.getLogger().info("Ticking hunt: " + this);
        if(getEggs().size() <= MAX_EGGS_IN_HUNT) {
            randomPointAttempts = 0;
            // Spawn an egg in this hunt
            Egg egg = (Egg) chooseEgg().getDeclaredConstructor().newInstance();
            getEggs().add(egg);
            plugin.getLogger().info("An egg has been chosen! " + egg);

            BlockVector3 randomPoint = randomPoint(region);
            if(null != randomPoint) {
                plugin.getLogger().info("Spawning egg at: " + randomPoint);
            }
        } else {
            plugin.getLogger().info("Too many eggs!");
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
        System.out.println("Maxima: (" + xMax + ", " + zMax + ")");
        System.out.println("Minima: (" + xMin + ", " + zMin + ")");
        int randX = getRandom(xMin, xMax);
        int randZ = getRandom(zMin, zMax);
        System.out.println("Point created: (" + randX + ", " + (world.getMaxHeight()-1) + ", " + randZ + ")");
        BlockVector3 randPoint = BlockVector3.at(randX, (world.getMaxHeight()-1), randZ);
        boolean isInRegion = regionManager.getApplicableRegionsIDs(randPoint).contains(mRegion.getId());
        if(isInRegion) {
            System.out.println("Point is in region!");
            return randPoint;
        }
        System.out.println("Point is NOT in region!");
        randomPointAttempts++;
        System.out.println("Attempts so far: " + randomPointAttempts);
        if(randomPointAttempts == MAX_RANDOM_POINT_ATTEMPTS) {
            System.out.println("Max attempts reached");
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
    private ArrayList<Egg> eggs;
    private EasterEggHunt plugin;
    private Random random;
    private int randomPointAttempts = 0;
    private final int MAX_EGGS_IN_HUNT = 10;
    private final int MAX_RANDOM_POINT_ATTEMPTS = 25;
}
