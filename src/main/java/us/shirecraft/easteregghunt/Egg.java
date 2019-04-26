package us.shirecraft.easteregghunt;

import org.bukkit.inventory.ItemStack;

public class Egg {
    public Egg(int value, int frequency) {
        setValue(value);
        setFrequency(frequency);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setPlayerUuid(String playerUuid) {
        this.playerUuid = playerUuid;
    }

    public void setDroppedItem(ItemStack item) {
        this.droppedItem = item;
    }

    private int value;
    private int frequency;
    private String texture;
    private String playerUuid;
    private ItemStack droppedItem;
}
