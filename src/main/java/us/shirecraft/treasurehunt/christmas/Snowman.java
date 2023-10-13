package us.shirecraft.treasurehunt.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class Snowman extends TreasureItem {
    public Snowman() {
        super("Snowman",30, 40);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFlZDlmZTRlZDA4OTNlMzI1ZjRmYmQzMmIwOTNjMWNjNTYyY2JhMjdmZjczMzU5ZDM1NmYxYzI4OGU0NDFmOSJ9fX0=";
    private final String PLAYER_UUID = "3047fa67-6a55-4c58-8baf-bfb42a738836";
}
