package us.shirecraft.easteregghunt.christmas;

import us.shirecraft.easteregghunt.TreasureItem;

public class Gift extends TreasureItem {
    public Gift() {
        super("Gift",3, 90);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAzYmQwMDQyMTcyOWNkNjM1Y2QzYjQ4MjQzNDMwYWQ0N2NmNzA3MDE4YTU5MTZmZjU5NTQ5ZDVlY2Q2Zjg3OSJ9fX0=";
    private final String PLAYER_UUID = "3167ba84-a532-4956-9db3-a312e2aaa12e";
}
