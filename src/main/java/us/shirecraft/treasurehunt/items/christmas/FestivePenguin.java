package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class FestivePenguin extends TreasureItem {
    public FestivePenguin() {
        super("Festive Penguin",100, 2);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "46f7ab2a3befa0742fab1942d40eb0632c6992fb7bb876ff9451fee4cd3f0a48";
    private final String PLAYER_UUID = "5a0af103-f085-4d61-832d-d39dad01e4f4";
}
