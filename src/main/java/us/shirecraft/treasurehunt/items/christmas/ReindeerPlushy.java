package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class ReindeerPlushy extends TreasureItem {
    public ReindeerPlushy() {
        super("Reindeer Plushy",60, 12);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA1MDU0NTcxMjI4MzczYzVjZGI4NGYyMTY1YzlhMjAwYmFmNTU0NDUyODU1NDhlYWNhZTA1OTRjYjY1OTJlZSJ9fX0=";
    private final String PLAYER_UUID = "f328c42a-a87b-4825-8e62-7b8b21e3a6ba";
}
