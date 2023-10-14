package us.shirecraft.treasurehunt.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class Cupcake extends TreasureItem {

    public Cupcake() {
        super("Cupcake",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTU3MTZmNDA4MDg0OGFhNGI3OWRhMzcwNmI5NGU3YjcyZmU0YjFiZDY0OTM4YzA4Y2Q1YzAzYWUzYjczNTk5In19fQ==";
    private final String PLAYER_UUID = "68b81805-f10c-4079-9afa-40191f63dad5";
}
