package sponge.mixin;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import sponge.blocksSponge.SpongeBlocks;


import static net.minecraft.src.Block.*;

@Mixin (BlockSponge.class)
public class SpongeMixin extends Block {

    protected SpongeMixin(int par1, Material par2Material) {
        super(par1, par2Material);
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, int iBlockID) {
        becomeWet(world, i, j, k);
    }

    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        becomeWet(world, i, j, k);
    }
    @Unique
    public void becomeWet(World world, int i, int j, int k){

        Block neighborBlock1 = blocksList[world.getBlockId(i-1, j, k)];
        Block neighborBlock2 = blocksList[world.getBlockId(i, j-1, k)];
        Block neighborBlock3 = blocksList[world.getBlockId(i, j, k-1)];
        Block neighborBlock4 = blocksList[world.getBlockId(i+1, j, k)];
        Block neighborBlock5 = blocksList[world.getBlockId(i, j+1, k)];
        Block neighborBlock6 = blocksList[world.getBlockId(i, j, k+1)];


        boolean nearWater = false;
        if (neighborBlock1 == waterMoving) {
            removeWater(world, i-1, j, k);
            nearWater = true;
        }
        if (neighborBlock2 == waterMoving){
            removeWater(world, i, j-1, k);
            nearWater = true;
        }
        if (neighborBlock3 == waterMoving){
            removeWater(world, i, j, k-1);
            nearWater = true;
        }
        if (neighborBlock4 == waterMoving){
            removeWater(world, i+1, j, k);
            nearWater = true;
        }
        if (neighborBlock5 == waterMoving){
            removeWater(world, i, j+1, k);
            nearWater = true;
        }
        if (neighborBlock6 == waterMoving){
            removeWater(world, i, j, k+1);
            nearWater = true;
        }
        if (neighborBlock1 == waterStill) {
            removeWater(world, i-1, j, k);
            nearWater = true;
        }
        if (neighborBlock2 == waterStill){
            removeWater(world, i, j-1, k);
            nearWater = true;
        }
        if (neighborBlock3 == waterStill){
            removeWater(world, i, j, k-1);
            nearWater = true;
        }
        if (neighborBlock4 == waterStill){
            removeWater(world, i+1, j, k);
            nearWater = true;
        }
        if (neighborBlock5 == waterStill){
            removeWater(world, i, j+1, k);
            nearWater = true;
        }
        if (neighborBlock6 == waterStill){
            removeWater(world, i, j, k+1);
            nearWater = true;
        }

        if (nearWater) {
            world.setBlockWithNotify(i, j, k, SpongeBlocks.wetSpongeID);
        }
    }

    @Unique
    public void removeWater(World world, int i, int j, int k) {
        Block neighborBlock1 = blocksList[world.getBlockId(i-1, j, k)];
        Block neighborBlock2 = blocksList[world.getBlockId(i, j-1, k)];
        Block neighborBlock3 = blocksList[world.getBlockId(i, j, k-1)];
        Block neighborBlock4 = blocksList[world.getBlockId(i+1, j, k)];
        Block neighborBlock5 = blocksList[world.getBlockId(i, j+1, k)];
        Block neighborBlock6 = blocksList[world.getBlockId(i, j, k+1)];

        world.setBlockToAir(i, j, k);

        if (neighborBlock1 == waterMoving) {
            removeWater2(world, i-1, j, k);
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterMoving){
            removeWater2(world, i, j-1, k);
            world.setBlockToAir(i, j-1, k);
        }
        if (neighborBlock3 == waterMoving){
            removeWater2(world, i, j, k-1);
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterMoving){
            removeWater2(world, i+1, j, k);
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterMoving){
            removeWater2(world, i, j+1, k);
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterMoving){
            removeWater2(world, i, j, k+1);
            world.setBlockToAir(i, j, k+1);
        }
        if (neighborBlock1 == waterStill) {
            removeWater2(world, i-1, j, k);
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterStill) {
            removeWater2(world, i, j - 1, k);
            world.setBlockToAir(i, j - 1, k);
        }
        if (neighborBlock3 == waterStill){
            removeWater2(world, i, j, k-1);
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterStill){
            removeWater2(world,i+1, j, k);
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterStill){
            removeWater2(world, i, j+1, k);
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterStill){
            removeWater2(world, i, j, k+1);
            world.setBlockToAir(i, j, k+1);
        }
    }

    @Unique
    public void removeWater2(World world, int i, int j, int k) {
        Block neighborBlock1 = blocksList[world.getBlockId(i-1, j, k)];
        Block neighborBlock2 = blocksList[world.getBlockId(i, j-1, k)];
        Block neighborBlock3 = blocksList[world.getBlockId(i, j, k-1)];
        Block neighborBlock4 = blocksList[world.getBlockId(i+1, j, k)];
        Block neighborBlock5 = blocksList[world.getBlockId(i, j+1, k)];
        Block neighborBlock6 = blocksList[world.getBlockId(i, j, k+1)];

        world.setBlockToAir(i, j, k);

        if (neighborBlock1 == waterMoving) {
            removeWater3(world, i-1, j, k);
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterMoving){
            removeWater3(world, i, j-1, k);
            world.setBlockToAir(i, j-1, k);
        }
        if (neighborBlock3 == waterMoving){
            removeWater3(world, i, j, k-1);
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterMoving){
            removeWater3(world, i+1, j, k);
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterMoving){
            removeWater3(world, i, j+1, k);
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterMoving){
            removeWater3(world, i, j, k+1);
            world.setBlockToAir(i, j, k+1);
        }
        if (neighborBlock1 == waterStill) {
            removeWater3(world, i-1, j, k);
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterStill) {
            removeWater3(world, i, j - 1, k);
            world.setBlockToAir(i, j - 1, k);
        }
        if (neighborBlock3 == waterStill){
            removeWater3(world, i, j, k-1);
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterStill){
            removeWater3(world,i+1, j, k);
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterStill){
            removeWater3(world, i, j+1, k);
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterStill){
            removeWater3(world, i, j, k+1);
            world.setBlockToAir(i, j, k+1);
        }
    }

    @Unique
    public void removeWater3(World world, int i, int j, int k) {
        Block neighborBlock1 = blocksList[world.getBlockId(i-1, j, k)];
        Block neighborBlock2 = blocksList[world.getBlockId(i, j-1, k)];
        Block neighborBlock3 = blocksList[world.getBlockId(i, j, k-1)];
        Block neighborBlock4 = blocksList[world.getBlockId(i+1, j, k)];
        Block neighborBlock5 = blocksList[world.getBlockId(i, j+1, k)];
        Block neighborBlock6 = blocksList[world.getBlockId(i, j, k+1)];

        world.setBlockToAir(i, j, k);

        if (neighborBlock1 == waterMoving) {
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterMoving){
            world.setBlockToAir(i, j-1, k);
        }
        if (neighborBlock3 == waterMoving){
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterMoving){
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterMoving){
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterMoving){
            world.setBlockToAir(i, j, k+1);
        }
        if (neighborBlock1 == waterStill) {
            world.setBlockToAir(i-1, j, k);
        }
        if (neighborBlock2 == waterStill){
            world.setBlockToAir(i, j-1, k);
        }
        if (neighborBlock3 == waterStill){
            world.setBlockToAir(i, j, k-1);
        }
        if (neighborBlock4 == waterStill){
            world.setBlockToAir(i+1, j, k);
        }
        if (neighborBlock5 == waterStill){
            world.setBlockToAir(i, j+1, k);
        }
        if (neighborBlock6 == waterStill){
            world.setBlockToAir(i, j, k+1);
        }
    }
}
