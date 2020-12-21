package us.shirecraft.easteregghunt.christmas;

import us.shirecraft.easteregghunt.TreasureItem;

public class FestivePenguin extends TreasureItem {
    public FestivePenguin() {
        super("Festive Penguin",100, 2);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDZmN2FiMmEzYmVmYTA3NDJmYWIxOTQyZDQwZWIwNjMyYzY5OTJmYjdiYjg3NmZmOTQ1MWZlZTRjZDNmMGE0OCJ9fX0=";
    private final String PLAYER_UUID = "5a0af103-f085-4d61-832d-d39dad01e4f4";
}
