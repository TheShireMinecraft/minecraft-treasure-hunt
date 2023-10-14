package us.shirecraft.treasurehunt.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class RainbowCake extends TreasureItem {

    public RainbowCake() {
        super("Rainbow Cake",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTYwZmFiYjE4Y2RlYjI3NmJiZjM2OGRkOTcxOTE4YTVkYThiOTdiYjg4NjY4MzcwYWM1YzMxZmZkMGU2NGMxYyJ9fX0=";
    private final String PLAYER_UUID = "efa825af-5b68-49cc-a875-9a8b11a55380";
}
