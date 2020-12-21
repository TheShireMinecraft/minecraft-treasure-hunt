package us.shirecraft.easteregghunt.christmas;

import us.shirecraft.easteregghunt.TreasureItem;

public class PlateOfCookies extends TreasureItem {
    public PlateOfCookies() {
        super("Plate of Cookies",10, 40);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQxMTQ4NTcwYWQ5ZTEyMDQ0MjA1ZDYzMmE0MzcxNjNlZDFjZDhkMjljN2RjNWIyOTJiNmY1NDc4MjZhZjE2In19fQ==";
    private final String PLAYER_UUID = "f328c42a-a87b-4825-8e62-7b8b21e3a6ba";
}
