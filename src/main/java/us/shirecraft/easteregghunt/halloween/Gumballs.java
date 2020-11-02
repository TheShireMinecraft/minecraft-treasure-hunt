package us.shirecraft.easteregghunt.halloween;

import us.shirecraft.easteregghunt.TreasureItem;

public class Gumballs extends TreasureItem {
    public Gumballs() {
        super("Jar of Gumballs",30, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E5NDNiYWY3MTQ1ODU5NmFmZjRlYmE4NzlmOGFjNWQyYmEyNjdjOTgzODVlNjc2NzNmNDhlYmVhNWZhIn19fQ==";
    private final String PLAYER_UUID = "aab2216a-e1f7-41a4-a6e5-118fb051fa5d";
}
