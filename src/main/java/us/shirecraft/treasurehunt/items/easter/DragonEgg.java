package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class DragonEgg extends TreasureItem {
    public DragonEgg() {
        super("Dragon",100, 5);
        setTexture(DRAGON_EGG_TEXTURE);
        setPlayerUuid(DRAGON_EGG_PLAYER_UUID);
    }

    private final String DRAGON_EGG_TEXTURE     = "3c151fb54b21fe5769ffb4825b5bc92da73657f214380e5d0301e45b6c13f7d";
    private final String DRAGON_EGG_PLAYER_UUID = "6b5afe13-b3b2-4c3c-a938-d6930e2d31c2";
}
