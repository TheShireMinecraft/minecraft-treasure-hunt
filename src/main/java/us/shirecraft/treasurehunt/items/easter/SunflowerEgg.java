package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class SunflowerEgg extends TreasureItem {
    public SunflowerEgg() {
        super("Sunflower",20, 15);
        setTexture(SUNFLOWER_EGG_TEXTURE);
        setPlayerUuid(SUNFLOWER_EGG_PLAYER_UUID);
    }

    private final String SUNFLOWER_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWYxOGI5YTQzNmEyN2Y4MTNjMjg3ZWI2NzM3OWVmOGFkYmZkYzcwYWZhZjMwNGM0M2IxNjZjZTk4NmRkOCJ9fX0=";
    private final String SUNFLOWER_EGG_PLAYER_UUID = "73028eb9-2ab9-4bb6-9bfd-a152966b5243";
}
