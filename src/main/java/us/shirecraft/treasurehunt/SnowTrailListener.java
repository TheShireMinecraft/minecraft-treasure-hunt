package us.shirecraft.treasurehunt;

import org.bukkit.Material;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;

public class SnowTrailListener implements Listener {
    public SnowTrailListener(TreasureHunt plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSnowTrailForm(EntityBlockFormEvent event) {
        // Remove snow trail made by a snowman created by this plugin
        if(event.getEntity() instanceof Snowman snowman) {
            if(event.getNewState().getType() == Material.SNOW) {
                boolean isPluginSnowman = snowman.hasMetadata("egghunt.random-snowman");
                if(isPluginSnowman) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
