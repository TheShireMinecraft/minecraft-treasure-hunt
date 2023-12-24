package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class Turkey extends TreasureItem {
    public Turkey() {
        super("Turkey",3, 10);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjdmY2NhNThkNjk5NjYyMWIyYTY2YmVjZDFiOGQ0ZDFiMTkwMWVkMWY5NmIwOWFkYTI0MDZkZTk3NjVmYjA5NyJ9fX0=";
    private final String PLAYER_UUID = "e4a0d1c5-d868-4b6e-aee6-290644743e1e";
}
