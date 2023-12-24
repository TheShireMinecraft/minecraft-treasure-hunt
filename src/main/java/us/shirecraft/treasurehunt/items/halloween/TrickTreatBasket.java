package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class TrickTreatBasket extends TreasureItem {
    public TrickTreatBasket() {
        super("Trick or Treat Basket",30, 60);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "35a9cce3223be1bb927bd2635e18b644601d521b64ca068c541d3bc960908c3a";
    private final String PLAYER_UUID = "82af1c74-0b63-4e3f-8f53-7fdf99b5261b";
}
