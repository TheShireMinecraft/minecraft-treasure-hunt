package us.shirecraft.easteregghunt;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import us.shirecraft.easteregghunt.halloween.BloodSpider;

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
            if(ev.getEntity() instanceof Player player) {
                NBTItem nbtItem = new NBTItem(is);
                String huntType = plugin.validateHuntType(nbtItem.getString("EggHuntType"));
                String treasureType = nbtItem.getString("TreasureType");

                player.sendMessage("§6 ** You found a " + treasureType + (huntType.equals("easter")?" Egg":""));
                Location eggLocation = ev.getItem().getLocation();
                World world = eggLocation.getWorld();
                assert world != null;

                if(getRandom(1,100) < 6 && huntType.equals("easter")) {
                    player.sendMessage("§a ** The egg hatched before you could collect it!");

                    world.spawnParticle(Particle.EXPLOSION_LARGE, eggLocation, 2);
                    ev.getItem().remove();

                    Chicken chicken = (Chicken) world.spawnEntity(eggLocation, EntityType.CHICKEN);
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
                if(getRandom(1,100) < 10 && huntType.equals("halloween") && treasureType.equals("Blood Spider")) {
                    player.sendMessage("§a ** It's... §a§lALIVE§a!");

                    world.spawnParticle(Particle.EXPLOSION_LARGE, eggLocation, 2);
                    ev.getItem().remove();

                    CaveSpider spider = (CaveSpider) world.spawnEntity(eggLocation, EntityType.CAVE_SPIDER);
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

                    world.spawnParticle(Particle.SPELL_INSTANT, eggLocation, 2);
                    ev.getItem().remove();

                    Location spawnSnowmanLoc = eggLocation.clone();
                    spawnSnowmanLoc.add(0, .05d, 0);

                    Snowman snowman = (Snowman) world.spawnEntity(spawnSnowmanLoc, EntityType.SNOWMAN);
                    snowman.setDerp(true);
                    snowman.setAI(false);
                    snowman.setTicksLived(1);
                    snowman.setCanPickupItems(false);
                    snowman.setCollidable(false);
                    snowman.setInvulnerable(true);
                    snowman.setAware(false);
                    snowman.setFreezeTicks(snowman.getMaxFreezeTicks());
                    snowman.setMetadata("egghunt.random-snowman", new FixedMetadataValue(plugin, true));

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
    private final Random random;
}
