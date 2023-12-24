package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class Snowman extends TreasureItem {
    public Snowman() {
        super("Snowman",30, 40);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "9aed9fe4ed0893e325f4fbd32b093c1cc562cba27ff73359d356f1c288e441f9";
    private final String PLAYER_UUID = "3047fa67-6a55-4c58-8baf-bfb42a738836";
}
