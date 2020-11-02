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
                String huntType = plugin.validateHuntType(nbtItem.getString("EggHuntType"));
                String treasureType = nbtItem.getString("TreasureType");

                player.sendMessage("§6 ** You found a " + treasureType + (huntType.equals("easter")?" Egg":""));
                Location eggLocation = ev.getItem().getLocation();
                World world = eggLocation.getWorld();

                if(getRandom(1,100) < 6 && huntType.equals("easter")) {
                    player.sendMessage("§a ** The egg hatched before you could collect it!");
                    world.spawnParticle(Particle.EXPLOSION_LARGE, eggLocation, 2);
                    ev.getItem().remove();

                    Chicken chicken = (Chicken) world.spawnEntity(eggLocation, EntityType.CHICKEN);
                    chicken.setBaby();
                    chicken.setTicksLived(1);
                    world.spawnParticle(Particle.HEART, chicken.getLocation(), 2);
                    world.playSound(chicken.getLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1, 1);

                    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
                        if(null == chicken || null == world || null == player) {
                            return;
                        }
                        world.spawnParticle(Particle.FLASH, chicken.getLocation(), 2);
                        world.playSound(chicken.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                        chicken.remove();
                    }, 60);
                }
                else {
                    player.playNote(player.getLocation(), Instrument.XYLOPHONE, Note.sharp(1, Note.Tone.F));
                    world.spawnParticle(Particle.SPELL_INSTANT, eggLocation, 5);
                    ev.getItem().remove();
                    String regionName = nbtItem.getString("EggHuntRegion");
                    plugin.sendToWebServer(player, treasureType, regionName);
                }
            }
            ev.setCancelled(true);
        }
    }

    public boolean isEasterEgg(ItemStack is) {
        if(is.getType() == Material.PLAYER_HEAD) {
            NBTItem nbtItem = new NBTItem(is);
            return nbtItem.getKeys().contains("TreasureType");
        }
        return false;
    }

    private int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private final EasterEggHunt plugin;
    private Random random;
}
