package us.shirecraft.treasurehunt.items.christmas;

import us.shirecraft.treasurehunt.TreasureItem;

public class GingerbreadMan extends TreasureItem {
    public GingerbreadMan() {
        super("Gingerbread Man",80, 4);
        setTexture(TEXTURE);
        setPlayerUuid(PLAYER_UUID);
    }

    private final String TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc5YzkzMmMzMWUxNmZkNjVjZTNjOTljY2E5ODY0NWFiMmYxNmIyNjIzYjVlMWU3MmM2ZGU2ODlhNjUxODdmIn19fQ==";
    private final String PLAYER_UUID = "d4ec2666-2001-4ecf-909c-b9ecff73706e";
}
