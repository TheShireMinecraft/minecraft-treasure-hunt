package us.shirecraft.treasurehunt;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import de.tr7zw.nbtapi.NBTItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

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
        GameProfile profile = new GameProfile(UUID.fromString(_playerUuid), "unknown");
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            return _item;
        }

        // Set texture
        propertyMap.put("textures", new Property("textures", _texture));

        // Get meta class for reflection
        ItemMeta headMeta = _item.getItemMeta();
        assert headMeta != null;

        // Set name and lore
        headMeta.displayName(Component.text(_name));
        headMeta.lore(List.of(
            Component.text("I've found some Treasure!")
        ));

        // Set profile
        Class<?> headMetaClass = headMeta.getClass();
        Field profileField;
        try {
            profileField = headMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException iae ) {
            iae.printStackTrace();
        }

        // Replace item meta
        _item.setItemMeta(headMeta);

        // Add custom NBT tags
        _nbtItem = new NBTItem(_item);
        _nbtItem.setString("TreasureType", _name);
        _nbtItem.setString("TreasureHuntType", _huntType);
        _nbtItem.setBoolean("Unbreakable", true);
        _nbtItem.setString("TreasureHuntRegion", _regionName);
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
