package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class SpookyPenguin extends TreasureItem {
    public SpookyPenguin() {
        super("Spooky Penguin",100, 2);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTFiZjEzZWFkMzBmMTRkMWE2ODdlNjg4OWM1MmQ3NzIyMWMxNGNkZThlY2VlNGFlYjI4MDMzYWRlNzNiMWNkOSJ9fX0=";
    private final String PLAYER_UUID = "8942cb66-9cf2-4828-97c8-68ffc59e92a7";
}
