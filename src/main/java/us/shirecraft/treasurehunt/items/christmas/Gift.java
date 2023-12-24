package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class Gift extends TreasureItem {
    public Gift() {
        super("Gift",3, 90);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "a03bd00421729cd635cd3b48243430ad47cf707018a5916ff59549d5ecd6f879";
    private final String PLAYER_UUID = "3167ba84-a532-4956-9db3-a312e2aaa12e";
}
