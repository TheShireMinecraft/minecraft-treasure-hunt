package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class VioletEgg extends TreasureItem {
    public VioletEgg() {
        super("Violet",10, 30);
        setTexture(VOILET_EGG_TEXTURE);
        setPlayerUuid(VIOLET_EGG_PLAYER_UUID);
    }

    private final String VOILET_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWNjZDUzYjhkOWU4ZjQ2M2JjMWYwNmZjMWEzNjdiNjI0YzIxZWY5MzY5NWE0MDcxZjI2OWQ4YmM3NzlkIn19fQ==";
    private final String VIOLET_EGG_PLAYER_UUID = "b84cf9d3-faf7-49f9-a25d-9040825c6153";
}
