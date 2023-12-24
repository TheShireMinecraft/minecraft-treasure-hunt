package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class RainbowCake extends TreasureItem {

    public RainbowCake() {
        super("Rainbow Cake",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "560fabb18cdeb276bbf368dd971918a5da8b97bb88668370ac5c31ffd0e64c1c";
    private final String PLAYER_UUID = "efa825af-5b68-49cc-a875-9a8b11a55380";
}
