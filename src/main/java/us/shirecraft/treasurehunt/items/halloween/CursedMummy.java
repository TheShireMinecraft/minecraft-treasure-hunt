package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class CursedMummy extends TreasureItem {
    public CursedMummy() {
        super("Cursed Mummy Head",0, 15);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "99a6450f5e32e15d92fbd28416dc2b24070ae6f062121798b3ba549c9c907372";
    private final String PLAYER_UUID = "7e858f48-3b6e-45db-8a85-8e9d527372e8";
}
