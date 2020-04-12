package us.shirecraft.easteregghunt;

public class DragonEgg extends Egg {
    public DragonEgg() {
        super("Dragon",100, 5);
        setTexture(DRAGON_EGG_TEXTURE);
        setPlayerUuid(DRAGON_EGG_PLAYER_UUID);
    }

    private final String DRAGON_EGG_TEXTURE     = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2MxNTFmYjU0YjIxZmU1NzY5ZmZiNDgyNWI1YmM5MmRhNzM2NTdmMjE0MzgwZTVkMDMwMWU0NWI2YzEzZjdkIn19fQ==";
    private final String DRAGON_EGG_PLAYER_UUID = "6b5afe13-b3b2-4c3c-a938-d6930e2d31c2";
}
