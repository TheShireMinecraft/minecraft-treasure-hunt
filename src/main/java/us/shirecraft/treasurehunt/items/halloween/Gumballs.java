package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class Gumballs extends TreasureItem {
    public Gumballs() {
        super("Jar of Gumballs",30, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "3a943baf71458596aff4eba879f8ac5d2ba267c98385e67673f48ebea5fa";
    private final String PLAYER_UUID = "aab2216a-e1f7-41a4-a6e5-118fb051fa5d";
}
