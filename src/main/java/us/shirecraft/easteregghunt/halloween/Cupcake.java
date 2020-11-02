package us.shirecraft.easteregghunt.halloween;

import us.shirecraft.easteregghunt.TreasureItem;

public class Cupcake extends TreasureItem {
    public Cupcake() {
        super("Halloween Cupcake",40, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY5ODg5MjNkZDRiMWEzOTRiN2Q2MjQ3Njc1ZjU0Y2JhZGYwNWE2MDIyY2U2YzEzYjczODYxM2Y4NzQ2NjAzNSJ9fX0=";
    private final String PLAYER_UUID = "d69e8a73-2d8e-4447-bc75-ff833bc111b0";
}
