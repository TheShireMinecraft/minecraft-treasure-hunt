package us.shirecraft.easteregghunt;

import com.mojang.authlib.GameProfile;

import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;

import de.tr7zw.nbtapi.NBTItem;

import io.github.coachluck.backpacksplus.utils.multiversion.ReflectionUtil;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class TreasureItem implements Comparable {
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
        GameProfile profile = new GameProfile(UUID.fromString(_playerUuid), null);
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            return _item;
        }

        // Set texture
        propertyMap.put("textures", new Property("textures", _texture));

        // Get meta class for reflection
        ItemMeta headMeta = _item.getItemMeta();

        // Set name and lore
        headMeta.setDisplayName(_name);
        headMeta.setLore(Arrays.asList("I've found some Treasure!"));

        // Set profile
        Class<?> headMetaClass = headMeta.getClass();
        ReflectionUtil.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);

        // Replace item meta
        _item.setItemMeta(headMeta);

        // Add custom NBT tags
        _nbtItem = new NBTItem(_item);
        _nbtItem.setString("TreasureType", _name);
        _nbtItem.setString("EggHuntType", _huntType);
        _nbtItem.setBoolean("Unbreakable", true);
        _nbtItem.setString("EggHuntRegion", _regionName);

        return _nbtItem.getItem();
    }

    @Override
    public String toString() {
        return this._name;
    }

    public int compareTo(Object o) {
        return ((TreasureItem)o)._value > this._value ? -1 : 1;
    }

    private int _value;
    private int _frequency;
    private String _playerUuid;
    private NBTItem _nbtItem;
    private ItemStack _item;
    private String _texture;
    private String _name;
    private String _huntType;
    private String _regionName;
}
