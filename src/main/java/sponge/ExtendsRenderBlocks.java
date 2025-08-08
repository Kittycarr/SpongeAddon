package sponge;

import net.minecraft.src.Block;
import net.minecraft.src.RenderBlocks;

public class ExtendsRenderBlocks extends RenderBlocks {

    public boolean renderBlockByRenderType(Block block, int i, int j, int k) {
        boolean bReturnValue = block.renderBlock(this, i, j, k);
        DryInNetherBlocks.staticDryInNetherBlocks.renderDryingInNether(this, i, j, k, bReturnValue);
        return bReturnValue;
    }
}
