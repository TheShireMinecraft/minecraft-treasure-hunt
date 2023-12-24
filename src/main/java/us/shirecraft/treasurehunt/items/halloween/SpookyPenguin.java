package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class SpookyPenguin extends TreasureItem {
    public SpookyPenguin() {
        super("Spooky Penguin",100, 2);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "51bf13ead30f14d1a687e6889c52d77221c14cde8ecee4aeb28033ade73b1cd9";
    private final String PLAYER_UUID = "8942cb66-9cf2-4828-97c8-68ffc59e92a7";
}
