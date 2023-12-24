package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class RainbowEgg extends TreasureItem {
    public RainbowEgg() {
        super("Rainbow",30, 20);
        setTexture(RAINBOW_EGG_TEXTURE);
        setPlayerUuid(RAINBOW_EGG_PLAYER_UUID);
    }

    private final String RAINBOW_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2JlNzU0NTI5N2RmZDYyNjZiYmFhMjA1MTgyNWU4ODc5Y2JmYTQyYzdlN2UyNGU1MDc5NmYyN2NhNmExOCJ9fX0=";
    private final String RAINBOW_EGG_PLAYER_UUID = "67e8c714-b4f1-4feb-8034-4a693723fdae";
}
