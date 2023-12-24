package us.shirecraft.treasurehunt.items.easter;

import us.shirecraft.treasurehunt.TreasureItem;

public class PolkaDotEgg extends TreasureItem {
    public PolkaDotEgg() {
        super("Polka Dot",30, 50);
        setTexture(POLKADOT_EGG_TEXTURE);
        setPlayerUuid(POLKADOT_EGG_PLAYER_UUID);
    }

    private final String POLKADOT_EGG_TEXTURE     = "b2cd5df9d7f1fa8341fcce2f3c118e2f517e4d2d99df2c51d61d93ed7f83e13";
    private final String POLKADOT_EGG_PLAYER_UUID = "259631f6-804d-4ce5-9e67-d47e236728d2";
}
