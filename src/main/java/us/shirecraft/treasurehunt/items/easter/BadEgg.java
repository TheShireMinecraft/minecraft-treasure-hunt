package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class BadEgg extends TreasureItem {
    public BadEgg() {
        super("Bad",0, 12);
        setTexture(BAD_EGG_TEXTURE);
        setPlayerUuid(BAD_EGG_PLAYER_UUID);
    }

    private final String BAD_EGG_TEXTURE     = "5f7de9fafa8fcb77d5944e628b72042b9f7988de43e422983c78d3762d6d7";
    private final String BAD_EGG_PLAYER_UUID = "c716726b-0a33-47e4-af74-1601dff8776b";
}
