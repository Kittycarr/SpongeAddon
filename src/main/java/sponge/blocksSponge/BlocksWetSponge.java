package sponge.blocksSponge;

import net.minecraft.src.*;
import sponge.DryInNetherBlocks;

public class BlocksWetSponge
        extends DryInNetherBlocks {

    protected BlocksWetSponge(int id) {
        super(id, Material.sponge);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

}
