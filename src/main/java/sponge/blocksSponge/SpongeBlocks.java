package sponge.blocksSponge;

import net.minecraft.src.Block;
import net.minecraft.src.ItemBlock;

import static net.minecraft.src.Block.soundGrassFootstep;

public class SpongeBlocks {

    public static int wetSpongeID = 869;
    public static Block wetSponge;

    public static void initBlocks() {
        wetSponge = new BlockWetSponge(wetSpongeID).setTextureName("wetSponge.png").setUnlocalizedName("wetSponge").setHardness(0.6f).setStepSound(soundGrassFootstep);
        initBlockItems(wetSpongeID);
    }
    public static void initBlockItems(int blockID) {
        new ItemBlock(blockID - 256);
    }
}
