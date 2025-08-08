package sponge;

import net.minecraft.src.Block;

import java.util.ArrayList;

public class DryInNetherCraftingManager {
    public static DryInNetherCraftingManager instance = new DryInNetherCraftingManager();
    private ArrayList<DryInNetherRecipe> recipes = new ArrayList();
    public static final boolean[] hasDryInNetherRecipe = new boolean[4096];

    private DryInNetherCraftingManager(){
    }

    public void addRecipe(Block output, Block block, int[] metadatas, byte cookTimeMultiplier) {
        DryInNetherRecipe recipe = new DryInNetherRecipe(output, block, metadatas, cookTimeMultiplier);
        this.recipes.add(recipe);
        hasDryInNetherRecipe[block.blockID] = true;
    }
    public boolean removeRecipe(Block output, Block block, int[] metadatas, byte cookTimeMultiplier) {
        DryInNetherRecipe recipeToRemove = new DryInNetherRecipe(output, block, metadatas, cookTimeMultiplier);
        for (DryInNetherRecipe recipe : this.recipes) {
            if (!recipe.matchesRecipe(recipeToRemove)) continue;
            this.recipes.remove(recipe);
            hasDryInNetherRecipe[block.blockID] = false;
            return true;
        }
        return false;
    }

    public DryInNetherRecipe getRecipe(Block block, int metadata) {
        for (DryInNetherRecipe recipe : this.recipes) {
            if (!recipe.matchesInputs(block, metadata)) continue;
            return recipe;
        }
        return null;
    }

    public Block getRecipeResult(Block block, int metadata) {
        for (DryInNetherRecipe recipe : this.recipes) {
            if (!recipe.matchesInputs(block, metadata)) continue;
            return recipe.getOutput();
        }
        return null;
    }

    public ArrayList<DryInNetherRecipe> getRecipes() {
        return this.recipes;
    }
}
