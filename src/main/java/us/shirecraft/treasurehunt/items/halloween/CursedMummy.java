package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class CursedMummy extends TreasureItem {
    public CursedMummy() {
        super("Cursed Mummy Head",0, 15);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTlhNjQ1MGY1ZTMyZTE1ZDkyZmJkMjg0MTZkYzJiMjQwNzBhZTZmMDYyMTIxNzk4YjNiYTU0OWM5YzkwNzM3MiJ9fX0=";
    private final String PLAYER_UUID = "7e858f48-3b6e-45db-8a85-8e9d527372e8";
}
