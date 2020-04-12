package us.shirecraft.easteregghunt;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Egg implements Comparable {
    public Egg(String name, int value, int frequency) {
        item = new ItemStack(Material.PLAYER_HEAD);
        nbtItem = new NBTItem(item);
        nbtItem.setString("EasterEgg", name);
        display = nbtItem.addCompound("display");
        skull = nbtItem.addCompound("SkullOwner");
        texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        nbtItem.setBoolean("Unbreakable", true);
        setName(name);
        setValue(value);
        setFrequency(frequency);
    }

    public void setPlayerUuid(String playerUuid) {
        this.playerUuid = playerUuid;
        skull.setString("Id", playerUuid);

    }

    public void setName(String name) {
        display.setString("Name", name + " Egg");
        display.getStringList("Lore").add("It's an egg!");
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setTexture(String texture) {
        this.texture.setString("Value", texture);
    }

    public ItemStack getItem() {
        return this.nbtItem.getItem();
    }

    @Override
    public String toString() {
        return this.name + " Egg";
    }

    public int compareTo(Object o) {
        return ((Egg)o).value>this.value?-1:1;
    }

    private int value;
    private int frequency;
    private String playerUuid;
    private NBTItem nbtItem;
    private NBTCompound display;
    private NBTCompound skull;
    private NBTListCompound texture;
    private ItemStack item;
    private String name;
}
