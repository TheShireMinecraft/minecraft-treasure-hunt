package us.shirecraft.easteregghunt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class EggListener implements Listener {
    public EggListener(EasterEggHunt plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPickUpEgg(EntityPickupItemEvent ev) {
        ItemStack is = ev.getItem().getItemStack();
        if(isEasterEgg(is)) {
            if(ev.getEntity() instanceof Player) {
                Player player = (Player) ev.getEntity();
                NBTItem nbtItem = new NBTItem(is);
                player.sendMessage("§6 ** You found a " + nbtItem.getString("EasterEgg") + " Egg");
            }
            ev.setCancelled(true);
        }
    }

    public boolean isEasterEgg(ItemStack is) {
        if(is.getType() == Material.PLAYER_HEAD) {
            NBTItem nbtItem = new NBTItem(is);
            return nbtItem.getKeys().contains("EasterEgg");
        }
        return false;
    }

    private final EasterEggHunt plugin;
}
