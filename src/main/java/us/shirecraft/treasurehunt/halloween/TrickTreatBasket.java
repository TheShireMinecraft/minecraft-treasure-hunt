package us.shirecraft.treasurehunt.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class TrickTreatBasket extends TreasureItem {
    public TrickTreatBasket() {
        super("Trick or Treat Basket",30, 60);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVhOWNjZTMyMjNiZTFiYjkyN2JkMjYzNWUxOGI2NDQ2MDFkNTIxYjY0Y2EwNjhjNTQxZDNiYzk2MDkwOGMzYSJ9fX0=";
    private final String PLAYER_UUID = "82af1c74-0b63-4e3f-8f53-7fdf99b5261b";
}
