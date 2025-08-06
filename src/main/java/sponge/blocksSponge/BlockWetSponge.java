package sponge.blocksSponge;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockWetSponge
        extends Block {

    protected BlockWetSponge(int id) {
        super(id, Material.sponge);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

}
