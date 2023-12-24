package us.shirecraft.treasurehunt.items.party;

import us.shirecraft.treasurehunt.TreasureItem;

public class PartyCrab extends TreasureItem {

    public PartyCrab() {
        super("Party Crab",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "993c60fd0dd130695e378eef010a7d3c5dfde77f6b82b20b8124cfb830017ff";
    private final String PLAYER_UUID = "3a80a521-2a9e-4a2e-9534-93e34931a3f4";
}
