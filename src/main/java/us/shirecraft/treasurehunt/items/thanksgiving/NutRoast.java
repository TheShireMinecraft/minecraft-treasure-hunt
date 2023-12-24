package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class NutRoast extends TreasureItem {
    public NutRoast() {
        super("Nut Roast",2, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "98eeafdf6d7c6c9943e44bfcfdbe037d6c15d6d5e354c3ccf72bf886cde4e1c6";
    private final String PLAYER_UUID = "37404cc5-1385-4be9-abd2-5c6cfbfaf6ce";
}
