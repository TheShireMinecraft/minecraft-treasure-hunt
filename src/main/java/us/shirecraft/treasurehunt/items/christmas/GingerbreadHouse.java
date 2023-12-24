package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class GingerbreadHouse extends TreasureItem {
    public GingerbreadHouse() {
        super("Gingerbread House",60, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBmNDg1NTkzNjc3YWFiMjhkYmNhNDViMTAyMmYyMWIxYmM3ODk2YmM5ZmU0NDA2YWJlNGQ2MDcwNTc4MjYifX19";
    private final String PLAYER_UUID = "469539e8-cdae-4666-a3a5-1aedb0b4f95d";
}
