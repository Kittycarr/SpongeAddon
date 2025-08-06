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
    }
}
