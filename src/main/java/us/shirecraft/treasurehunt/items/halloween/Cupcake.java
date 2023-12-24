package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class Cupcake extends TreasureItem {
    public Cupcake() {
        super("Halloween Cupcake",40, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "26988923dd4b1a394b7d6247675f54cbadf05a6022ce6c13b738613f87466035";
    private final String PLAYER_UUID = "d69e8a73-2d8e-4447-bc75-ff833bc111b0";
}
