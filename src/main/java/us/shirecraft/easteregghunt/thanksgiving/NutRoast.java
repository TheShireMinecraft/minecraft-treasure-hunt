package us.shirecraft.easteregghunt.thanksgiving;

import us.shirecraft.easteregghunt.TreasureItem;

public class NutRoast extends TreasureItem {
    public NutRoast() {
        super("Nut Roast",2, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThlZWFmZGY2ZDdjNmM5OTQzZTQ0YmZjZmRiZTAzN2Q2YzE1ZDZkNWUzNTRjM2NjZjcyYmY4ODZjZGU0ZTFjNiJ9fX0=";
    private final String PLAYER_UUID = "37404cc5-1385-4be9-abd2-5c6cfbfaf6ce";
}
