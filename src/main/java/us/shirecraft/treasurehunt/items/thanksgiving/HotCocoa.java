package us.shirecraft.treasurehunt.items.thanksgiving;

import us.shirecraft.treasurehunt.TreasureItem;

public class HotCocoa extends TreasureItem {
    public HotCocoa() {
        super("Hot Cocoa",10, 50);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "268d81f2a495cf767e97d56ebcb4df7bb1df0559d2735cbc99f7c78eeff4f68f";
    private final String PLAYER_UUID = "270927b6-76ac-4274-a2ba-948b9818e7eb";
}
