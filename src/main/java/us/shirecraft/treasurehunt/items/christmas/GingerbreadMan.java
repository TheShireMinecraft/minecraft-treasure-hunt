package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class GingerbreadMan extends TreasureItem {
    public GingerbreadMan() {
        super("Gingerbread Man",80, 4);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "a79c932c31e16fd65ce3c99cca98645ab2f16b2623b5e1e72c6de689a65187f";
    private final String PLAYER_UUID = "d4ec2666-2001-4ecf-909c-b9ecff73706e";
}
