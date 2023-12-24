package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class BloodSpider extends TreasureItem {
    public BloodSpider() {
        super("Blood Spider",30, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "8300986ed0a04ea79904f6ae53f49ed3a0ff5b1df62bba622ecbd3777f156df8";
    private final String PLAYER_UUID = "bf0be255-89ce-415a-b7ca-037424dce343";
}
