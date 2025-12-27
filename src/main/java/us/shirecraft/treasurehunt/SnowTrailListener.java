package us.shirecraft.treasurehunt;

import org.bukkit.Material;
import org.bukkit.entity.Snowman;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.persistence.PersistentDataType;

public class SnowTrailListener implements Listener {
    private final TreasureHunt plugin;

    public SnowTrailListener(TreasureHunt plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSnowTrailForm(EntityBlockFormEvent event) {
        // Remove snow trail made by a snowman created by this plugin
        if(event.getEntity() instanceof Snowman snowman) {
            if(event.getNewState().getType() == Material.SNOW) {
                boolean isPluginSnowman = snowman.getPersistentDataContainer()
                    .has(plugin.getSnowmanKey(), PersistentDataType.BOOLEAN);
                if(isPluginSnowman) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
