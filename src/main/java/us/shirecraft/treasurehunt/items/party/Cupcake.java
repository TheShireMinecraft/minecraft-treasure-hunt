package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class Cupcake extends TreasureItem {

    public Cupcake() {
        super("Cupcake",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "e5716f4080848aa4b79da3706b94e7b72fe4b1bd64938c08cd5c03ae3b73599";
    private final String PLAYER_UUID = "68b81805-f10c-4079-9afa-40191f63dad5";
}
