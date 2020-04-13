package us.shirecraft.easteregghunt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class EggListener implements Listener {
    public EggListener(EasterEggHunt plugin) {
        this.plugin = plugin;
        this.random = new Random();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPickUpEgg(EntityPickupItemEvent ev) {
        ItemStack is = ev.getItem().getItemStack();
        if(isEasterEgg(is)) {
            if(ev.getEntity() instanceof Player) {
                Player player = (Player) ev.getEntity();
                NBTItem nbtItem = new NBTItem(is);
                String eggType = nbtItem.getString("EasterEgg");
                player.sendMessage("§6 ** You found a " + eggType + " Egg");
                if(getRandom(1,100) < 6) {
                    player.sendMessage("§a ** Oh no. The egg hatched before you could collect it!");
                    Location eggLocation = ev.getItem().getLocation();
                    World world = eggLocation.getWorld();
                    world.spawnParticle(Particle.EXPLOSION_LARGE, eggLocation, 2);
                    ev.getItem().remove();
                    Chicken chicken = (Chicken) world.spawnEntity(eggLocation, EntityType.CHICKEN);
                    chicken.setBaby();
                    chicken.setTicksLived(1);
                    world.spawnParticle(Particle.HEART, chicken.getLocation(), 2);
                }
                else {
                    player.playNote(player.getLocation(), Instrument.XYLOPHONE, Note.sharp(1, Note.Tone.F));
                    ev.getItem().getLocation().getWorld().spawnParticle(Particle.SPELL_INSTANT, ev.getItem().getLocation(), 5);
                    ev.getItem().remove();
                    String regionName = nbtItem.getString("EggHuntRegion");
                    plugin.sendToWebServer(player, eggType, regionName);
                }
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

    private int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private final EasterEggHunt plugin;
    private Random random;
}
