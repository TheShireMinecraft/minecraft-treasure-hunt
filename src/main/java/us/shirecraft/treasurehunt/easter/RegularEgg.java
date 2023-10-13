package us.shirecraft.treasurehunt.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class RegularEgg extends TreasureItem {
    public RegularEgg() {
        super("Regular",1, 70);
        setTexture(REGULAR_EGG_TEXTURE);
        setPlayerUuid(REGULAR_EGG_PLAYER_UUID);
    }

    private final String REGULAR_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzViMTQ4ZjM4OWNlOTU3NGRkMmE1NjVmM2ZlMjMzNzI0OTc0OThmYTIzNjUyYjlkOTlmNjc4OTVjN2IyYjcwIn19fQ==";
    private final String REGULAR_EGG_PLAYER_UUID = "d3772c0b-d0d4-47df-baef-d35cd0bc5d35";
}
