package sponge;

import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import sponge.blocksSponge.SpongeBlocks;

public class SpongeRecipes {
    public static void initRecipes() {

        RecipeManager.addRecipe(new ItemStack(Block.sponge, 1),new Object[]{
                "DBD",
                "BCB",
                "BAB",
                'A', new ItemStack(BTWItems.soulFlux),
                'B', new ItemStack(BTWItems.fabric),
                'C', new ItemStack(Item.slimeBall),
                'D', new ItemStack(BTWItems.straw)
        });
        RecipeManager.addKilnRecipe(new ItemStack(Block.sponge), SpongeBlocks.wetSponge);

        addDryInNetherRecipe(Block.sponge, SpongeBlocks.wetSponge,(byte) 2);
    }


    public static void addDryInNetherRecipe(Block output, Block block, byte cookTimeMultiplier) {
        addDryInNetherRecipe(output, block, Short.MAX_VALUE, cookTimeMultiplier);
    }

    public static void addDryInNetherRecipe(Block output, Block block, int metadata, byte cookTimeMultiplier) {
        addDryInNetherRecipe(output, block, new int[]{metadata}, cookTimeMultiplier);
    }

    public static void addDryInNetherRecipe(Block output, Block block, int[] metadatas, byte cookTimeMultiplier) {
        DryInNetherCraftingManager.instance.addRecipe(output, block, metadatas, cookTimeMultiplier);
    }
}
