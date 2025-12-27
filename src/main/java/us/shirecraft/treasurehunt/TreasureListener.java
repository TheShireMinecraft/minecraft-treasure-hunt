package us.shirecraft.treasurehunt;

import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import us.shirecraft.treasurehunt.helpers.AntiCheatHelper;

import java.util.Random;

public class TreasureListener implements Listener {
    public TreasureListener(TreasureHunt plugin) {
        this.plugin = plugin;
        this.random = new Random();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerPickUpTreasure(EntityPickupItemEvent ev) {
        ItemStack is = ev.getItem().getItemStack();
        if(isTreasureItem(is)) {
            if(ev.getEntity() instanceof Player player) {
                NBTItem nbtItem = new NBTItem(is);
                String huntType = plugin.validateHuntType(nbtItem.getString("TreasureHuntType"));
                String treasureType = nbtItem.getString("TreasureType");
                String regionName = nbtItem.getString("TreasureHuntRegion");

                Location treasureLocation = ev.getItem().getLocation();
                World world = treasureLocation.getWorld();
                assert world != null;

                if(!AntiCheatHelper.locationIsWithinRegion(treasureLocation, regionName))
                {
                    player.sendMessage(
                        Component.text(
                            " ** Sorry, there was a problem with the treasure you found and it has not been counted.",
                            NamedTextColor.DARK_RED
                        )
                    );
                    ev.getItem().remove();
                    ev.setCancelled(true);
                    return;
                }

                player.sendMessage("§6 ** You found a " + treasureType + (huntType.equals("easter")?" Egg":""));

                if(getRandom(1,100) < 6 && huntType.equals("easter")) {
                    player.sendMessage("§a ** The egg hatched before you could collect it!");

                    world.spawnParticle(Particle.EXPLOSION, treasureLocation, 2);
                    ev.getItem().remove();

                    Chicken chicken = (Chicken) world.spawnEntity(treasureLocation, EntityType.CHICKEN);
                    chicken.setBaby();
                    chicken.setTicksLived(1);
                    world.spawnParticle(Particle.HEART, chicken.getLocation(), 2);
                    world.playSound(chicken.getLocation(), Sound.ENTITY_CHICKEN_AMBIENT, 1, 1);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        //noinspection ConstantConditions
                        if(null == chicken || null == world || null == player) {
                            return;
                        }
                        world.spawnParticle(Particle.FLASH, chicken.getLocation(), 2);
                        world.playSound(chicken.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                        chicken.remove();
                    }, 60);
                }
                if(getRandom(1,100) < 7 && huntType.equals("halloween") && treasureType.equals("Blood Spider")) {
                    player.sendMessage("§a ** It's... §a§lALIVE§a!");

                    world.spawnParticle(Particle.EXPLOSION, treasureLocation, 2);
                    ev.getItem().remove();

                    CaveSpider spider = (CaveSpider) world.spawnEntity(treasureLocation, EntityType.CAVE_SPIDER);
                    spider.setTicksLived(1);
                    world.spawnParticle(Particle.FLASH, spider.getLocation(), 2);
                    world.playSound(spider.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 1, 1);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        //noinspection ConstantConditions
                        if(null == spider || null == world || null == player) {
                            return;
                        }
                        world.spawnParticle(Particle.FLASH, spider.getLocation(), 2);
                        world.playSound(spider.getLocation(), Sound.ENTITY_SPIDER_HURT, 1, 1);
                        spider.remove();
                    }, 80);
                }
                if(getRandom(1,100) <= 10 && huntType.equals("christmas") && treasureType.equals("Snowman")) {
                    player.sendMessage("§a ** The snowman sprung to life before you could collect it!");

                    world.spawnParticle(Particle.INSTANT_EFFECT, treasureLocation, 2);
                    ev.getItem().remove();

                    Location spawnSnowmanLoc = treasureLocation.clone();
                    spawnSnowmanLoc.add(0, .05d, 0);

                    Snowman snowman = (Snowman) world.spawnEntity(spawnSnowmanLoc, EntityType.SNOW_GOLEM);
                    snowman.setDerp(true);
                    snowman.setAI(false);
                    snowman.setTicksLived(1);
                    snowman.setCanPickupItems(false);
                    snowman.setCollidable(false);
                    snowman.setInvulnerable(true);
                    snowman.setAware(false);
                    snowman.setFreezeTicks(snowman.getMaxFreezeTicks());
                    snowman.getPersistentDataContainer().set(
                         plugin.getSnowmanKey(),
                         PersistentDataType.BOOLEAN,
                         true
                    );

                    world.spawnParticle(Particle.SNOWFLAKE, snowman.getLocation(), 2);
                    world.playSound(snowman.getLocation(), Sound.ENTITY_PLAYER_HURT_FREEZE, 1, 1);

                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        //noinspection ConstantConditions
                        if(null == snowman || null == world || null == player) {
                            return;
                        }
                        world.spawnParticle(Particle.FLASH, snowman.getLocation(), 2);
                        world.playSound(snowman.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
                        snowman.damage(8192d);
                        snowman.remove();
                    }, 40);
                }
                else {
                    player.playNote(player.getLocation(), Instrument.XYLOPHONE, Note.sharp(1, Note.Tone.F));
                    world.spawnParticle(Particle.INSTANT_EFFECT, treasureLocation, 5);
                    ev.getItem().remove();
                    plugin.sendToWebServer(player, treasureType, regionName, world.getName());
                }
            }
            ev.setCancelled(true);
        }
    }

    public boolean isTreasureItem(ItemStack is) {
        if(is.getType() == Material.PLAYER_HEAD) {
            NBTItem nbtItem = new NBTItem(is);
            return nbtItem.getKeys().contains("TreasureType");
        }
        return false;
    }

    @SuppressWarnings("SameParameterValue")
    private int getRandom(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private final TreasureHunt plugin;
    private final Random random;
}
