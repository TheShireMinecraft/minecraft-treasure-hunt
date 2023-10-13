package us.shirecraft.treasurehunt.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class BadEgg extends TreasureItem {
    public BadEgg() {
        super("Bad",0, 12);
        setTexture(BAD_EGG_TEXTURE);
        setPlayerUuid(BAD_EGG_PLAYER_UUID);
    }

    private final String BAD_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY3ZGU5ZmFmYThmY2I3N2Q1OTQ0ZTYyOGI3MjA0MmI5Zjc5ODhkZTQzZTQyMjk4M2M3OGQzNzYyZDZkNyJ9fX0=";
    private final String BAD_EGG_PLAYER_UUID = "c716726b-0a33-47e4-af74-1601dff8776b";
}
