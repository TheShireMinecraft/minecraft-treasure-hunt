package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class RegularEgg extends TreasureItem {
    public RegularEgg() {
        super("Regular",1, 70);
        setTexture(REGULAR_EGG_TEXTURE);
        setPlayerUuid(REGULAR_EGG_PLAYER_UUID);
    }

    private final String REGULAR_EGG_TEXTURE     = "c5b148f389ce9574dd2a565f3fe23372497498fa23652b9d99f67895c7b2b70";
    private final String REGULAR_EGG_PLAYER_UUID = "d3772c0b-d0d4-47df-baef-d35cd0bc5d35";
}
