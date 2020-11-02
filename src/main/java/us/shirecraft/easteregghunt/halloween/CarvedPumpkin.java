package us.shirecraft.easteregghunt.halloween;

import us.shirecraft.easteregghunt.TreasureItem;

public class CarvedPumpkin extends TreasureItem {
    public CarvedPumpkin() {
        super("Carved Pumpkin",60, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk1YWZkMDE5OGJjM2ExODNlYzlkYjZmOGRjODY1Y2RkNDExMDQ5MmM3NGY4OWU1OWQ1MGIxMTA4YzhmYTBlYSJ9fX0=";
    private final String PLAYER_UUID = "c6f635dd-7770-40e5-9743-39d64016edbf";
}
