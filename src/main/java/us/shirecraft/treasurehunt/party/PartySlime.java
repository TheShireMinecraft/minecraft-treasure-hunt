package us.shirecraft.treasurehunt.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartySlime extends TreasureItem {

    public PartySlime() {
        super("Party Slime",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmU3Mjk0ZTMzMDg3MmRlOGM4NzA4NWY5NGI1OGMwYjhhNDJlZDJjNTljZGJjNmVmZGM1MjZjNWI1OGNmZjdiIn19fQ==";
    private final String PLAYER_UUID = "97559785-83dd-4c39-8866-63aeefad775a";
}
