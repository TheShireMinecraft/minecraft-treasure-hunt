package us.shirecraft.easteregghunt.thanksgiving;

import us.shirecraft.easteregghunt.TreasureItem;

public class RoastDinner extends TreasureItem {
    public RoastDinner() {
        super("Roast Dinner",2, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA2NTU1NzA2YjY0MWZkYWY0MzZjMDc2NjNmOTIzYWZjNWVlNzIxNDZmOTAxOTVmYjMzN2I5ZGU3NjY1ODhkIn19fQ==";
    private final String PLAYER_UUID = "bf871b6c-92c7-454c-aa05-174e6cf98c45";
}
