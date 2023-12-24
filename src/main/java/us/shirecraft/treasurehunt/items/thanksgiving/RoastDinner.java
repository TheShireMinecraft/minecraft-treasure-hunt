package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class RoastDinner extends TreasureItem {
    public RoastDinner() {
        super("Roast Dinner",2, 20);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "f06555706b641fdaf436c07663f923afc5ee72146f90195fb337b9de766588d";
    private final String PLAYER_UUID = "bf871b6c-92c7-454c-aa05-174e6cf98c45";
}
