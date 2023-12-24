package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class GingerbreadHouse extends TreasureItem {
    public GingerbreadHouse() {
        super("Gingerbread House",60, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "40f485593677aab28dbca45b1022f21b1bc7896bc9fe4406abe4d607057826";
    private final String PLAYER_UUID = "469539e8-cdae-4666-a3a5-1aedb0b4f95d";
}
