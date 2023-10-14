package us.shirecraft.treasurehunt.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartyCrab extends TreasureItem {

    public PartyCrab() {
        super("Party Crab",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTkzYzYwZmQwZGQxMzA2OTVlMzc4ZWVmMDEwYTdkM2M1ZGZkZTc3ZjZiODJiMjBiODEyNGNmYjgzMDAxN2ZmIn19fQ==";
    private final String PLAYER_UUID = "3a80a521-2a9e-4a2e-9534-93e34931a3f4";
}
