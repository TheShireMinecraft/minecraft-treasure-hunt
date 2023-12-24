package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class ReindeerPlushy extends TreasureItem {
    public ReindeerPlushy() {
        super("Reindeer Plushy",60, 12);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "f05054571228373c5cdb84f2165c9a200baf55445285548eacae0594cb6592ee";
    private final String PLAYER_UUID = "f328c42a-a87b-4825-8e62-7b8b21e3a6ba";
}
