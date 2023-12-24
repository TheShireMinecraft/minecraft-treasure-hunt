package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class RainbowEgg extends TreasureItem {
    public RainbowEgg() {
        super("Rainbow",30, 20);
        setTexture(RAINBOW_EGG_TEXTURE);
        setPlayerUuid(RAINBOW_EGG_PLAYER_UUID);
    }

    private final String RAINBOW_EGG_TEXTURE     = "7be7545297dfd6266bbaa2051825e8879cbfa42c7e7e24e50796f27ca6a18";
    private final String RAINBOW_EGG_PLAYER_UUID = "67e8c714-b4f1-4feb-8034-4a693723fdae";
}
