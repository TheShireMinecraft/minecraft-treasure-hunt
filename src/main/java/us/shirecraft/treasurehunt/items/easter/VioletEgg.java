package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class VioletEgg extends TreasureItem {
    public VioletEgg() {
        super("Violet",10, 30);
        setTexture(VOILET_EGG_TEXTURE);
        setPlayerUuid(VIOLET_EGG_PLAYER_UUID);
    }

    private final String VOILET_EGG_TEXTURE     = "1ccd53b8d9e8f463bc1f06fc1a367b624c21ef93695a4071f269d8bc779d";
    private final String VIOLET_EGG_PLAYER_UUID = "b84cf9d3-faf7-49f9-a25d-9040825c6153";
}
