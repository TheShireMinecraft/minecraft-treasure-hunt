package us.shirecraft.treasurehunt.helpers;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Objects;

public class AntiCheatHelper {
    public static boolean locationIsWithinRegion(Location treasureLocation, String regionName)
    {
        World world = treasureLocation.getWorld();

        var adaptedTreasureWorld = BukkitAdapter.adapt(world);
        var adaptedTreasureLocation = BukkitAdapter.adapt(treasureLocation).toVector().toBlockPoint();
        var wgRegionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        var regionsTreasureWasFoundIn = Objects.requireNonNull(wgRegionContainer.get(adaptedTreasureWorld))
                .getApplicableRegions(adaptedTreasureLocation)
                .getRegions()
                .stream()
                .map(ProtectedRegion::getId)
                .toList();

        return regionsTreasureWasFoundIn.contains(regionName);
    }
}
