package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class PlateOfCookies extends TreasureItem {
    public PlateOfCookies() {
        super("Plate of Cookies",10, 40);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "641148570ad9e12044205d632a437163ed1cd8d29c7dc5b292b6f547826af16";
    private final String PLAYER_UUID = "f328c42a-a87b-4825-8e62-7b8b21e3a6ba";
}
