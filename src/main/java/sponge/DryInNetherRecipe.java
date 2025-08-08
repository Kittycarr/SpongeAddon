package sponge;

import btw.crafting.recipe.types.KilnRecipe;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import java.util.Arrays;

public class DryInNetherRecipe {

    private final Block output;
    private final Block block;
    private final int[] metadatas;
    private final byte cookTimeMultiplier;

    public DryInNetherRecipe(Block output, Block block, int[] metadatas, byte cookTimeMultiplier) {
        this.output = output;
        this.block = block;
        this.metadatas = metadatas;
        this.cookTimeMultiplier = cookTimeMultiplier;

    }

    public boolean ignoreMetadata() {
        return this.metadatas.length == 1 && this.metadatas[0] == Short.MAX_VALUE;
    }

    public boolean matchesRecipe(DryInNetherRecipe recipe) {
        if (this.block == recipe.block) {
            return this.output.equals(recipe.output);
        }
        return false;
    }

    public boolean matchesInputs(Block block, int metadata) {
        boolean containsGivenMetadata = false;
        for (int i : this.metadatas) {
            if (i != metadata) continue;
            containsGivenMetadata = true;
            break;
        }
        return this.block.blockID == block.blockID && (containsGivenMetadata || this.ignoreMetadata());
    }



    public Block getInputblock() {
        return this.block;
    }

    public Block getOutput() {
        return this.output;
    }


    public byte getCookTimeMultiplier() {
        return this.cookTimeMultiplier;
    }
}
