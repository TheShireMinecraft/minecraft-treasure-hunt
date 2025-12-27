package us.shirecraft.treasurehunt;

import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class TreasureItem implements Comparable<TreasureItem> {
    public TreasureItem(String name, int value, int frequency) {
        setName(name);
        setValue(value);
        setFrequency(frequency);
    }

    public void setPlayerUuid(String playerUuid) {
        this._playerUuid = playerUuid;
    }

    public void setName(String name) {
        this._name = name;
    }

    public void setRegionName(String name) {
        this._regionName = name;
    }

    public void setHuntType(String type) {
        this._huntType = type;
    }

    public void setValue(int value) {
        this._value = value;
    }

    public void setFrequency(int frequency) {
        this._frequency = frequency;
    }

    public void setTexture(String texture) {
        this._texture = texture;
    }

    public ItemStack getItem() {
        // Create item stack
        _item = new ItemStack(Material.PLAYER_HEAD, 1);

        // Create game profile
        var playerProfile = Bukkit.createProfile(UUID.fromString(_playerUuid), "unknown");

        // Set texture
        var currentTextures = playerProfile.getTextures();
        try {
            currentTextures.setSkin(new URL("http://textures.minecraft.net/texture/" + _texture));
        } catch (MalformedURLException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Invalid texture URL", e);
        }
        playerProfile.setTextures(currentTextures);

        // Get meta class for reflection
        ItemMeta itemMeta = _item.getItemMeta();
        assert itemMeta != null;
        SkullMeta headMeta = (SkullMeta) itemMeta;

        // Set name and lore
        headMeta.displayName(Component.text(_name));
        headMeta.lore(List.of(
            Component.text("I've found some Treasure!")
        ));

        // Set profile
        headMeta.setPlayerProfile(playerProfile);

        // Replace item meta
        _item.setItemMeta(headMeta);

        // Add custom NBT tags
        _nbtItem = new NBTItem(_item);
        _nbtItem.setString("TreasureType", _name);
        _nbtItem.setString("TreasureHuntType", _huntType);
        _nbtItem.setBoolean("Unbreakable", true);
        _nbtItem.setString("TreasureHuntRegion", _regionName);
        _nbtItem.setString("TreasureHuntConst", "TRHU");
        _nbtItem.setString("ItemId", UUID.randomUUID().toString()); // Stop treasure items stacking

        return _nbtItem.getItem();
    }

    @Override
    public String toString() {
        return this._name;
    }

    public int compareTo(TreasureItem o) {
        return o._value > this._value ? -1 : 1;
    }

    private int _value;
    @SuppressWarnings("unused")
    private int _frequency;
    private String _playerUuid;
    private NBTItem _nbtItem;
    private ItemStack _item;
    private String _texture;
    private String _name;
    private String _huntType;
    private String _regionName;
}
