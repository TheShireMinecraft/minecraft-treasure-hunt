package us.shirecraft.treasurehunt.items.halloween;

import us.shirecraft.treasurehunt.TreasureItem;

public class CarvedPumpkin extends TreasureItem {
    public CarvedPumpkin() {
        super("Carved Pumpkin",60, 30);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "395afd0198bc3a183ec9db6f8dc865cdd4110492c74f89e59d50b1108c8fa0ea";
    private final String PLAYER_UUID = "c6f635dd-7770-40e5-9743-39d64016edbf";
}
