package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class SunflowerEgg extends TreasureItem {
    public SunflowerEgg() {
        super("Sunflower",20, 15);
        setTexture(SUNFLOWER_EGG_TEXTURE);
        setPlayerUuid(SUNFLOWER_EGG_PLAYER_UUID);
    }

    private final String SUNFLOWER_EGG_TEXTURE     = "1f18b9a436a27f813c287eb67379ef8adbfdc70afaf304c43b166ce986dd8";
    private final String SUNFLOWER_EGG_PLAYER_UUID = "73028eb9-2ab9-4bb6-9bfd-a152966b5243";
}
