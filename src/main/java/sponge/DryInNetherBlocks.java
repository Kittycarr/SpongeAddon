package sponge;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.src.*;

import java.util.Random;

import static sponge.DryInNetherCraftingManager.hasDryInNetherRecipe;

public class DryInNetherBlocks extends Block {


    public boolean doShowParticlesWhenDried(){
        return true;
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        super.randomDisplayTick(world, i, j, k, random);
        for (int count = 0; count < 10; ++count) {
            spawnParticles(world, i, j, k, random);
        }
    }


    public void spawnParticles(World world, int i, int j, int k, Random random){
        float offsetI = random.nextFloat();
        float offsetJ = 1-random.nextFloat();
        float offsetK = random.nextFloat();
        float smokeX = (float)i + offsetI;
        float smokeY = (float)j + offsetJ;
        float smokeZ = (float)k + offsetK;
        if (world.provider.dimensionId == -1) {
            if (doShowParticlesWhenDried()) {
                world.spawnParticle("largesmoke", smokeX, smokeY, smokeZ, 0.0, 0.05, 0.0);
            }
        }
    }

    public static DryInNetherBlocks staticDryInNetherBlocks;

    @Environment(value=EnvType.CLIENT)
    private Icon[] cookIcons;

    protected DryInNetherBlocks(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    public void onBlockAdded(World world, int i, int j, int k) {
        super.onBlockAdded(world, i, j, k);
        this.startDryingInNether(world, i, j, k);
    }

    public void startDryingInNether(World world, int i, int j, int k){
        if (world.provider.dimensionId == -1) {
            if (canBlockBeDried(world, i, j, k)) {
                scheduleUpdateBasedOnCookState(world, i, j, k);
            }
        }
    }

    public boolean canBlockBeDried(IBlockAccess blockAccess, int i, int j, int k) {
        return canBlockBeCooked(blockAccess,i,j,k);
    }
    private boolean canBlockBeCooked(IBlockAccess blockAccess, int i, int j, int k) {
        int iBlockID = blockAccess.getBlockId(i, j, k);
        Block block = Block.blocksList[iBlockID];
        if (block != null) {
            return getCanBeDriedInNether(blockAccess, i, j, k);
        }
        return false;
    }

    protected void scheduleUpdateBasedOnCookState(World world, int i, int j, int k) {
        int iTickRate = 1;
        world.scheduleBlockUpdate(i, j, k, this.blockID, iTickRate *= this.getBlockCookTimeMultiplier(world, i, j + 1, k));
    }

    private int getBlockCookTimeMultiplier(IBlockAccess blockAccess, int i, int j, int k) {
        int iBlockID = blockAccess.getBlockId(i, j, k);
        Block block = Block.blocksList[iBlockID];
        if (block != null) {
            return getCookTimeMultiplierInNetherDrying(blockAccess, i, j, k);
        }
        return 1;
    }

    public final int getCookTimeMultiplierInNetherDrying(IBlockAccess blockAccess, int i, int j, int k) {
        int metadata = blockAccess.getBlockMetadata(i, j, k);
        DryInNetherRecipe recipe = DryInNetherCraftingManager.instance.getRecipe(this, metadata);
        return recipe != null ? recipe.getCookTimeMultiplier() : (byte)1;
    }


    @Override
    public void updateTick(World world, int i, int j, int k, Random random) {
        int iOldCookCounter = this.getCookCounter(world, i, j, k);
        int iNewCookCounter = 0;
        if (this.canBlockBeCooked(world, i, j, k)) {
            if (iOldCookCounter >= 15) {
                    this.cookBlock(world, i, j, k);
            } else {
                iNewCookCounter = iOldCookCounter + 1;this.scheduleUpdateBasedOnCookState(world, i, j, k);
            }
        } else {
            this.scheduleUpdateBasedOnCookState(world, i, j, k);
        }
        if (iOldCookCounter != iNewCookCounter) {
            this.setCookCounter(world, i, j, k, iNewCookCounter);
        }
    }


    public int getCookCounter(IBlockAccess blockAccess, int i, int j, int k) {
        return this.getCookCounter(blockAccess.getBlockMetadata(i, j, k));
    }
    public int getCookCounter(int iMetadata) {
        return iMetadata;
    }


    public void setCookCounter(World world, int i, int j, int k, int iCounter) {
        int iMetadata = this.setCookCounter(world.getBlockMetadata(i, j, k), iCounter);
        world.setBlockMetadataWithNotify(i, j, k, iMetadata);
    }
    public int setCookCounter(int iMetadata, int iCounter) {
        return iCounter;
    }


    private void cookBlock(World world, int i, int j, int k) {
        int iBlockID = world.getBlockId(i, j, k);
        Block block = Block.blocksList[iBlockID];
        if (block != null && getCanBeDriedInNether(world, i, j, k)) {
            onDriedInNether(world, i, j, k);
        }
    }

    public void onDriedInNether(World world, int i, int j, int k) {
        Block outputs = this.getOutputsWhenDriedInNether(world, i, j, k);
        if (outputs != null) {
            world.setBlockWithNotify(i,j,k,outputs.blockID);
        }
    }

    public final Block getOutputsWhenDriedInNether(IBlockAccess blockAccess, int i, int j, int k) {
        int metadata = blockAccess.getBlockMetadata(i, j, k);
        return DryInNetherCraftingManager.instance.getRecipeResult(this, metadata);
    }

    @Override
    @Environment(value= EnvType.CLIENT)
    public void registerIcons(IconRegister register) {
        this.cookIcons = new Icon[7];
        for (int i = 0; i < 7; ++i) {
            this.cookIcons[i] = register.registerIcon("btw:kiln_cooking_overlay_" + (i + 1));
        }
    }


    @Environment(value=EnvType.CLIENT)
    public Icon getCookTextureForCurrentState(IBlockAccess blockAccess, int i, int j, int k) {
        int iTextureIndex = this.getCookCounter(blockAccess, i, j, k) / 2 - 1;
        if (iTextureIndex >= 0 && iTextureIndex <= 6) {
            return this.cookIcons[iTextureIndex];
        }
        return null;
    }

    public final boolean getCanBeDriedInNether(IBlockAccess blockAccess, int i, int j, int k) {
        int metadata = blockAccess.getBlockMetadata(i, j, k);
        return DryInNetherCraftingManager.instance.getRecipeResult(this, metadata) != null;
    }

    @Environment(value= EnvType.CLIENT)
    public void renderDryingInNether(RenderBlocks renderBlocks, int i, int j, int k, boolean bFirstPassResult) {
        if (bFirstPassResult && hasDryInNetherRecipe[this.blockID]) {
            Icon overlayTexture;
            IBlockAccess blockAccess = renderBlocks.blockAccess;
            if (!renderBlocks.hasOverrideBlockTexture() && getCanBeDriedInNether(blockAccess, i, j, k) && (overlayTexture = this.getCookTextureForCurrentState(blockAccess, i, j , k)) != null) {
                this.renderBlockWithTexture(renderBlocks, i, j, k, overlayTexture);
            }
        }
    }

}
