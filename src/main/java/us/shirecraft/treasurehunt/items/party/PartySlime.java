package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartySlime extends TreasureItem {

    public PartySlime() {
        super("Party Slime",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "fe7294e330872de8c87085f94b58c0b8a42ed2c59cdbc6efdc526c5b58cff7b";
    private final String PLAYER_UUID = "97559785-83dd-4c39-8866-63aeefad775a";
}
