package io.github.fallOut015.milkable.fluid;

import io.github.fallOut015.milkable.MainMilkable;
import io.github.fallOut015.milkable.block.BlocksMilkable;
import io.github.fallOut015.milkable.item.ItemsMilkable;
import io.github.fallOut015.milkable.tags.FluidTagsMilkable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class InkFluid extends FlowingFluid {
    private static final ResourceLocation STILL_TEXTURE = new ResourceLocation(MainMilkable.MODID, "block/ink_still");
    private static final ResourceLocation FLOWING_TEXTURE = new ResourceLocation(MainMilkable.MODID, "block/ink_flow");

    @Override
    protected FluidAttributes createAttributes() {
        return FluidAttributes.builder(STILL_TEXTURE, FLOWING_TEXTURE).build(this);
    }

    public Fluid getFlowing() {
        return FluidsMilkable.FLOWING_INK.get();
    }

    public Fluid getSource() {
        return FluidsMilkable.INK.get();
    }

    public Item getBucket() {
        return ItemsMilkable.INK_BUCKET.get();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(World level, BlockPos pos, FluidState state, Random rand) {
        if (!state.isSource() && !state.getValue(FALLING)) {
            if (rand.nextInt(64) == 0) {
                level.playLocalSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundCategory.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
            }
        } else if (rand.nextInt(10) == 0) {
            level.addParticle(ParticleTypes.UNDERWATER, (double)pos.getX() + rand.nextDouble(), (double)pos.getY() + rand.nextDouble(), (double)pos.getZ() + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public IParticleData getDripParticle() {
        return ParticleTypes.DRIPPING_WATER;
    }

    protected boolean canConvertToSource() {
        return true;
    }

    protected void beforeDestroyingBlock(IWorld level, BlockPos pos, BlockState state) {
        TileEntity tileentity = state.hasTileEntity() ? level.getBlockEntity(pos) : null;
        Block.dropResources(state, level, pos, tileentity);
    }

    public int getSlopeFindDistance(IWorldReader level) {
        return 4;
    }

    public BlockState createLegacyBlock(FluidState state) {
        return BlocksMilkable.INK.get().defaultBlockState().setValue(FlowingFluidBlock.LEVEL, Integer.valueOf(getLegacyLevel(state)));
    }

    public boolean isSame(Fluid fluid) {
        return fluid == FluidsMilkable.INK.get() || fluid == FluidsMilkable.FLOWING_INK.get();
    }

    public int getDropOff(IWorldReader level) {
        return 1;
    }

    public int getTickDelay(IWorldReader level) {
        return 5;
    }

    public boolean canBeReplacedWith(FluidState state, IBlockReader level, BlockPos pos, Fluid fluid, Direction direction) {
        return direction == Direction.DOWN && !fluid.is(FluidTagsMilkable.INK);
    }

    protected float getExplosionResistance() {
        return 100.0F;
    }

    public static class Flowing extends InkFluid {
        protected void createFluidStateDefinition(StateContainer.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends InkFluid {
        public int getAmount(FluidState state) {
            return 8;
        }

        public boolean isSource(FluidState state) {
            return true;
        }
    }
}