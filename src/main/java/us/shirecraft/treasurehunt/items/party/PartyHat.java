package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartyHat extends TreasureItem {

    public PartyHat() {
        super("Party Hat",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "66f3ebec9c6448c299973d1812ab98c3c33606abd18b73aacee9be95cc7bbc3c";
    private final String PLAYER_UUID = "e7a6a760-6e68-4249-bfdb-4a314222ce24";
}
