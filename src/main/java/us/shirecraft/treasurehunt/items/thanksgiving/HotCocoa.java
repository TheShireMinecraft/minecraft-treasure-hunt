package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class HotCocoa extends TreasureItem {
    public HotCocoa() {
        super("Hot Cocoa",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY4ZDgxZjJhNDk1Y2Y3NjdlOTdkNTZlYmNiNGRmN2JiMWRmMDU1OWQyNzM1Y2JjOTlmN2M3OGVlZmY0ZjY4ZiJ9fX0=";
    private final String PLAYER_UUID = "270927b6-76ac-4274-a2ba-948b9818e7eb";
}
