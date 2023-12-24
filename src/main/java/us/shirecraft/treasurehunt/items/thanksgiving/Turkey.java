package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class Turkey extends TreasureItem {
    public Turkey() {
        super("Turkey",3, 10);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "b7fcca58d6996621b2a66becd1b8d4d1b1901ed1f96b09ada2406de9765fb097";
    private final String PLAYER_UUID = "e4a0d1c5-d868-4b6e-aee6-290644743e1e";
}
