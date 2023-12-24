package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartyHat extends TreasureItem {

    public PartyHat() {
        super("Party Hat",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjZmM2ViZWM5YzY0NDhjMjk5OTczZDE4MTJhYjk4YzNjMzM2MDZhYmQxOGI3M2FhY2VlOWJlOTVjYzdiYmMzYyJ9fX0=";
    private final String PLAYER_UUID = "e7a6a760-6e68-4249-bfdb-4a314222ce24";
}
