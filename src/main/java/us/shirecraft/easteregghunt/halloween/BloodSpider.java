package us.shirecraft.easteregghunt.halloween;

import us.shirecraft.easteregghunt.TreasureItem;

public class BloodSpider extends TreasureItem {
    public BloodSpider() {
        super("Blood Spider",30, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODMwMDk4NmVkMGEwNGVhNzk5MDRmNmFlNTNmNDllZDNhMGZmNWIxZGY2MmJiYTYyMmVjYmQzNzc3ZjE1NmRmOCJ9fX0=";
    private final String PLAYER_UUID = "bf0be255-89ce-415a-b7ca-037424dce343";
}
